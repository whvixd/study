package com.github.whvixd.util.datastructure;

/**
 * 图的邻接矩阵定义的数据结构
 * Created by wangzhixiang on 2020/10/20.
 */
public class AdjoinMatrixGraph<T> {
    // 边 edges[1][2]=1 => (1) -> (2) , edges[2][1]=1 , (2) -> (1)  1与2相互连通
    // 若是有权图，可用float，值为权值
    private int edges[][];
    // 顶点数
    private int n;
    // 边数
    private int e;
    // 顶点
    private Vertex<T> vex[];


    /**
     * 顶点数据结构
     */
    private static class Vertex<T> {
        // 顶点编号
        private int no;
        // 顶点存储的信息
        private T info;
    }
}
/*
C 数据结构

#define maxSize=100;

typedef struct
{
    int edges [maxSize][maxSize];
    int n;
    int e;
    Vertex vex[maxSize];
}Graph;

type struct{
    int no;
    char info;
}Vertex;
 */
