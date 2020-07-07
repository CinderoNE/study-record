package com.cinder.question;

public class Revese {
    public static void main(String[] args) {
        System.out.println(revese("abcdefghijkdsadsa"));
    }

    static String revese(String str){
        char[] chars = str.toCharArray();
        int end = chars.length-1;
        int start = 0;
        for (int i = end; i > chars.length/2; i--) {
            char c = chars[i];
            chars[i] = chars[start];
            chars[start] = c;
            start++;
        }
        return String.valueOf(chars);
    }
}
