package com.linxu.algorithm.hot100.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2020/2/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 同样是子集问题，但是子集大小为K
 */
public class SubCollectionInK {
    public List<List<Integer>> subSets(int[] numbers, int k) {
        List<List<Integer>> list = new LinkedList<>();
        subSet(new LinkedList<>(),list,numbers,0,k);
        return list;
    }

    private void subSet(List<Integer> curSet, List<List<Integer>> list, int[] numbers, int index, int k) {
        if (curSet.size() == k) {
            list.add(new LinkedList<>(curSet));
            return;
        }
        for (int i = index; i < numbers.length; i++) {
            curSet.add(numbers[i]);
            subSet(curSet, list, numbers, i+1, k);
            curSet.remove(curSet.size() - 1);
        }
    }

    void print(List<List<Integer>> list) {
        for (List<Integer> l1 : list
                ) {
            for (Integer i : l1
                    ) {
                System.out.print(i + " ");
            }
            System.out.println("");
            ;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4};
        new SubCollectionInK().print(new SubCollectionInK().subSets(numbers,2));
    }
}
