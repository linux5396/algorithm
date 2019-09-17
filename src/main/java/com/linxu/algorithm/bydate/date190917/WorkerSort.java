package com.linxu.algorithm.bydate.date190917;

/**
 * @author linxu
 * @date 2019/9/17
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 实现一个时间效率为O(N)的排序算法，具体场景如下：
 * 1、公司员工有进10K个，员工的年龄区间在15-100之间。包括15和100
 * 2、对员工的年龄进行排序
 * 3、可以使用辅助空间
 */
public class WorkerSort {
    /**
     * 年龄位移
     */
    private static final int OFFSET = 15;

    public static int[] sort(int[] ages) {
        if (ages == null || ages.length == 0) {
            return null;
        }
        int[] counter = new int[86];
        for (int i = 0; i < ages.length; i++) {
            counter[ages[i - OFFSET]]++;
        }
        int[] result = new int[ages.length];
        for (int i = 0, r = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[i]; j++) {
                result[r++] = i + OFFSET;
            }
        }
        return result;
    }
}
