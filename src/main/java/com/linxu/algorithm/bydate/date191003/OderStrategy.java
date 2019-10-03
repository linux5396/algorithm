package com.linxu.algorithm.bydate.date191003;

/**
 * @author linxu
 * @date 2019/10/3
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 排序策略
 */
public abstract class OderStrategy<T extends Object> {
    protected boolean defaultStrategy(T elem) {
        int n= (Integer) elem;
        return ((n & 1) == 1);
    }
}
