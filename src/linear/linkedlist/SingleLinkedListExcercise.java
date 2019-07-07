package linear.linkedlist;

import java.util.Stack;

public class SingleLinkedListExcercise {

    public static void main(String[] args) {
        SingleLinkedList linkedList=new SingleLinkedList();
        linkedList.addNode(new Node(20));
        linkedList.addNode(new Node(30));
        linkedList.addNode(new Node(40));
        linkedList.addNode(new Node(50));
        linkedList.addNode(new Node(60));
        linkedList.addNode(new Node(70));
        linkedList.addNode(new Node(80));
        linkedList.addNode(new Node(90));
        linkedList.addNode(new Node(100));
        System.out.println("链表长度：");
        System.out.println(getLength(linkedList.getHeadNode())+"\n");
        System.out.println("倒数第五个链表元素");
        System.out.println((findLastNodeOf(linkedList.getHeadNode(),5)).data+"\n");
        System.out.println("逆序打印");
        reverseShow(linkedList.getHeadNode());
        System.out.println();
        System.out.println("链表反转");
        reverse_1(linkedList);
        linkedList.showLinkedList();
    }
    //1. 求出单链表有效节点个数（不含头节点）
    public static int getLength(Node headNode){
        if(headNode.next==null) return 0;
        int length=0;
        Node current=headNode.next;
        while(current!=null) {
            length++;
            current=current.next;
        }
        return length;
    }

    //2. 查找单链表的倒数第k个节点
    //  先从头遍历，得到总长度，
    //  从第1个开始遍历，遍历length-index个。
    public static Node findLastNodeOf(Node headNode,int index){
        if (headNode.next==null) return null;
        int length=getLength(headNode);
        if (index<=0 || index>length) return null;
        int count=0;
        Node current=headNode.next;
        while(count<(length-index)){
            current=current.next;
            count++;
        }
        return current;
    }

    //3. 单链表反转
    public static void reverse_1(SingleLinkedList linkedList){
        Node headNode=linkedList.getHeadNode();
        if (headNode==null||headNode.next==null) return;
        Node reverseHead=new Node(0);
        Node current=headNode.next;
        Node next=null;
        while(current!=null){
            next=current.next;
            current.next=reverseHead.next;
            reverseHead.next=current;
            current=next;
        }
        headNode.next=reverseHead.next;
    }

    //4. 单链表逆序打印
    public static void reverseShow(Node headNode){
        if (headNode.next==null) return;
        Stack<Node> stack=new Stack<Node>();
        Node current=headNode.next;
        while (current!=null){
            stack.push(current);
            current=current.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop().data);
        }
    }

    //5. 合并两个有序链表

}
