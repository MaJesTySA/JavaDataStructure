package linear.array;

public class SparseArray {
    public static void main(String[] args) {
        //建立数组
        int chessArr1[][]=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        //输出数组
        for (int[] row : chessArr1) {
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //二维数组 → 稀疏数组
        //1. 遍历
        int sum=0;
        for (int i = 0; i <11 ; i++) {
            for (int j=0;j<11;j++){
                if(chessArr1[i][j]!=0)
                    sum++;
            }
        }
        System.out.println("sum="+sum);

        //2. 创建稀疏数组
        int[][] sparseArr=new int[sum+1][3];
        // 稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        // 遍历二维数据，存放到稀疏数组
        int count=0;  //用于记录是第几个非0数据。
        for (int i = 0; i <11 ; i++) {
            for (int j=0;j<11;j++){
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        System.out.println();
        System.out.println("稀疏数组——————");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        // 稀疏数组恢复成原始二维数组
        int[][] chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        for (int[] row : chessArr1) {
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
