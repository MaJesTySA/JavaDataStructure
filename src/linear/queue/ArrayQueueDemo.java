package linear.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue=new ArrayQueue(3);
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
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value=scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res=arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res=arrayQueue.headQueue();
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

class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //创建队列
    public ArrayQueue(int arrMaxSize){
        this.maxSize=arrMaxSize;
        arr = new int[maxSize];
        front=-1; //指向队列头部，指向队列头的**前一个**数据
        rear=-1;  //指向队列尾部，指向最后一个的数据
    }

    //判断是否满
    public boolean isFull(){
        return rear==maxSize-1;
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
        rear++;
        arr[rear]=n;
    }

    //出队
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("空队列，无法取");
        }
        front++;
        int tmp=front;
        if(front==rear && front!=-1){
            front=-1;
            rear=-1;
        }
        return arr[tmp];
    }

    //显示数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("空队列");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列头
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("空队列，无法取");
        }
        return arr[front+1];
    }
}
