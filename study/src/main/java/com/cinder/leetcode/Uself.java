package com.cinder.leetcode;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:30 2020/6/3/003
 * @Modified By:
 */
public class Uself {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println("m = " + m);
        System.out.println("n = " + n);
        int[] arr = new int[n];
        int count = 0;
        while(n != 1){
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] == 0){
                    count++;
                }
                if(count == m){
                    System.out.println(i + "出场");
                    arr[i] = 1;
                    n--;
                    count = 0;
                    break;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 0)
                System.out.println("result = " + (i+1));
        }
        int result = 0;
        for (int i = 2; i <= arr.length; i++) {
            result = (result + m) % i;
        }
        System.out.println(result+1);
    }
}
