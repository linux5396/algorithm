package com.linxu.algorithm.bydate.date190910;

/**
 * @author linxu
 * @date 2019/9/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 二维数组的数据检索，简称平面搜索
 * 问题：
 * 1、数组为每行从左往右递增，每列从上往下递增
 * 2、输入一个特定的数组，判断是否在该平面内
 * 3、找到输出true，找不到输出false.
 * <p>
 * 注意，该平面并非正方形
 * 解决思想：选取边角，而不要选取中间逐渐缩小区域进行判断
 */
public class PlanarSearch {
    public static boolean search(int[][] array, int number) {
        //三个判断条件缺一不可
        //fix 2019/9/16
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        //边界值快速判断
        if (number < array[0][0] || number > array[array.length - 1][array[0].length - 1]) {
            return false;
        }
        //选取左上角
        int leftTop;
        int row = 0;
        int col = array[0].length - 1;
        while (row <= (array.length - 1) && col >= 0) {
            leftTop = array[row][col];
            if (number == leftTop) {
                return true;
            }
            if (number < leftTop) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}};
        System.out.println(search(a, 9));
        int[][] b = {};
        search(b, 9);
    }
}
