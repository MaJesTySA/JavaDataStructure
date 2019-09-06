package tree.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 5, 20, 4, 3};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();
        binarySortTree.del(5);
        binarySortTree.infixOrder();

    }
}

class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
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
}
