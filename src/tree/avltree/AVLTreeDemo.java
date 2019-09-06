package tree.avltree;

public class AVLTreeDemo {
    public static void main(String[] args) {

        //int[] arr={4,3,6,5,7,8}; //左旋
        int[] arr={10,11,7,6,8,9}; //双旋
        AVLTree avlTree=new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("未平衡前的总高度："+avlTree.getRoot().getHeight());
        System.out.println("未平衡前的左高度："+avlTree.getRoot().getLeftHeight());
        System.out.println("未平衡前的右高度："+avlTree.getRoot().getRightHeight());
        System.out.println("未平衡前中序遍历：");
        avlTree.infixOrder();
        System.out.println("=================");
        AVLTree avlTreeWithAVL=new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTreeWithAVL.addWithAVL(new Node(arr[i]));
        }
        System.out.println("旋转后的总高度："+avlTreeWithAVL.getRoot().getHeight());
        System.out.println("旋后的左高度："+avlTreeWithAVL.getRoot().getLeftHeight());
        System.out.println("旋后的右高度:"+avlTreeWithAVL.getRoot().getRightHeight());
        System.out.println("旋后中序遍历:");
        avlTreeWithAVL.infixOrder();
        System.out.println(avlTreeWithAVL.getRoot().left);
        System.out.println(avlTreeWithAVL.getRoot().right);
    }
}

class AVLTree {
    private Node root;

    public Node getRoot(){
        return root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void addWithAVL(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addWithAVL(node);
        }
    }



    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树");
        }
    }

    public Node search(int value) {
        if (root == null)
            return null;
        else
            return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent(value);
    }

    //返回以node为根节点的二叉排序树的最小节点值
    //并删除node。
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找左节点，就会找到最小值。
        while (target.left != null) {
            target = target.left;
        }
        //此时target指向了最小节点
        //删除最小节点
        del(target.value);
        return target.value;
    }

    public void del(int value) {
        if (root == null)
            return;
        else {
            //要删除的是根节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node targetNode = search(value);
            //找不到需要删除的值
            if (targetNode == null)
                return;
            //找到父节点
            Node parentNode = searchParent(value);
            //如果删除节点满足叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断目标节点是父节点的左节点还是右节点
                if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == targetNode.value) {
                    parentNode.right = null;
                }
                //删除节点有两颗子树
            } else if (targetNode.left != null && targetNode.right != null) {
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }
            //删除节点有一颗子树
            else {
                //如果删除节点有左子节点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        //如果删除节点是父节点的左节点
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.left;
                        }
                        //如果删除节点是父节点的右节点
                        else {
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                }
                //如果删除节点有右子节点
                else {
                    if (parentNode != null) {
                        //如果删除节点是父节点的左节点
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.right;
                        }
                        //如果删除节点是父节点的右节点
                        else {
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getLeftHeight() {
        if (left == null)
            return 0;
        return left.getHeight();
    }

    public int getRightHeight() {
        if (right == null)
            return 0;
        return right.getHeight();
    }

    //返回以当前节点为根节点的树的高度
    public int getHeight() {
        return Math.max(left == null ? 0 : left.getHeight(), right == null ? 0 : right.getHeight()) + 1;
    }

    //递归添加
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {
            //比当前结点大或者相等
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void addWithAVL(Node node) {
        add(node);
        //当添加完节点后，如果不平衡，则进行平衡操作。
        //右子树大于左子树，执行左旋。
        if (getRightHeight()-getLeftHeight()>1){
            //如果右子树的左子树比右子树高，则需要先对右子树右旋，再左旋
            if (right!=null && right.getLeftHeight()>right.getRightHeight())
            {
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        //左子树大于右子树，执行右旋
        if (getLeftHeight()-getRightHeight()>1){
            //如果左子树的右子树比左子树高，则需要先对左子树左旋，再右旋。
            if (left!=null && left.getRightHeight()>left.getLeftHeight()){
                left.leftRotate();
            }
            rightRotate();
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    //AVL左旋
    private void leftRotate(){
        Node newNode = new Node(value);
        //把新节点的左子树设置成当前节点的左子树
        newNode.left=this.left;
        //把新节点的右子树设置成当前节点的右子树的左子树
        newNode.right=this.right.left;
        //把当前节点的值替换成右在节点的值
        this.value=this.right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        this.right=this.right.right;
        //把当前节点的左子树设置成新的节点
        this.left=newNode;
    }

    private void rightRotate(){
        Node newNode=new Node(value);
        newNode.right=this.right;
        newNode.left=this.left.right;
        this.value=this.left.value;
        this.left=this.left.left;
        this.right=newNode;
    }
}
