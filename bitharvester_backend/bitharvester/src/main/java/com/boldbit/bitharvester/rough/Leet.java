package com.boldbit.bitharvester.rough;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Leet {

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for(int i : arr)
            count.merge(i, 1, Integer::sum);
        return new HashSet<>(count.values()).size() == count.size();

        // List<Integer> list = new ArrayList<>();
        // Set<Integer> occurrences = new HashSet<>();
        // for(int i : arr)
        //     list.add(i);
        // while (list.size() > 0) {
        //     int temp = list.get(0);
        //     int o = 0;
        //     for (int i = 0; i < list.size(); i++) {
        //         if (list.get(i).equals(temp)) {
        //             list.remove(i);
        //             o++;
        //             i--;
        //         }
        //     }
        //     if(!occurrences.add(o))
        //         return false;
        // }
        // return true;


        // int output = 0;
        // int row = grid.length, col = grid[0].length;
        // for (int r = 0; r < row; r++) {
        //     for (int c = 0; c < col; c++) {
        //         int temp = 0;
        //         if (r >= 0 && r < row && c + 1 >= 0 && c + 1 < col) {
        //             if (grid[r][c + 1] > temp) {
        //                 temp = grid[r][c + 1];
        //             }
        //         }
        //         if (r >= 0 && r < row && c - 1 >= 0 && c < col) {
        //             if (grid[r][c - 1] > temp) {
        //                 temp = grid[r][c - 1];
        //             }
        //         }
        //         if (r + 1 >= 0 && r + 1 < row && c >= 0 && c < col) {
        //             if (grid[r + 1][c] > temp) {
        //                 temp = grid[r + 1][c];
        //             }
        //         }
        //         if (r - 1 >= 0 && r < row && c >= 0 && c < col) {
        //             if (grid[r - 1][c] > temp) {
        //                 temp = grid[r - 1][c];
        //             }
        //         }
        //         if (r < row && c < col && (temp + grid[r][c]) > output)
        //             output = temp + grid[r][c];
        //         if (output == 0 && grid[r][c] > 0)
        //             output = grid[r][c];
        //     }
        // }
        // System.out.println(output);
        // return output;
    }

    public static void main(String[] args) {
        int[]arr = {1,2,2,1,1,3};
        Leet.uniqueOccurrences(arr);
    }
}