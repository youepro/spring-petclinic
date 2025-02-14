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

class BinaryMatrixReconstruction {
    public static int[][] reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        int[][] matrix = new int[2][n];

        // 遍历每一列来填充矩阵
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 2) {
                // 如果列和为2，则上行和下行都为1
                if (upper > 0 && lower > 0) {
                    matrix[0][i] = 1;
                    matrix[1][i] = 1;
                    upper--;
                    lower--;
                } else {
                    // 不足以满足条件
                    return new int[0][0];
                }
            } else if (colsum[i] == 1) {
                // 如果列和为1，优先分配给上行
                if (upper > 0) {
                    matrix[0][i] = 1;
                    upper--;
                } else if (lower > 0) {
                    matrix[1][i] = 1;
                    lower--;
                } else {
                    // 不足以满足条件
                    return new int[0][0];
                }
            }
        }

        // 检查是否所有的upper和lower都被用完
        if (upper == 0 && lower == 0) {
            return matrix;
        } else {
            return new int[0][0];
        }
    }

    public static void main(String[] args) {
        // 示例用例1
        int upper1 = 2;
        int lower1 = 1;
        int[] colsum1 = {1, 1, 1};
        int[][] result1 = reconstructMatrix(upper1, lower1, colsum1);
        System.out.println("Output 1: " + Arrays.deepToString(result1));  // 输出: [[1,1,0],[0,0,1]]

        // 示例用例2
        int upper2 = 2;
        int lower2 = 3;
        int[] colsum2 = {2, 2, 1};
        int[][] result2 = reconstructMatrix(upper2, lower2, colsum2);
        System.out.println("Output 2: " + Arrays.deepToString(result2));  // 输出: []

        // 示例用例3
        int upper3 = 5;
        int lower3 = 5;
        int[] colsum3 = {2, 1, 2, 0, 1, 0, 1, 2, 0, 1};
        int[][] result3 = reconstructMatrix(upper3, lower3, colsum3);
        System.out.println("Output 3: " + Arrays.deepToString(result3));  // 输出: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
    }
}

class MatrixConstruction {
    public static int[][] constructMatrix(int[] rowSum, int[] colSum) {
        int rows = rowSum.length;
        int cols = colSum.length;
        int[][] matrix = new int[rows][cols];

        // 逐行和逐列填充矩阵
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 选择当前行和列的最小值
                int value = Math.min(rowSum[i], colSum[j]);
                matrix[i][j] = value;

                // 更新行和列的和
                rowSum[i] -= value;
                colSum[j] -= value;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        // 示例用例1
        int[] rowSum1 = {3, 8};
        int[] colSum1 = {4, 7};
        int[][] result1 = constructMatrix(rowSum1, colSum1);
        System.out.println("Output 1: " + java.util.Arrays.deepToString(result1));// [[3,0],[1,7]]

        // 示例用例2
        int[] rowSum2 = {5, 7, 10};
        int[] colSum2 = {8, 6, 8};
        int[][] result2 = constructMatrix(rowSum2, colSum2);
        System.out.println("Output 2: " + java.util.Arrays.deepToString(result2));// [[0,5,0],[6,1,0],[2,0,8]]

        // 示例用例3
        int[] rowSum3 = {14, 9};
        int[] colSum3 = {6, 9, 8};
        int[][] result3 = constructMatrix(rowSum3, colSum3);
        System.out.println("Output 3: " + java.util.Arrays.deepToString(result3));// [[0,9,5],[6,0,3]]

        // 示例用例4
        int[] rowSum4 = {1, 0};
        int[] colSum4 = {1};
        int[][] result4 = constructMatrix(rowSum4, colSum4);
        System.out.println("Output 4: " + java.util.Arrays.deepToString(result4));// [[1],[0]]

        // 示例用例5
        int[] rowSum5 = {0};
        int[] colSum5 = {0};
        int[][] result5 = constructMatrix(rowSum5, colSum5);
        System.out.println("Output 5: " + java.util.Arrays.deepToString(result5));// [[0]]
    }
}














