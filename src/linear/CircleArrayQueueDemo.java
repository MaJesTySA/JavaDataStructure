package linear;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleQueue circleQueue=new CircleQueue(4);
        char key=' ';
        Scanner scanner=new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加");
            System.out.println("g(get):取出");
            System.out.println("h(head):显示队头");
            key=scanner.next().charAt(0);
            switch (key){
                case 's':
                    circleQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value=scanner.nextInt();
                    circleQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res=circleQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res=circleQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程度退出");
    }
}

class CircleQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //创建队列
    public CircleQueue(int arrMaxSize){
        this.maxSize=arrMaxSize;
        arr = new int[maxSize];
        front=0;
        rear=0;
    }

    //判断是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    //判断是否空
    public boolean isEmpty(){
        return rear==front;
    }

    //入队
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，无法加入");
            return;
        }
        arr[rear]=n;
        rear=(rear+1)%maxSize;
    }

    //出队
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("空队列，无法取出");
        }
        int val=arr[front];
        front=(front+1)%maxSize;
        return val;
    }

    //显示数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("空队列");
            return;
        }
        //从front开始遍历
        for (int i = front; i < front+((rear+maxSize-front)%maxSize); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //显示队列头
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("空队列，无法取出");
        }
        return arr[front];
    }
}
