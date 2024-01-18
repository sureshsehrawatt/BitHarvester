package com.boldbit.bitharvester.rough;

public class Leet {

    public static int climbStairs(int n) {
        int curr=1, pre=1, temp=0;
        for(int i=1; i<n; i++){ 
            temp = curr;
            curr = pre + curr;
            pre = temp;
        }
        return curr;


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
        int n = 3;
        Leet.climbStairs(n);
    }
}