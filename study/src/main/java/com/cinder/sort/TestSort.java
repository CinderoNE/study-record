package com.cinder.sort;

import com.cinder.sort.impl.QuickSort;

import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        int[] nums = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        int[] nums2 = {1,2,3,4,5};
        CinderSort sort = new QuickSort();
        sort.sort(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
