package com.linxu.algorithm.data_struct;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lx
 * @date 12月
 * @package com.linxu.algorithm.data_struct
 * 并查集的实现
 */
public class UnionSet {
    private final Map<String, String> nodeAndLeader = new HashMap<>();
    private final Map<String, Integer> nodeAndWeight = new HashMap<>();
    private static final int baseWeight = 1;

    public void makeSet(String[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return;
        }
        for (int i = 0; i < nodes.length; i++) {
            makeSet(nodes[i]);
        }
    }

    private void makeSet(String node) {
        nodeAndLeader.put(node, node);
        nodeAndWeight.put(node, baseWeight);
    }

    public boolean isSameSet(String node1, String node2) {
        return getLeader(node1) == getLeader(node2);
    }

    /**
     * 基于路径压缩的递归查找代表
     */
    private String getLeader(String node) {
        String father = nodeAndLeader.get(node);
        //recursive
        if (father != node) {
            father = getLeader(father);
        }
        nodeAndLeader.put(node, father);
        return father;
    }

    public void union(String node1, String node2) {
        String leader1 = getLeader(node1);
        String leader2 = getLeader(node2);
        int weightOfSet1 = nodeAndWeight.get(leader1);
        int weightOfSet2 = nodeAndWeight.get(leader2);

        if (weightOfSet1 >= weightOfSet2) {
            nodeAndLeader.put(leader2, leader1);
            nodeAndWeight.put(leader1, weightOfSet1 + weightOfSet2);
        }
    }

}
