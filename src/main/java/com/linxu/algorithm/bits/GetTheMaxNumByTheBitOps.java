package com.linxu.algorithm.bits;

/**
 * @author linxu
 * @date 2020/4/15
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class GetTheMaxNumByTheBitOps {
    public static int getMax(int a, int b) {
        //get sub
        int subOfab = a - b;
        //get sign of ...
        int signOfa = sign(a);
        int signOfb = sign(b);
        int signOfsubAB = sign(subOfab);
        //是否不同
        int difSab = signOfa ^ signOfb;
        //符号相同
        int sameSab = flip(difSab);
        //A大的情况：a,b符号不同，a为0或者正，b为负，则返回a.
        //a,b符号相同，且a-b符号为正，则返回a.
        int returna = difSab * signOfa + sameSab * signOfsubAB;
        //a为负，b为0或者正，则返回b.
        //a,b符号相同，a-b为负数，则返回B
        //那么，下面的表达即，如果是a,那么不返回b；如果非a，那么就是b
        int reutrnb = flip(returna);
        return a * returna + b * reutrnb;
    }

    /**
     * get sign
     * @return  0 equals negative  or 0. 1 mean >0
     */
    private static int sign(int n) {
        return flip((n >> 31) & 1);
    }

    /**
     * @param n the lowest bit
     * @return if >0 return 0  if ==0  return 1
     */
    private static int flip(int n) {
        return n ^ 1;
    }

    public static void main(String[] args) {
        System.out.println(getMax(Integer.MIN_VALUE+1,Integer.MIN_VALUE));
        System.err.println((0>>31)&1);
    }
}
