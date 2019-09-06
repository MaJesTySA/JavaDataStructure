package tree.arrbinarytree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree=new ArrBinaryTree(arr);
        System.out.print("前序遍历:");
        arrBinaryTree.preOrder();
        System.out.println();

        System.out.print("中序遍历:");
        arrBinaryTree.infixOrder();
        System.out.println();

        System.out.print("后序遍历:");
        arrBinaryTree.postOrder();
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * @param index 数组下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        System.out.print(arr[index]+" ");
        if ((index * 2 + 1) < arr.length)
            preOrder(index * 2 + 1);
        if ((index * 2 + 2) < arr.length)
            preOrder(index * 2 + 2);
    }

    public void preOrder(){
        preOrder(0);
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        if ((index * 2 + 1) < arr.length)
            infixOrder(index * 2 + 1);
        System.out.print(arr[index]+" ");
        if ((index * 2 + 2) < arr.length)
            infixOrder(index * 2 + 2);
    }

    public void infixOrder(){
        infixOrder(0);
    }

    public void postOrder(int index){
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        if ((index * 2 + 1) < arr.length)
            postOrder(index * 2 + 1);
        if ((index * 2 + 2) < arr.length)
            postOrder(index * 2 + 2);
        System.out.print(arr[index]+" ");
    }

    public void postOrder(){
        postOrder(0);
    }
}
