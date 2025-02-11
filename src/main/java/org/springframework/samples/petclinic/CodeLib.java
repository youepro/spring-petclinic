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
        List<List<Integer>> leftDiagonal = new ArrayList<>();
        // 存储右上角三角形对角线
        List<List<Integer>> rightDiagonal = new ArrayList<>();

        // 提取左下角三角形对角线
        // 首先输出主对角线和下方元素
        List<Integer> left = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            // System.out.print(grid[i][i] + " "); // 主对角线元素
            left.add(grid[i][i]);
        }
        Collections.sort(left, Collections.reverseOrder());
        
        // 然后输出主对角线下方的元素
        
        for (int i = 1; i < grid.length; i++) {
            List<Integer> lleft = new ArrayList<>();
            for (int j = 0; j < grid.length - i; j++) {
                lleft.add(grid[i + j][j]);
                // System.out.print(matrix[i + j][j] + " "); // 下方元素
                // 4 8 7 
            }
            Collections.sort(lleft, Collections.reverseOrder());    
            leftDiagonal.add(lleft);
        }

        // 然后输出主对角线上方的元素
        for (int i = 1; i < grid.length; i++) {
            List<Integer> right = new ArrayList<>();
            for (int j = 0; j < grid.length - i; j++) {
                right.add(grid[j][i + j]);
                // System.out.print(grid[j][i + j] + " "); // 下方元素
                // 2 6 3
            }
            // 排序
            Collections.sort(right);
            rightDiagonal.add(right);
        }
        // 放回矩阵
        int index = 0;
        for (int i = 0; i < grid.length; i++) {
            grid[i][i] = left.get(index++);
        }
        index = 0;    
        for (int i = 1; i < grid.length; i++) {
            List<Integer> lleft = leftDiagonal.get(index++);
            int l = 0;
            for (int j = 0; j < grid.length - i; j++) {
                grid[i + j][j] = lleft.get(l++);
            }
        }
        index = 0;
        for (int i = 1; i < grid.length; i++) {
            List<Integer> right = rightDiagonal.get(index++);
            int r = 0;
            for (int j = 0; j < grid.length - i; j++) {
                grid[j][i + j] = right.get(r++);
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
        // (1,1 2,2 3,3),(2,1 3,2),(3,1)  (1,2 2,3),(1,3)
        int[][] result1 = am.adjustGrid(grid1);
        System.out.println(Arrays.deepToString(result1)); // [[8, 2, 3], [9, 6, 7], [4, 5, 1]]

        // 示例 2
        int[][] grid2 = {
            {0, 1},
            {1, 2}
        };
        int[][] result2 = am.adjustGrid(grid2);
        System.out.println(Arrays.deepToString(result2)); // [[2, 1], [1, 0]]

        // 示例 3
        int[][] grid3 = {
            {1}
        };
        int[][] result3 = am.adjustGrid(grid3);
        System.out.println(Arrays.deepToString(result3)); // [[1]]
    }
}

// 左斜线元素
class LowerLeftTriangle {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // 按左斜线顺序提取左下角三角形元素
        System.out.println("Left Lower Triangle Elements in Diagonal Order:");
        
        // 首先输出主对角线和下方元素
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i][i] + " "); // 主对角线元素
        }
        
        // 然后输出主对角线下方的元素
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length - i; j++) {
                System.out.print(matrix[i + j][j] + " "); // 下方元素
                // 4 8 7 
            }
        }
        System.out.println();
        // 然后输出主对角线上方的元素
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length - i; j++) {
                System.out.print(matrix[j][i + j] + " "); // 下方元素
                // 2 6 3
            }
        }
    }
}

class GroupAssignment {
    public int[] assignElements(int[] groups, int[] elements) {
        int[] assigned = new int[groups.length];
        Arrays.fill(assigned, -1); // Initialize with -1

        for (int i = 0; i < groups.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                if (groups[i] % elements[j] == 0) {
                    assigned[i] = j; // Assign the first valid element index
                    break; // Stop searching after the first match
                }
            }
        }
        return assigned;
    }

    public static void main(String[] args) {
        GroupAssignment ga = new GroupAssignment();
        
        // Test case 1
        int[] groups1 = {8, 4, 3, 2, 4};
        int[] elements1 = {4, 2};
        System.out.println(Arrays.toString(ga.assignElements(groups1, elements1))); // [0, 0, -1, 1, 0]
        
        // Test case 2
        int[] groups2 = {2, 3, 5, 7};
        int[] elements2 = {5, 3, 3};
        System.out.println(Arrays.toString(ga.assignElements(groups2, elements2))); // [-1, 1, 0, -1]
        
        // Test case 3
        int[] groups3 = {10, 21, 30, 41};
        int[] elements3 = {2, 1};
        System.out.println(Arrays.toString(ga.assignElements(groups3, elements3))); // [0, 1, 0, 1]
    }
}

