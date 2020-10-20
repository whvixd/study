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

    public AdjoinLinkedGraph(VNode<T> vNodes[]){
        if(vNodes==null||vNodes.length==0){
            return;
        }
        this.vNodes=vNodes;
        this.n=vNodes.length;
    }

    public AdjoinLinkedGraph(VNode<T> vNodes[],int e){
        if(vNodes==null||vNodes.length==0){
            return;
        }
        this.vNodes=vNodes;
        this.n=vNodes.length;
        this.e=e;
    }

    public void setvNodes(VNode<T>[] vNodes) {
        if(vNodes==null||vNodes.length==0){
            return;
        }
        this.vNodes = vNodes;
        this.n=vNodes.length;
    }

    public void setE(int e) {
        this.e = e;
    }

    /**
     * 顶点信息
     */
    public static class VNode<T> {
        // 第一条边
        private ArcNode firstArc;
        // 顶点存储的信息
        private T data;

        public VNode() {
        }

        public VNode(ArcNode firstArc, T data) {
            this.firstArc = firstArc;
            this.data = data;
        }

        public void setFirstArc(ArcNode firstArc) {
            this.firstArc = firstArc;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    /**
     * 边
     */
    public static class ArcNode {
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
     * 邻接表图
     * @param vIndex 图中存储的顶点下标
     */
    public void deepFirstSearch(int vIndex) {
        // 置为访问态
        visitedVNodes[vIndex]=1;
        // 访问顶点
        visit(vIndex);
        // 获取顶点的边
        ArcNode arc = this.vNodes[vIndex].firstArc;
        while (arc!=null){
            // 判断边中的顶点是否访问
            if(visitedVNodes[arc.adjvex]==0){
                deepFirstSearch(arc.adjvex);
            }
            arc=arc.nextArc;
        }
    }

    private void visit(int vIndex){
        VNode vNode = this.vNodes[vIndex];
        System.out.println(vNode.data);
    }

    public void broadFirstSearch(int vIndex, int visitedVNodes[]){
        int length = this.n;
        int queue[] = new int[length];
        int front=0,rear=0;
        int j;
        ArcNode arcNode;

        visit(vIndex);
        visitedVNodes[vIndex]=1;
        rear=(rear+1)%length;
        // 进队
        queue[rear]=vIndex;

        // 队不为空
        while(front!=rear){
            // 出栈
            front=(front+1)%length;
            j=queue[front];

            arcNode=this.vNodes[j].firstArc;
            while (arcNode!=null){
                int adjvex = arcNode.adjvex;
                // 未访问，访问后进队
                if(visitedVNodes[adjvex]==0){
                    visit(adjvex);
                    visitedVNodes[adjvex]=1;
                    rear=(rear+1)%length;
                    queue[rear]=adjvex;
                }
                // 已访问，指向下一条边
                arcNode=arcNode.nextArc;
            }
        }
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
