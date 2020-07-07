package com.cinder.sort.impl;

import com.cinder.sort.CinderSort;


public class BubbleSort implements CinderSort {

    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            boolean flag = false;
            for (int j = 1; j <nums.length-i ; j++) {
                if (nums[j-1] > nums[j]){
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                    flag = true;
                }
            }
            if (!flag)
                return;
        }
    }


}
