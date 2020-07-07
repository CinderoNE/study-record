package com.cinder.question;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        solu();
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        bubbleSort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
        readFile(new File("a.txt"));
        query("select * from user");
    }


    public static void readFile(File file){
        try(InputStream inputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
        ) {
            int len = -1;
            char[] flush = new char[2];
            StringBuilder sb = new StringBuilder();
            while((len = br.read(flush)) != -1){
                System.out.println(sb.append(flush));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag;
        for(int i=0; i < arr.length-1; i++){
            flag = false;
            for(int j= 1; j < arr.length-i; j++){
                if(arr[j-1] > arr[j]){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if (flag == false)
                return ;
        }

        String str = "abc";
        try {
            str = new String(str.getBytes(),"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String toISO_8859_1(String source){
        try {
            return new String(source.getBytes("GB2312"),"ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void solu(){
        double h = 100;
        double sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += h;
            h /= 2;
            sum += h;
        }
        System.out.println("第10次反弹高度 = " + h);
        System.out.println("共经过 = " + sum + "米");
    }

    public static ResultSet query(String sql){
        Connection conn;
        PreparedStatement ps =null;
        ResultSet resultSet = null;
        try {
            conn = DBUtil.getCon();
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            System.out.println(resultSet);
            resultSet.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
