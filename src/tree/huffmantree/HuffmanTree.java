package tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,28,6,1};
        Node root= createHuffmanTree(arr);
        preOrder(root);
    }
    
    public static Node createHuffmanTree(int[] arr){
        //1. 遍历arr，将arr的每个元素构建成一个Node，
        //   将Node放入到Arraylist。
        List<Node> nodes=new ArrayList<>();
        for (int value:arr){
            nodes.add(new Node(value));
        }
        while (nodes.size()>1){
            Collections.sort(nodes);
            //2 取出权值最小的结点
            Node leftNode=nodes.get(0);
            Node rightNode=nodes.get(1);
            //3 构建新的二叉树
            Node parent=new Node(leftNode.value+rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            //4 从ArrayList中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5 将parent加入到ArrayList
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else{
            System.out.println("空树");
        }
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}