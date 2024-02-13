package com.boldbit.bitharvester.rough;

import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[][] divideArray(int[] nums, int k) {
        int[][] answer = new int[nums.length / 3][3];
        Arrays.sort(nums);
        int y = 1;
        // for (int i = 0; i < nums.length - 1; i++) {
        //     y++;
        //     int diff = (nums[i + 1] - nums[i]);
        //     if (diff > k && y != 3)
        //         return new int[0][0];
        //     if (y == 3)
        //         y = 1;
        // }
        int z = 0;
        for (int i = 0; i < nums.length / 3; i++) {
            for (int j = 0; j < 3; j++) {
                answer[i][j] = nums[z];
                z++;
            }
            for (int j = 0; j < 2; j++) {
                if (!((answer[i][j + 1] - answer[i][j]) <= k))
                    return new int[0][0];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution obj=new Solution();
        int[] nums = {6,10,5,12,7,11,6,6,12,12,11,7};
        obj.divideArray(nums, 2);
        
    }
}