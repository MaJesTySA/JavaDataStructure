package linear;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList linkedList=new DoubleLinkedList();
        linkedList.addNode(new DoubleNode(10));
        linkedList.addNode(new DoubleNode(20));
        linkedList.addNode(new DoubleNode(30));
        linkedList.addNode(new DoubleNode(40));
        linkedList.addNode(new DoubleNode(50));
        linkedList.addNode(new DoubleNode(60));
        linkedList.addNode(new DoubleNode(70));
        linkedList.addNode(new DoubleNode(80));
        linkedList.showLinkedList();
        System.out.println("修改——将20修改成22");
        linkedList.updateNode(new DoubleNode(22),20);
        linkedList.showLinkedList();
        System.out.println("删除——删除60");
        linkedList.deleteNode(60);
        linkedList.showLinkedList();
        System.out.println("删除——删除80");
        linkedList.deleteNode(80);
        linkedList.showLinkedList();
        System.out.println("插入——在30后面插入33");
        linkedList.insertNode(new DoubleNode(33),30);
        linkedList.showLinkedList();
        System.out.println("插入——在50后面插入55");
        linkedList.insertNode(new DoubleNode(55),50);
        linkedList.showLinkedList();

    }
}

class DoubleLinkedList{
    private DoubleNode head=new DoubleNode(0);
    public DoubleNode getHeadNode(){
        return head;
    }

    //添加节点到最后
    public void addNode(DoubleNode node){
        DoubleNode temp=head;
        while(true){
            if (temp.next==null)
                break;
            temp=temp.next;
        }
        //退出while循环，temp就指向了链表最后一个节点
        temp.next=node;
        node.pre=temp;
    }

    //插入
    public void insertNode(DoubleNode node,int where){
        DoubleNode temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null)
                break;
            if (temp.data==where){
                node.next=temp.next;
                temp.next=node;
                node.next.pre=node;
                node.pre=temp;
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (!flag) throw new RuntimeException("没有找到相应元素");
    }

    //修改
    public void updateNode(DoubleNode node, int where){
        DoubleNode temp=head;
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
        if (head.next==null) throw new RuntimeException("链表为空，不能删除");
        DoubleNode temp=head;
        boolean flag=false;
        while(true){
            if(temp==null)
                break;
            if (temp.data==where){
                temp.pre.next=temp.next;
                //最后一个节点要单独处理
                if (temp.next!=null){
                    temp.next.pre=temp.pre;
                }
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
        DoubleNode temp=head.next;
        while(true){
            if (temp==null)
                break;
            System.out.println(temp.data);
            temp=temp.next;
        }
    }
}

class DoubleNode{
    public int data;
    public DoubleNode next;
    public DoubleNode pre;
    public DoubleNode(int data){
        this.data=data;
    }
}
