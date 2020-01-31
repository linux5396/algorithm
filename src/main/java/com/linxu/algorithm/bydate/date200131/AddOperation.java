package com.linxu.algorithm.bydate.date200131;

/**
 * @author linxu
 * @date 2020/1/31
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class AddOperation {
    /**
     * 不使用加减乘除做加法
     *
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        int sum, carry;
        sum = num1 ^ num2;
        carry = (num1 & num2) << 1;
        while (carry != 0) {
            int temp = sum;
            sum = sum ^ carry;
            carry = (temp & carry) << 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new AddOperation().Add(1, 10));
    }
}
