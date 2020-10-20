package com.github.whvixd.util.datastructure;

/**
 * 图的邻接表数据结构
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
    private static class VNode<T>{
        // 第一条边
        private ArcNode firstArc;
        // 顶点存储的信息
        private T data;
    }

    /**
     * 边
     */
    private static class ArcNode{
        // 该边指向的顶点位置
        private int adjvex;
        // 下一条边
        private ArcNode nextArc;
        // 边的权值
        private float weight;
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
