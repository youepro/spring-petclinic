package org.springframework.samples.petclinic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeLib {
    
}

class AdjustMatrix {
    public int[][] adjustGrid(int[][] grid) {
        int n = grid.length;
        // 存储左下角三角形对角线
        List<Integer> leftDiagonal = new ArrayList<>();
        // 存储右上角三角形对角线
        List<Integer> rightDiagonal = new ArrayList<>();

        // 提取左下角三角形对角线
        for (int col = 0; col < n; col++) {
            for (int row = n - 1; row >= col; row--) {
                leftDiagonal.add(grid[row][col]);
            }
        }

        // 提取右上角三角形对角线
        for (int row = 0; row < n; row++) {
            for (int col = row; col < n; col++) {
                rightDiagonal.add(grid[row][col]);
            }
        }

        // 排序
        Collections.sort(leftDiagonal, Collections.reverseOrder());
        Collections.sort(rightDiagonal);

        // 放回矩阵
        int index = 0;
        for (int col = 0; col < n; col++) {
            for (int row = n - 1; row >= col; row--) {
                grid[row][col] = leftDiagonal.get(index++);
            }
        }

        index = 0;
        for (int row = 0; row < n; row++) {
            for (int col = row; col < n; col++) {
                grid[row][col] = rightDiagonal.get(index++);
            }
        }

        return grid;
    }

    public static void main(String[] args) {
        AdjustMatrix am = new AdjustMatrix();

        // 示例 1
        int[][] grid1 = {
            {1, 7, 3},
            {9, 8, 2},
            {4, 5, 6}
        };
        int[][] result1 = am.adjustGrid(grid1);
        System.out.println(Arrays.deepToString(result1)); // [[8, 2, 3], [9, 6, 7], [4, 5, 1]]
        // [[1, 2, 3], [8, 6, 7], [9, 5, 8]]

        // 示例 2
        int[][] grid2 = {
            {0, 1},
            {1, 2}
        };
        int[][] result2 = am.adjustGrid(grid2);
        System.out.println(Arrays.deepToString(result2)); // [[2, 1], [1, 0]]
        // [[0, 1], [2, 2]]

        // 示例 3
        int[][] grid3 = {
            {1}
        };
        int[][] result3 = am.adjustGrid(grid3);
        System.out.println(Arrays.deepToString(result3)); // [[1]]
        // [[1]]
    }
}
