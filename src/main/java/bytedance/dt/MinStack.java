package bytedance.dt;

import java.util.Stack;

/**
 * @author linxu
 * @date 2020/3/4
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class MinStack {
    private final Stack<Integer> dataStack = new Stack<>();
    private final Stack<Integer> helpStack = new Stack<>();

    public MinStack() {
    }

    public void push(int x) {
        if (dataStack.isEmpty()) {
            dataStack.push(x);
            helpStack.push(x);
        } else {
            int minEle = helpStack.peek();
            dataStack.push(x);
            helpStack.push(Math.min(minEle, x));
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.pop();
            helpStack.pop();
        }
    }

    public int top() {
        if (!dataStack.isEmpty()) {
            return dataStack.peek();
        } else {
            return -1;
        }
    }

    public int getMin() {
        return helpStack.isEmpty() ? -1 : helpStack.peek();
    }
}
