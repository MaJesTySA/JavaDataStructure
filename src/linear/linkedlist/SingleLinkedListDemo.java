package linear.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        Node node1=new Node(20);
        Node node2=new Node(30);
        Node node3=new Node(50);
        SingleLinkedList linkedList=new SingleLinkedList();
        linkedList.addNode(node1);
        linkedList.addNode(node2);
        linkedList.addNode(node3);
        linkedList.showLinkedList();
        System.out.println("20元素后插入40————");
        linkedList.insertNode2(new Node(40),50);
        linkedList.showLinkedList();
        System.out.println("40元素后插入66————");
        linkedList.insertNode2(new Node(66),40);
        linkedList.showLinkedList();
        System.out.println("修改50为70————");
        linkedList.updateNode(new Node(70),50);
        linkedList.showLinkedList();
        System.out.println("删除40————");
        linkedList.deleteNode(40);
        linkedList.showLinkedList();
        System.out.println("删除—70———");
        linkedList.deleteNode(70);
        linkedList.showLinkedList();
        System.out.println(SingleLinkedListExcercise.getLength(linkedList.getHeadNode()));
    }
}

class SingleLinkedList{
    private Node head=new Node(0);
    public Node getHeadNode(){
        return head;
    }

    //添加节点
    public void addNode(Node node){
        Node temp=head;
        while(true){
            if (temp.next==null)
                break;
            temp=temp.next;
        }
        //退出while循环，temp就指向了链表最后一个节点
        temp.next=node;
    }

    //插入
    public void insertNode(Node node,int where){
        Node temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null)
                break;
            if (temp.next.data==where){
                temp=temp.next;
                if(node==temp.next){
                    throw new RuntimeException("已经添加该对象无法重复添加");
                }
                node.next=temp.next;
                temp.next=node;
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (!flag) throw new RuntimeException("没有找到相应元素");
    }

    public void insertNode2(Node node,int where){
        Node curNode=head;
        boolean flag=false;
        while (true){
            if (curNode.next==null&&curNode.data!=where){
                break;
            }
            if (curNode.data==where){
                node.next=curNode.next;
                curNode.next=node;
                flag=true;
                break;
            }
            curNode=curNode.next;
        }
        if (!flag){
            System.out.println("没有找到相关元素");
        }
    }

    //修改
    public void updateNode(Node node, int where){
        Node temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null)
                break;
            if (temp.next.data==where){
               temp.next.data=node.data;
               flag=true;
               break;
            }
            temp=temp.next;
        }
        if (!flag) throw new RuntimeException("没有找到相应元素");
    }

    //删除
    public void deleteNode(int where){
        Node temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null)
                break;
            if (temp.next.data==where){
                temp.next=temp.next.next;
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (!flag) throw new RuntimeException("没有找到相应元素");
    }

    //显示链表
    public void showLinkedList(){
        if (head.next==null){
            System.out.println("空链表");
            return;
        }
        Node temp=head.next;
        System.out.print("HEAD-->");
        while(true){
            if (temp==null)
                break;
            System.out.print(temp.data+"-->");
            temp=temp.next;
        }
        System.out.println("NULL");
    }
}

class Node{
    public int data;
    public Node next;
    public Node(int data){
        this.data=data;
    }
}
