package bytedance.chars;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 对Unix的路径输入进行简化
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1013/
 */
public class SimplifyUnixPath {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        char[] characters = path.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < characters.length; i++) {
            if (stack.isEmpty()) {
                stack.push(characters[i]);
            } else {
                char top = stack.peek();
                if (top == '/' && characters[i] == '/') {
                    continue;
                } else if (top == '.' && characters[i] == '/') {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push('/');
                    }
                } else if (top == '.' && characters[i] == '.') {
                    if (stack.size() >= 3) {
                        for (int j = 0; j < 3; j++) {
                            stack.pop();
                        }
                    }
                } else {
                    stack.push(characters[i]);
                }
            }
        }
        char tail;
        if (!stack.isEmpty()) {
            tail = stack.pop();
            if (stack.isEmpty()) {
                return tail + "";
            }
        } else {
            return "/";
        }
        char[] temp = new char[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty() && i >= 0) {
            temp[i--] = stack.pop();
        }
        return new String(temp);
    }

    /**
     * 消/法：使用消斜杆方法，简化计算逻辑
     */
    public String simplifyPathInDeque(String path) {
        String[] pathArr = path.split("/");
        LinkedList<String> stack = new LinkedList<>();
        for (String s : pathArr) {
            if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else if (".".equals(s) || "".equals(s)) {
                continue;
            } else {
                stack.addLast(s);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append('/');
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SimplifyUnixPath().simplifyPath("/home//foo/"));
        System.out.println(new SimplifyUnixPath().simplifyPath("/a/./b/../../c/"));
        System.out.println(new SimplifyUnixPath().simplifyPath("/a/../../b/../c//.//"));
        System.out.println(new SimplifyUnixPath().simplifyPath("/a//b////c/d//././/.."));
        System.out.println(new SimplifyUnixPath().simplifyPath("/../"));
        System.out.println(new SimplifyUnixPath().simplifyPath("/.."));
        System.out.println(new SimplifyUnixPath().simplifyPathInDeque("/..."));
    }
}
