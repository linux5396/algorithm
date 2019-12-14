package com.linxu.algorithm.bydate.date191215;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2019/12/15
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：三色排序，用0 1 2代表
 */
public class ColorSort {

    public static int[] colorSort(int[] colors) {
        if (colors == null || colors.length == 0) {
            return null;
        }
        //normal 
        int low = 0;
        int high = colors.length - 1;

        for (int i = 0; i < high; i++) {
            if (colors[i] == 0) {
                GenerationUtil.swap(colors, i, low);
                ++low;
            } else if (colors[i] == 2) {
                GenerationUtil.swap(colors, i, high);
                high--;
                i--;
            }
        }
        return colors;
    }

    public static int[] myColorSort(int[] colors) {
        if (colors == null || colors.length == 0) {
            return null;
        }
        //三色排序的实际思想
        //三个指针  left middle high
        //left的右位middle  middle的右为high
        //mid指向1  mid继续走，mid指向0，那么与left交换，mid继续走
        //mid指向2，与high交换，mid不走
        int low = 0;
        int middle = 0;
        int high = colors.length - 1;
        //限定
        while (middle < high) {
            if (colors[middle] == 0) {
                GenerationUtil.swap(colors, middle, low);
                low++;
                middle++;
            } else if (colors[middle] == 2) {
                GenerationUtil.swap(colors, middle, high);
                high--;
            } else {
                middle++;
            }
        }
        return colors;
    }

    public static void main(String[] args) {
        int[] c = {0, 0, 2, 0, 1, 2, 1, 2,1};
        GenerationUtil.print(myColorSort(c), false);
    }
}
