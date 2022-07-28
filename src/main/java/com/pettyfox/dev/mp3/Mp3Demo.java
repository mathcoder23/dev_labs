package com.pettyfox.dev.mp3;

import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * 获取、修改mp3文件信息
 *
 * @author gsdcr
 */
public class Mp3Demo {
    /**
     * 原来文件存放位置
     */
    public static String originPath = "";
    /**
     * 新文件存放位置
     */
    public static String targetPath = "";

    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException, NotSupportedException {
        
        String name = "";
        File parentFile = new File(originPath);
        File targetFile = new File(targetPath);
        // 创建新文件父目录
        targetFile.mkdirs();
        // 获取原来所有文件
        File[] files = parentFile.listFiles();
        for (File file : files) {
            if (!file.getName().contains(".mp3")) {
                continue;
            }
            // 创建 Mp3File 对象
            System.out.println(file.getCanonicalFile());
            Mp3File mp3file = new Mp3File(file);
            // 获取文件名
            String fileName = file.getName();
            // 获取ID3v2对象，通过该对象可以获取文件信息如标题、艺术家等内容
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            // 将文件名去除扩展名作为新的标题
            String newTitle = fileName.substring(0, fileName.lastIndexOf("."));
            newTitle = newTitle.replace("(歌词版)","").trim();
            // 简单打印一下
            System.out.println(id3v2Tag.getTitle() + "->" + newTitle);
            // 修改标题
            id3v2Tag.setTitle(newTitle);
            // 修改艺术家
            id3v2Tag.setArtist(name);
            // 修改唱片集
            id3v2Tag.setAlbum(name);
            // 将修改后的文件保存到新的目录
            mp3file.save(targetPath + fileName);
        }
    }
}