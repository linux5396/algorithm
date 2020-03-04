package bytedance.chars;

import java.util.Arrays;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 大数相乘
 */
public class MultiPlicationInBigNumber {
    /**
     * 根据数字乘法的计算规则，从一个数个位开始依次求出与另一个数的乘积并逐位相加。下面以“98”和“99”的乘法计算来说明算法思想。
     * <p>
     * 首先考虑乘积的总位数，两个数相乘的最大位数为两数的位数之和，所以先申请一个结果字符串位数为4，并且每一位都初始化为‘0’
     * 从第一个数的个位数‘8’开始，依次与“99”相乘。在乘法过程中首先初始化每一位置的进位add为0，然后计算出对应单个位的乘积mul，
     * 比如第一位8x9=72，然后取其个位与当前位置的数字以及前一位置的进位add相加得到sum，此时sum为2+0+0=2，所以结果字符串的个位数字就为‘2’。当前位置的进位add更新为mul的十位数与sum十位数之和，此时进位add为7+0=7.
     * 计算完一次单个位置的乘法后，最后将当前乘积的前一位更新为add，具体来说8x99=792，但遍历完99后结果只记录了最后两位“92”，
     * 此时进位add为7，所以要将前一位更新为7
     * 计算完结果后要判断输出的总位数，因为可能出现结果字符串前几位都是0的情况，找到第一位不是0的数字然后返回之后的字符串。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return "0";
        }
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }
        char[] number1 = num1.toCharArray();
        char[] number2 = num2.toCharArray();
        //最大长度为两个的和
        char[] multiplication = new char[number1.length + number2.length];
        //必须填充！！
        Arrays.fill(multiplication, '0');
        for (int i = num1.length() - 1; i >= 0; i--) {
            int add = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int mul = (number1[i] - 48) * (number2[j] - 48);
                //个位和
                int sum = (multiplication[i + j + 1] - 48) + add + (mul % 10);
                multiplication[i + j + 1] = (char) (sum % 10 + 48);
                add = mul / 10 + sum / 10;
            }
            //检查结束后的进位情况，因为如果8*99=792，由于J的长度为2，只能处理92，7这个进位就会丢失；
            if (add != 0) {
                multiplication[i] += add;
            }
        }
        for (int i = 0; i < multiplication.length; i++) {
            if (multiplication[i] != '0') {
                return new String(multiplication).substring(i);
            }
        }
        return "0";
    }

    public static void main(String[] args) {
        System.out.println(new MultiPlicationInBigNumber().multiply("123", "456"));
    }
}
