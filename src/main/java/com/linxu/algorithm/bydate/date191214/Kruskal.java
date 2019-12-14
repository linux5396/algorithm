package com.linxu.algorithm.bydate.date191214;

import com.linxu.algorithm.data_struct.UnionSet;
import com.linxu.algorithm.utils.GenerationUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Lx
 * @date 12月
 * @package com.linxu.algorithm.bydate.date191214
 * 使用于稀疏图。
 */
public class Kruskal {
    private static class Edge {
        String endPoint1;
        String endPoint2;
        int weight;

        public Edge(String endPoint1, String endPoint2, int weight) {
            this.endPoint1 = endPoint1;
            this.endPoint2 = endPoint2;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "endPoint1='" + endPoint1 + '\'' +
                    ", endPoint2='" + endPoint2 + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    public List<Edge> makeMST(List<Edge> edges, String[] nodes) {
        //sort list
        edges.sort(Comparator.comparingInt(o -> o.weight));
        //define now process idx
        int curOffset = 0;
        //创建并查集
        UnionSet unionSet = new UnionSet();
        unionSet.makeSet(nodes);
        //n-1 mst
        List<Edge> mst = new ArrayList<>(nodes.length - 1);
        for (int i = 0; i < edges.size(); i++) {
            if (mst.size() == nodes.length - 1) {
                break;
            }
            Edge edge = edges.get(i);
            if (!unionSet.isSameSet(edge.endPoint1, edge.endPoint2)) {
                unionSet.union(edge.endPoint1, edge.endPoint2);
                mst.add(edge);
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        Edge edge1 = new Edge("A", "C", 1);
        Edge edge2 = new Edge("A", "B", 6);
        Edge edge3 = new Edge("A", "D", 5);
        Edge edge4 = new Edge("B", "C", 5);
        Edge edge5 = new Edge("C", "D", 5);
        Edge edge6 = new Edge("B", "E", 3);
        Edge edge7 = new Edge("E", "F", 6);
        Edge edge8 = new Edge("F", "D", 2);
        Edge edge9 = new Edge("C", "E", 6);
        Edge edge10 = new Edge("C", "F", 14);

        List<Edge> edges = new ArrayList<>();
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        edges.add(edge5);
        edges.add(edge6);
        edges.add(edge7);
        edges.add(edge8);
        edges.add(edge9);
        edges.add(edge10);

        String[] s = {
                "A", "B",
                "C", "D",
                "E", "F"
        };

        List<Edge> edges1=new Kruskal().makeMST(edges,s);
        for (Edge e:edges1
             ) {
            System.out.println(e);
        }
    }

}
