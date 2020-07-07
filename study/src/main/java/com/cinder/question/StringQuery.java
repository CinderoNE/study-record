package com.cinder.question;



public class StringQuery {
    public static void main(String[] args) {
        System.out.println(solution("aabbccb"));
        int i=5;
        int j=10;
        System.out.println(i + ~j);
    }

    static String solution(String source){
        if (source.length() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        char[] chars = source.toCharArray();
        char cur = chars[0];
        int count = 1;
        for (int i = 0; i < chars.length-1; i++) {
            cur = chars[i];
            if (cur != chars[i+1]){
                sb.append(cur).append(count);
                count = 1;
            } else{
                ++count;
                continue;
            }

        }
        cur = chars[chars.length-1];
        sb.append(cur).append(count);
        return sb.toString();
    }
}
