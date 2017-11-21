package com.v.quarter.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/16.
 */

public class FileUtil {
    public static void writeObjectToFile(Object o,File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(o);
                objectOutputStream.flush();
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static Object ReadFromFile(File file) {

        //在这里做一个  判断此次从文件读取距离上一次读取是否过了20分钟
        long currentTime = getCurrentTime();
        long l = file.lastModified();

        //时间差
        long l1 = currentTime - l;
        //如果时间差大于等于20分钟name让它重新去请求 重新写入
//        System.out.println(l1/1000/60+"分");
        //开发阶段 先关闭
        if (l1 / 1000 / 60 > 20) {
            return null;
        }
        //关闭

        if (!file.exists()) {
            return null;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Object o = objectInputStream.readObject();
                return o;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //可以得到当前的时间
    private static long getCurrentTime() {
        Date date = new Date();
//        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time=format.format(date);
        return date.getTime();
    }

    ;

    public static Object ReadFromLocalFile(File file) {
        Object o = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            o = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return o;
    }


}
