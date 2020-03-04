package bytedance.chars;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class ReverseWords {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);
        int left, right;
        left = 0;
        right = left;
        while (left < chars.length) {
            if (chars[left] == ' ') {
                left++;
                right = left + 1;
            } else if (right == chars.length || chars[right] == ' ') {
                reverse(chars, left, right - 1);
                left = right;
                right++;
            } else {
                right++;
            }
        }
        String[] splits = new String(chars).split(" ");
        //filter 空格
        StringBuilder stringBuilder = new StringBuilder();
        for (String ss : splits) {
            if (ss.length() > 0)
                stringBuilder.append(ss).append(" ");
        }
        return stringBuilder.length()>0? stringBuilder.toString().substring(0,stringBuilder.length()-1):"";
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWords().reverseWords(" "));
    }
}
