package com.linxu.algorithm.utils;


/**
 * @author linxu
 * @date 2019/9/29
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class GenerationUtil {
    public static final Object NULL_POINTER = null;

    /**
     * memset util
     ***/
    public static void memset(int[][] arr, int initElem) {
        //single init
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    arr[i][j] = initElem;
                }
            }
        }
    }

    public static void memset(int[] arr, int initElem) {
        //single init
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = initElem;
            }
        }
    }

    public static void memset(char[][] arr, char initElem) {
        //single init
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    arr[i][j] = initElem;
                }
            }
        }
    }

    public static void memset(char[] arr, char initElem) {
        //single init
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = initElem;
            }
        }
    }

    public static void memset(boolean[][] arr, boolean initElem) {
        //single init
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    arr[i][j] = initElem;
                }
            }
        }
    }

    public static void memset(boolean[] arr, boolean initElem) {
        //single init
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = initElem;
            }
        }
    }

    /**
     * print util
     ***/
    public static void print(int[] arr, boolean withEnter) {
        if (arr != null) {
            for (int n : arr) {
                if (withEnter) {
                    System.out.println(n);
                } else {
                    System.out.printf("%-4d", n);
                }
            }
        }
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+",");
            }
            System.out.println("");
        }
    }

    public static void print(char[] arr, boolean withEnter) {
        if (arr != null) {
            for (char n : arr) {
                if (withEnter) {
                    System.out.println(n);
                } else {
                    System.out.print(n);
                }
            }
        }
    }

    /**
     * odd . even util
     */
    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }

    public static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    /**
     * @param number number
     * @return descending powers of this numbers to int array
     */
    public static int[] descendingPowers(int number) {
        int length = 0;
        int origin = number;
        for (int k = 1; k <= number; k <<= 1) {
            length++;
            number -= k;
        }
        if (number > 0) {
            length++;
        }
        int[] ret = new int[length];
        int i = 0;
        for (int k = 1; k <= origin; k <<= 1, i++) {
            origin -= k;
            ret[i] = k;
        }
        if (origin > 0) {
            ret[i] = origin;
        }
        return ret;
    }

    /***pause thread********/
    public static void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*********swap the elem****************/
    public static void swap(char[] array, int idx1, int idx2) throws ArrayIndexOutOfBoundsException {
        if (array != null) {
            char temp = array[idx1];
            array[idx1] = array[idx2];
            array[idx2] = temp;
        }
    }
    /*******************素数判断********************/
    /**
     * using enum
     */
    public static boolean isPrim(int number) {
        if (number <= 0) {
            return false;
        }
        if (number > 2) {
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * fast using sqrt
     */
    public static boolean isPrimFast(int number) {
        if (number <= 0) {
            return false;
        }
        if (number > 2) {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    /***************牛顿迭代的开方******************/

    /**
     * 牛顿迭代
     *
     * @param n
     * @return
     */
    public static double sqrt(int n) {
        double x = n, y = 0.0;
        while (Math.abs(x - y) > 0.00001) {
            y = x;
            x = 0.5 * (x + n / x);
        }
        return x;
    }

    /***********gcd*****************/
    public static int gcd(int m, int n) {
        int temp = 0;
        while (n != 0) {
            temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    /**************partition fuction**************/
    public static int partitionWith2Sentinels(int[] arr, int low, int high) {
        int i, j, base;
        i = low;
        j = high;
        //base就是基准位
        base = arr[low];
        while (i < j) {
            //先看右边，依次往左递减
            while (base <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (base >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换;
            //如果i>j,则证明原本顺序无需更换
            if (i < j) {
                //交换位置
                int t;
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        arr[low] = arr[i];
        arr[i] = base;
        return i;
    }

    /************查找最大值*****************/
    public static int findTheMax(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int[] findTheMaxAndIdx(int[] arr) {
        int[] max = new int[2];
        max[0] = arr[0];
        max[1] = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max[0] < arr[i]) {
                max[0] = arr[i];
                max[1] = i;
            }
        }
        return max;
    }

    /************查找最小值*****************/
    public static int findTheMin(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] a = descendingPowers(15);
        print(a, false);
    }
}
