package com.cinder.question;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr,int low,int high){
        if(low >= high)
            return ;
        int temp = arr[low];
        int i = low;
        int j = high;

        int t;
        while(i < j){
            while(i < j && arr[j] >= temp){
                j--;
            }
            while(i < j && arr[i] <= temp){
                i++;
            }
            if(i < j){
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[low] = arr[j];
        arr[j] = temp;
        quickSort(arr,low,i-1);
        quickSort(arr,i+1,high);
    }

    private static void sort(int[] nums, int left, int right){
        if(left >= right)
            return ;
        int base = nums[left];
        int leftt = left;
        int rightt = right;
        while(leftt < rightt){
            while(leftt < rightt && nums[rightt] > base){
                rightt--;
            }
            if(leftt < rightt){
                int temp = nums[rightt];
                nums[rightt] = nums[leftt];
                nums[leftt] = temp;
                leftt++;
            }
            while(leftt < rightt && nums[leftt] < base){
                leftt++;
            }
            if(leftt < rightt){
                int temp = nums[rightt];
                nums[rightt] = nums[leftt];
                nums[leftt] = temp;
                rightt--;
            }
        }
        nums[left] = nums[leftt];
        nums[leftt] = base;
        sort(nums,left,leftt-1);
        sort(nums,leftt+1,right);

    }

    public static void main(String[] args) {
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(System.out::println);

        sort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(System.out::println);


    }
}
