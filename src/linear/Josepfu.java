package linear;

public class Josepfu {
    public static void main(String[] args) {
        JosepfuLinkedList linkedList = new JosepfuLinkedList();
        linkedList.addBoy(5);
        linkedList.showBoy();
        linkedList.countBoy(1,2,5);
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

class JosepfuLinkedList {
    private Boy first = new Boy(-1);

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums不正确");
            return;
        }
        Boy currentBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                //构成环
                first = boy;
                first.setNext(first);
                currentBoy = first;
            } else {
                currentBoy.setNext(boy);
                boy.setNext(first);
                currentBoy = boy;
            }
        }
    }

    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy currentBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", currentBoy.getNo());
            if (currentBoy.getNext() == first) break;
            currentBoy = currentBoy.getNext();
        }
    }

    /**
     * @param startNo  从第几个小孩开始
     * @param countNum 数几下
     * @param nums     有多少个小孩
     **/
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums || countNum > nums) {
            System.out.println("数据非法，重新输入");
            return;
        }
        //创建辅助指针，帮助出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) break;
            helper = helper.getNext();
        }
        //定位first和helper的位置到startNo
        for (int i = 0; i < startNo - 1; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        //开始报数，循环让first和helper同时移动m-1次，然后出圈，直到只剩下一个Boy
        while (true){
            //只有一人
            if (helper==first) break;
            for (int i = 0; i <countNum-1; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //此时first指向的节点，需要出圈
            System.out.printf("小孩%d出圈\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("剩下圈中的小孩是%d \n",first.getNo());
    }
}
