package com.cinder.sort.impl;

import com.cinder.sort.CinderSort;

public class InsertSort implements CinderSort {
    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            //要插入的元素
            int temp = nums[i];
            int j = i;
            while(j > 0 && temp < nums[j-1]){
                nums[j] = nums[--j];
            }
            if(i != j)
                nums[j] = temp;
        }
    }


}
