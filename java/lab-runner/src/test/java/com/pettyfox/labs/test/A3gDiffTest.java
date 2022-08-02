package com.pettyfox.labs.test;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * a3g 实验性功能对比
 */
@Slf4j
public class A3gDiffTest {

  @Test
  void run() {
    JSONObject wei = JSON.parseObject(FileUtil.readString("a3g/wei.json", "UTF-8"));
    JSONObject jindou = JSON.parseObject(FileUtil.readString("a3g/jindou.json", "UTF-8"));
    List<String> weiList = new ArrayList<>();
    List<String> jinDouList = new ArrayList<>();
    JSONArray weiArr = getList(wei);
    JSONArray jindouArr = getList(jindou);
    for (int i = 0; i < weiArr.size(); i++) {
      weiList.add(weiArr.getString(i));
    }
    for (int i = 0; i < jindouArr.size(); i++) {
      jinDouList.add(jindouArr.getString(i));
    }
    log.info("wei json:{}", weiList.size());
    log.info("jindou json:{}", jinDouList.size());

    jinDouList.removeAll(weiList);
    log.info("jindou over:{}", jinDouList.size());
    jinDouList.forEach(item -> {
      log.info("over:{}", item);
    });
  }

  @Test
  void run2() {
    JSONObject wei = JSON.parseObject(FileUtil.readString("a3g/wei.json", "UTF-8"));
    JSONObject jindou = JSON.parseObject(FileUtil.readString("a3g/jindou.json", "UTF-8"));
    List<String> weiList = new ArrayList<>();
    List<String> jinDouList = new ArrayList<>();
    JSONArray weiArr = getList(wei);
    JSONArray jindouArr = getList(jindou);
    for (int i = 0; i < weiArr.size(); i++) {
      weiList.add(weiArr.getString(i));
    }
    for (int i = 0; i < jindouArr.size(); i++) {
      jinDouList.add(jindouArr.getString(i));
    }
    log.info("wei json:{}", weiList.size());
    log.info("jindou json:{}", jinDouList.size());

    weiList.removeAll(jindouArr);
    log.info("jindou lack:{}", weiList.size());
    weiList.forEach(item -> {
      log.info("lack:{}", item);
    });
  }

  JSONArray getList(JSONObject obj) {
    return obj.getJSONArray("experimentsForUsersList").getJSONObject(0).getJSONArray("experiments");
  }
}
