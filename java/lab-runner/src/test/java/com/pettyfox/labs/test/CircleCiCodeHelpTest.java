package com.pettyfox.labs.test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

@Slf4j
public class CircleCiCodeHelpTest {
  @Test
  void run() {
    log.info("Hello");
    File template = new File(
        "/Users/wei.feng/Documents/compass/work-lab/urbancompass/.circleci/scripts/scheduled_pipelines/payloads/build-and-deploy-ceres-media-app-ecr.json");
    String dir = template.getParentFile().getPath();
    String name = FilenameUtils.getName(template.getPath());
    String baseName = FilenameUtils.getBaseName(template.getPath());
    String workflowDir
        = "/Users/wei.feng/Documents/compass/work-lab/urbancompass/.circleci/config/workflows";
    log.info("dir:{}", dir);
    log.info("name:{}", name);
    log.info("baseName:{}", baseName);
    log.info("workflowDir:{}", workflowDir);

    // data
    List<String[]> input =
        ImmutableList.<String[]>builder()
            .add(new String[] {"andromeda", "18"})
            .add(new String[] {"ceres", "16"})
            .add(new String[] {"comet", "17"})
            .add(new String[] {"fortknox", "17"})
            .add(new String[] {"louvre", "19"})
            .add(new String[] {"mars", "19"})
            .add(new String[] {"mercury", "16"})
            .add(new String[] {"pluto", "21"})
            .add(new String[] {"sprinklercontroller", "18","dpi"})
            .add(new String[] {"sprinklerscheduler", "16","dpi"})
            .add(new String[] {"sprinklerworker", "17","dpi"})
            .add(new String[] {"telescope", "17"})
            .add(new String[] {"vesta", "16"})
            .build();

    // build
    String content = FileUtil.readString(template, "UTF-8");
    input.forEach(items -> {
      log.info("building item:{}/{}", items[0], items[1]);
      String itemBaseName = baseName.replace("ceres", items[0]);
      if(items.length>=3){
        itemBaseName = itemBaseName.replace("media",items[2]);
      }
      File checkFile = Paths.get(workflowDir, itemBaseName+".yaml").toFile();
      if (!checkFile.exists()) {
        log.error("file not exist:{}", checkFile.getPath());
        return;
      }
      log.info("check passed:{}", checkFile.getPath());

      String itemContent = content.replace("ceres", items[0]).replace("16", items[1]);
      if(items.length>=3){
        itemContent = itemContent.replace("media",items[2]);
      }
      File out = Paths.get(dir, itemBaseName+".json").toFile();
      FileUtil.writeBytes(itemContent.getBytes(StandardCharsets.UTF_8),
          Paths.get(dir, itemBaseName+".json").toFile());
      log.info("finished build:{}", out.getPath());
    });
  }
}
