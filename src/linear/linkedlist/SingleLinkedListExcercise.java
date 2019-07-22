package linear.linkedlist;

import java.util.Stack;

public class SingleLinkedListExcercise {

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addNode(new Node(10));
        linkedList.addNode(new Node(20));
        linkedList.addNode(new Node(30));
        linkedList.addNode(new Node(40));
        linkedList.addNode(new Node(50));
        linkedList.addNode(new Node(60));
        linkedList.addNode(new Node(70));
        linkedList.addNode(new Node(80));
        linkedList.addNode(new Node(90));
        linkedList.addNode(new Node(100));
        System.out.print("链表长度：");
        System.out.print(getLength(linkedList.getHeadNode())+"\n");
        System.out.print("倒数第五个链表元素：");
        System.out.print((findLastNodeOf(linkedList.getHeadNode(),5)).data+"\n");
        System.out.print("逆序打印：");
        reverseShow(linkedList.getHeadNode());
        System.out.print("链表反转：");
        reverse_1(linkedList);
        linkedList.showLinkedList();
        System.out.println("合并两个有序链表：");
        mergeTest();
    }

    private static void mergeTest() {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addNode(new Node(10));
        linkedList.addNode(new Node(11));
        linkedList.addNode(new Node(38));
        linkedList.addNode(new Node(89));
        System.out.print("有序链表1：");
        linkedList.showLinkedList();
        SingleLinkedList linkedList1=new SingleLinkedList();
        linkedList1.addNode(new Node(11));
        linkedList1.addNode(new Node(15));
        linkedList1.addNode(new Node(16));
        linkedList1.addNode(new Node(19));
        System.out.print("有序链表2：");
        linkedList1.showLinkedList();
        System.out.println("合并后的链表");
        merge(linkedList,linkedList1);
        linkedList.showLinkedList();
    }

    //1. 求出单链表有效节点个数（不含头节点）
    public static int getLength(Node headNode) {
        if (headNode.next == null) return 0;
        int length = 0;
        Node current = headNode.next;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    //2. 查找单链表的倒数第k个节点
    //  先从头遍历，得到总长度，
    //  从第1个开始遍历，遍历length-index个。
    public static Node findLastNodeOf(Node headNode, int index) {
        if (headNode.next == null) return null;
        int length = getLength(headNode);
        if (index <= 0 || index > length) return null;
        int count = 0;
        Node current = headNode.next;
        while (count < (length - index)) {
            current = current.next;
            count++;
        }
        return current;
    }

    //3. 单链表反转
    public static void reverse_1(SingleLinkedList linkedList) {
        Node headNode = linkedList.getHeadNode();
        if (headNode == null || headNode.next == null) return;
        Node reverseHead = new Node(0);
        Node current = headNode.next;
        Node next;
        while (current != null) {
            next = current.next;
            current.next = reverseHead.next;
            reverseHead.next = current;
            current = next;
        }
        headNode.next = reverseHead.next;
    }

    //4. 单链表逆序打印
    public static void reverseShow(Node headNode) {
        if (headNode.next == null) return;
        Stack<Node> stack = new Stack<Node>();
        Node current = headNode.next;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        System.out.print("HEAD-->");
        while (stack.size() > 0) {
            System.out.print(stack.pop().data+"-->");
        }
        System.out.print("NULL\n");
    }

    //5. 合并两个有序链表
    public static void merge(SingleLinkedList list,SingleLinkedList list2) {
        Node mergedCur=list.getHeadNode().next;
        Node cur=list2.getHeadNode().next;
        while (cur!=null)
        {
            Node next=cur.next;
            while (mergedCur.next.data<cur.data){
                mergedCur=mergedCur.next;
            }
            cur.next=mergedCur.next;
            mergedCur.next=cur;
            cur=next;
        }
    }
}
