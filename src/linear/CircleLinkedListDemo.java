package linear;

public class CircleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(new CircleNode(7));
        circleSingleLinkedList.addNode(new CircleNode(33));
        circleSingleLinkedList.addNode(new CircleNode(53));
        circleSingleLinkedList.addNode(new CircleNode(92));
        circleSingleLinkedList.addNode(new CircleNode(21));
        circleSingleLinkedList.addNode(new CircleNode(66));
        circleSingleLinkedList.addNode(new CircleNode(37));
        circleSingleLinkedList.showList();
        System.out.println("\n删除第1个位置的元素");
        circleSingleLinkedList.deleteNode(1);
        circleSingleLinkedList.showList();
        System.out.println("\n删除第15个位置的元素");
        circleSingleLinkedList.deleteNode(15);
        circleSingleLinkedList.showList();
        System.out.println("\n删除第2个位置的元素");
        circleSingleLinkedList.deleteNode(2);
        circleSingleLinkedList.showList();
        System.out.println("\n在第3个位置后面添加元素");
        circleSingleLinkedList.insertNode(3,new CircleNode(44));
        circleSingleLinkedList.showList();
        System.out.println("\n在第1个位置后面添加元素");
        circleSingleLinkedList.insertNode(1,new CircleNode(19));
        circleSingleLinkedList.showList();
    }
}

class CircleSingleLinkedList {
    private CircleNode first = null;

    public void addNode(CircleNode node) {
        if (this.getLength() == 0) {
            first = node;
            node.next = first;
        } else if (this.getLength() == 1) {
            first.next = node;
            node.next = first;
        } else {
            CircleNode current = first;
            while (true) {
                current = current.next;
                if (current.next == first) break;
            }
            current.next = node;
            node.next = first;
        }
    }

    //根据位置，在前面插入
    public void insertNode(int position,CircleNode node) {
        if (position==1){
            CircleNode next=first.next;
            first.next=node;
            node.next=next;
            return;
        }
        CircleNode current = first;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        CircleNode next=current.next;
        current.next=node;
        node.next=next;
    }

    //根据位置删除
    public void deleteNode(int position) {
        CircleNode current = first;
        if (getLength() == 1) {
            first = null;
            return;
        }
        if (position > getLength()) {
            System.out.println("超过范围");
            return;
        }
        //默认将first向前移
        if (position == 1) {
            while (true) {
                if (current.next == first) break;
                current = current.next;
            }
            current.next = first.next;
            first = current.next;
        } else {
            for (int i = 0; i < position - 2; i++) {
                current = current.next;
            }
            CircleNode temp = current.next;
            current.next = temp.next;
        }
    }

    public int getLength() {
        CircleNode current = first;
        if (current == null) return 0;
        int length = 0;
        while (current.next != first) {
            length++;
            current = current.next;
        }
        return length + 1;
    }
    
    public void showList() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        CircleNode current = first;
        while (true) {
            System.out.printf("%d ", current.data);
            if (current.next == first) {
                break;
            }
            current = current.next;
        }
    }
}

class CircleNode {
    public int data;
    public CircleNode next;

    public CircleNode(int data) {
        this.data = data;
    }
}
