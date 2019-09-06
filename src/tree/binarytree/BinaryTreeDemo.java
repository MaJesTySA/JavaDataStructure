package tree.binarytree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
        System.out.println("前序查找");
        System.out.println(binaryTree.preOrderSearch(2));
        System.out.println("中序查找");
        System.out.println(binaryTree.infixOrderSearch(2));
        System.out.println("后序查找");
        System.out.println(binaryTree.postOrderSearch(2));
        System.out.println("删除结点2");
        binaryTree.delNode(7);
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null)
            this.root.preOrder();
    }

    public void infixOrder() {
        if (this.root != null)
            this.root.infixOrder();
    }

    public void postOrder() {
        if (this.root != null)
            this.root.postOrder();
    }

    public Node preOrderSearch(int value){
        if (root!=null)
            return root.preOrderSearch(value);
        else
            return null;
    }

    public Node infixOrderSearch(int value){
        if (root!=null)
            return root.infixOrderSearch(value);
        else
            return null;
    }

    public Node postOrderSearch(int value){
        if (root!=null)
            return root.postOrderSearch(value);
        else
            return null;
    }

    public void delNode(int value){
        if (root!=null){
            //root是不是需要删除的结点
            if (root.getData()==value){
                root=null;
            }else{
                root.delNode(value);
            }
        }else {
            System.out.println("空树不能删除");
        }
    }

}

class Node {
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
        ;
    }

    public void postOrder() {
        if (this.left != null)
            this.left.postOrder();
        if (this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    public Node preOrderSearch(int value){
        if (this.data==value)
            return this;
        Node resNode=null;
        if (this.left!=null)
            resNode=this.left.preOrderSearch(value);
        if (resNode!=null)
            return resNode;
        if (this.right!=null)
            resNode=this.right.preOrderSearch(value);
        return resNode;
    }

    public Node infixOrderSearch(int value){
        Node resNode=null;
        if (this.left!=null)
            resNode=this.left.infixOrderSearch(value);
        if (resNode!=null)
            return resNode;
        if (this.data==value)
            return this;
        if (this.right!=null)
            resNode=this.right.infixOrderSearch(value);
        return resNode;
    }

    public Node postOrderSearch(int value){
        Node resNode=null;
        if (this.left!=null)
            resNode=this.left.postOrderSearch(value);
        if (resNode!=null)
            return resNode;
        if (this.right!=null)
            resNode=this.right.postOrderSearch(value);
        if (resNode!=null)
            return resNode;
        if (this.data==value)
            return this;
        return resNode;
    }

    //如果是叶子结点，删除该结点
    //如果是非叶子结点，删除该子树
    public void delNode(int value){
        if (this.left!=null&&this.left.data==value) {
            this.left=null;
            return;
        }
        if (this.right!=null && right.data==value){
            this.right=null;
            return;
        }
        if (this.left!=null){
            this.left.delNode(value);
        }
        if (this.right!=null){
            this.right.delNode(value);
        }
    }

}
