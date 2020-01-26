package com.linxu.algorithm.bydate.date200123;


/**
 * @author linxu
 * @date 2020/1/23
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：字符串反转问题，具体看方法doc.
 */
public class ReverseStr {
    public static String reverse(String text) {
        //special
        if (text == null) {
            return null;
        }
        //all reverse
        char[] reverseText = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            reverseText[i] = text.charAt(text.length() - i - 1);
        }
        //sub reverse.
        int start = 0;
        int end = 0;
        while (start < reverseText.length - 1) {
            if (reverseText[start] == ' ') {
                start++;
                end = start + 1;
            } else if (reverseText[end] == ' ' || end == reverseText.length - 1) {
                reverse(reverseText, start, --end);
                start = ++end;
            } else {
                end++;
            }
        }
        //construct
        return new String(reverseText);
    }

    public static String leftRotate(String text, int leftLength) {
        //special
        if (text == null) {
            return null;
        }
        if (leftLength > text.length()) {
            return text;
        }
        char[] reverseText = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            reverseText[i] = text.charAt(i);
        }
        reverse(reverseText, 0, leftLength - 1);
        reverse(reverseText, leftLength, reverseText.length - 1);
        //all reverse
        reverse(reverseText, 0, reverseText.length - 1);
        return new String(reverseText);
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char tempChar = chars[start];
            chars[start] = chars[end];
            chars[end] = tempChar;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse("I am student."));
        System.out.println(leftRotate("abcdefg",2));
    }
}
