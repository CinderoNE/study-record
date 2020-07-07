package com.cinder.sort.impl;

import com.cinder.sort.CinderSort;

public  class QuickSort implements CinderSort {
    @Override
    public  void sort(int[] nums) {
        quickSort(nums,0,nums.length-1);
    }

    private void quickSort(int[] nums,int low,int high){
       if(low < high){
           int index = getIndex(nums, low, high);

           quickSort(nums,low,index-1);
           quickSort(nums,index+1,high);

       }

    }

    private int getIndex(int[] nums,int low,int high){
        int temp = nums[low];

        while(low < high){
            while(low < high && nums[high] >= temp)
                high--;
            nums[low] = nums[high];
            while(low < high && nums[low] <= temp)
                low++;
            nums[high] = nums[low];
        }
        nums[low] = temp;
        return low;
    }


}
