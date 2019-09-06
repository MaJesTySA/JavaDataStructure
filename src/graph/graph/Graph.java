package graph.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList; //顶点集合
    private int[][] edges; //邻结矩阵
    private int numOfEdges; //边的个数
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String[] VertexValues = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : VertexValues) {
            graph.insertVertex(vertex);
        }
        //A-B,A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs();
        System.out.println("\n广度优先");
        graph.bfs();

    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回节点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i对应的节点数据 0->A 1-B
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回节点间的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }

    //得到某个节点的第一个邻结节点下标w
    private int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个节点的下标，来获取下一个邻结节点下标
    private int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0)
                return i;
        }
        return -1;
    }

    //深度优先遍历
    private void dfs(int i) {
        //首先访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        //查找节点i的第一个邻结节点w
        int w = getFirstNeighbor(i);
        //下一个邻结节点是否存在
        while (w != -1) {
            //下一个邻结节点是否访问过
            if (isVisited[w] = false) {
                dfs(w);
            }
            //如果已经被访问过，查找该邻结节点的下一个邻结节点
            w = getNextNeighbor(i, w);
        }

    }

    //遍历所有节点，进行dfs
    public void dfs() {
        isVisited=new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i])
                dfs(i);
        }
    }

    //广度优先遍历
    private void bfs(int i) {
        int u;//队列头结点对应的下标
        int w;//邻结节点对应的下标
        LinkedList<Integer> queue = new LinkedList();//节点访问顺序队列
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        //节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列头的节点
            u = queue.removeFirst();
            //得到该节点第一个邻结节点下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为起始点，找u的下一个
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited=new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i])
                bfs(i);
        }
    }
}
