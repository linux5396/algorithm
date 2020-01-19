package com.linxu.algorithm.bydate.date200119;

/**
 * @author linxu
 * @date 2020/1/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：在排序数组中查找某一个数字，并返回该数字出现的次数
 */
public class SearchTheNumInSortArr {
    /**
     * 利用两次折半查找
     *
     * @param array
     * @param target
     * @return
     */
    public static int findTheNoAppearTimes(int[] array, int target) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("");
        }
        if (target < array[0] || target > array[array.length - 1]) {
            throw new IllegalArgumentException("");
        }
        //normal
        int firstIdx = getFirstKIdx(array, 0, array.length - 1, target);
        int lastIdx = getLastKIdx(array, 0, array.length - 1, target);
        if (lastIdx != -1 && firstIdx != -1) {
            return lastIdx - firstIdx+1;
        }
        return -1;
    }

    private static int getFirstKIdx(int[] array, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int middleIdx = (end + start) >> 1;
        int middleData = array[middleIdx];
        if (middleData == target) {
            if ((middleIdx > 0 && array[middleIdx - 1] != target) || middleIdx == 0) {
                return middleIdx;
            } else {
                //继续查找，end为middle的前一个位置
                end = middleIdx - 1;
            }
        } else if (middleData > target) {
            end = middleIdx - 1;
        } else {
            start = middleIdx + 1;
        }
        return getFirstKIdx(array, start, end, target);
    }

    private static int getLastKIdx(int[] array, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int middleIdx = (end + start) >> 1;
        int middleData = array[middleIdx];
        if (middleData == target) {
            if ((middleIdx < array.length - 1 && array[middleIdx + 1] != target) || middleIdx == array.length - 1) {
                return middleIdx;
            } else {
                //继续查找,start为middle的下一个位置
                start = middleIdx + 1;
            }
        } else if (middleData < target) {
            start = middleIdx + 1;
        } else {
            end = middleIdx - 1;
        }
        return getLastKIdx(array, start, end, target);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 3, 3, 4, 7, 9, 11};
        System.out.println(findTheNoAppearTimes(array, 1));
    }
}
