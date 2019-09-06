package tree.threadedtree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(3);
        Node node2 = new Node(6);
        Node node3 = new Node(8);
        Node node4 = new Node(10);
        Node node5 = new Node(14);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.infixThreadedNodes();
        System.out.println(node4.getLeft());
        System.out.println(node4.getRight());
        System.out.println("线索化方式遍历二叉树");
        threadedBinaryTree.infixThreadedList();
    }
}

class ThreadedBinaryTree {
    private Node root;
    //保留前一个结点
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void infixThreadedNodes() {
        infixThreadedNodes(root);
    }

    public void infixThreadedNodes(Node node) {
        if (node == null)
            return;
        //先线索化左子树
        infixThreadedNodes(node.getLeft());
        //线索化当前结点
        // 先处理前驱结点
        if (node.getLeft() == null) {
            //当前结点的左指针指向前驱结点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 处理后继结点，在下次处理
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //！！！每处理一个结点后，让当前结点是下一个节点的前驱结点。pre跟着node移动
        pre = node;
        //最后线索化右子树
        infixThreadedNodes(node.getRight());
    }

    //遍历中序线索化二叉树
    public void infixThreadedList(){
        Node node=root;
        while (node!=null){
            //先找到leftType==1的节点，该节点就是中序遍历的第一个节点。
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            while (node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            //此时不等于rightType!=1，不是后继节点，则替换当前的节点
            node=node.getRight();
        }
    }
}

class Node {
    private int data;
    private Node left;
    private Node right;
    // 0 表示指向左树  1 表示前驱/后置
    private int leftType;
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public Node preOrderSearch(int value) {
        if (this.data == value)
            return this;
        Node resNode = null;
        if (this.left != null)
            resNode = this.left.preOrderSearch(value);
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode = this.right.preOrderSearch(value);
        return resNode;
    }

    public Node infixOrderSearch(int value) {
        Node resNode = null;
        if (this.left != null)
            resNode = this.left.infixOrderSearch(value);
        if (resNode != null)
            return resNode;
        if (this.data == value)
            return this;
        if (this.right != null)
            resNode = this.right.infixOrderSearch(value);
        return resNode;
    }

    public Node postOrderSearch(int value) {
        Node resNode = null;
        if (this.left != null)
            resNode = this.left.postOrderSearch(value);
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode = this.right.postOrderSearch(value);
        if (resNode != null)
            return resNode;
        if (this.data == value)
            return this;
        return resNode;
    }

    //如果是叶子结点，删除该结点
    //如果是非叶子结点，删除该子树
    public void delNode(int value) {
        if (this.left != null && this.left.data == value) {
            this.left = null;
            return;
        }
        if (this.right != null && right.data == value) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(value);
        }
        if (this.right != null) {
            this.right.delNode(value);
        }
    }
}