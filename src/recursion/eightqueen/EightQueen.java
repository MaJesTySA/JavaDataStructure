package recursion.eightqueen;

public class EightQueen {
    private static int max=8;
    private static int[] array =new int[max];
    private static int count=0;
    public static void main(String[] args) {
        placeQueen(0);
        System.out.println(count);
    }

    //放置第n个皇后
    private static void placeQueen(int n){
        if (n==max){
            show();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //把皇后n，放到该行第一列（第一个皇后n=1，放在第一行，第二个皇后n=2，放在第二行。
            array[n]=i;
            //判断是否冲突
            if (isConflict(n)){
                placeQueen(n+1);
            }
            //如果冲突，放到下一列。
        }
    }

    private static boolean isConflict(int n){
        for (int i = 0; i < n; i++) {
            if (array[i]== array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    private static void show(){
        for (int i = 0; i <max; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
        count++;
    }
}
