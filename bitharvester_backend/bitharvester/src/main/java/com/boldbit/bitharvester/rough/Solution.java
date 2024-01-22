package com.boldbit.bitharvester.rough;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public static int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int[] dup = new int[2];
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i] == nums[i + 1]){
                dup[0] = nums[i];
                break;
            }
        }

        for (int i = 0; i < nums.length; i++){
            if (nums[i] != i+1){
                dup[1] = i + 1;
                // break;
            }
        }
        if (dup[1] == 0)
            dup[1] = nums.length;
        return dup;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,4,6,5};
        Solution.findErrorNums(nums);
    }
}