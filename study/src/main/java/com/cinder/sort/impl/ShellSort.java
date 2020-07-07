package com.cinder.sort.impl;

import com.cinder.sort.CinderSort;

public class ShellSort implements CinderSort {
    @Override
    public void sort(int[] nums) {
        //分组，最开始gap为数组长度的一半
        for(int gap = nums.length/2; gap > 0; gap /= 2){
            //各个分组进行插入排序
            for (int i = gap; i < nums.length; i++) {
                //将arr[i]插入到所在分组正确位置
                insertI(nums,gap,i);
            }
        }
    }

    private void insertI(int[] arr,int gap,int i){
        int inserted = arr[i];
        int j;
        for (j = i-gap; j >= 0 && inserted < arr[j] ; j -= gap) {
            arr[j+gap] = arr[j];
        }
        arr[j+gap] = inserted;
    }
}
