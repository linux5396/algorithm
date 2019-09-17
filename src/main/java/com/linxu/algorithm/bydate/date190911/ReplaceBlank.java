package com.linxu.algorithm.bydate.date190911;

import com.linxu.algorithm.Recommend;

/**
 * @author linxu
 * @date 2019/9/11
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 1、输入一个带有空格的字符串，将每个字符串替换成指定的字符串
 */
public class ReplaceBlank {
    private static final Character BLANK = ' ';

    /**
     * 思想：
     * 遍历计算出空格的数量，申请一块刚刚好满足替换后的大小的内存
     * 遍历原字符串，将原字符串的内容迁移，同时遇到空格，则执行替换.
     *
     * 时间为N ，相比使用stringbuilder进行替换，在大数据下，速度快了接近一倍
     *
     * @param origion
     * @param specify
     * @return
     */
    @Recommend
    public static String replace(StringBuffer origion, String specify) throws NullPointerException{
        if (origion == null || origion.length() == 0) {
            throw new NullPointerException("原字符串为空串");
        }

        char[] origionChars = origion.toString().toCharArray();
        char[] specifiedChars = specify.toCharArray();
        //设置计算游标
        int isr = 0;
        //执行一次N的遍历
        for (int i = 0; i < origionChars.length; i++) {
            if (origionChars[i] == ' ') {
                isr++;
            }
        }
        //最后的新数组的大小
        int newCapacity = origionChars.length + (isr * specifiedChars.length);
        char[] newChars = new char[newCapacity];
        for (int i = 0, j = 0; i < newCapacity && j < origionChars.length; i++) {
            if (origionChars[j] == ' ') {
                for (int t = 0; t < specifiedChars.length; t++) {
                    newChars[i++] = specifiedChars[t];
                }
                j++;
                continue;
            }
            //正常迁移
            newChars[i] = origionChars[j++];
        }
        return new String(newChars);
    }

    /**
     * 时间也为N 但是底层需要多次重新分配新的内存块，导致速度下降
     * 该算法相比直接使用string类去拼接会快很多，因为string类的拼接会重复创建对象
     * 原因：string是固定长度的而stringbuilder内部采用了一个char[]数组维护字符串内容
     * 只有当超过固定长度的时候才会重新扩容
     *
     * @param origion
     * @param specify
     * @return
     */
    public static String replaceSimple(String origion, String specify) throws NullPointerException{
        if (origion == null || origion.length() == 0) {
            throw new NullPointerException("原字符串为空串");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : origion.toCharArray()) {
            if (c == BLANK) {
                stringBuilder.append(specify);
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < 10000000; i++) {
            s.append("OK ERROR  ");
        }
        String s1=s.toString();
        System.out.println("Start==========================");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long ok = System.currentTimeMillis();
      String s2=  replace(new StringBuffer("Hello world"), "%20");
        System.out.println(s2);
        System.out.println(System.currentTimeMillis() - ok);

       /* long start = System.currentTimeMillis();
        replaceSimple(s1, "__");
        System.err.println(System.currentTimeMillis() - start);*/
    }
}
