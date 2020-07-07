package com.cinder.sort.impl;

import com.cinder.sort.CinderSort;

public class SelectionSort implements CinderSort {
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            int min = i;
            for (int j = i+1; j < nums.length; j++) {
                if(nums[min] > nums[j])
                    min = j;
            }
            if(min != i){
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
    }
}
