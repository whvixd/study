package com.github.whvixd.util.datastructure;

/**
 * 图的邻接表数据结构 -> 链式存储结构
 * Created by wangzhixiang on 2020/10/20.
 */
public class AdjoinLinkedGraph<T> {
    // 邻接表，存储所有顶点
    private VNode<T> vNodes[];
    // 顶点数
    private int n;
    // 边数
    private int e;


    /**
     * 顶点信息
     */
    private static class VNode<T> {
        // 第一条边
        private ArcNode firstArc;
        // 顶点存储的信息
        private T data;
    }

    /**
     * 边
     */
    private static class ArcNode {
        // 该边指向的顶点位置
        private int adjvex;
        // 下一条边
        private ArcNode nextArc;
        // 边的权值
        private float weight;
    }


    private transient int visitedVNodes[]=new int[n];

    /**
     * 深度优先搜索遍历，类似于二叉树的先序遍历
     * @param graph 邻接表图
     * @param vIndex 图中存储的顶点下标
     */
    public void deepFirstSearch(AdjoinLinkedGraph graph, int vIndex) {
        // 置为访问态
        visitedVNodes[vIndex]=1;
        // 访问顶点
        visit(graph,vIndex);
        // 获取顶点的边
        ArcNode arc = graph.vNodes[vIndex].firstArc;
        while (arc!=null){
            // 判断边中的顶点是否访问
            if(visitedVNodes[arc.adjvex]==0){
                deepFirstSearch(graph,arc.adjvex);
            }
            arc=arc.nextArc;
        }
    }

    private void visit(AdjoinLinkedGraph graph,int vIndex){
        VNode vNode = graph.vNodes[vIndex];
        System.out.println(vNode.data);
    }

}
/*
#define maxSize=100;

typedef struct
{
    VNode adjvex[maxSize];
    int n;
    int e;
}Graph

typedef struct
{
    char data;
    ArcNode *firstAcr;
}VNode;

typedef struct
{
    int adjvex;
    ArcNode *nextAcr;
    float weight;
}ArcNode;

 */
