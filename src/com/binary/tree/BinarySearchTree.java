package com.binary.tree;

public class BinarySearchTree {

    public class Node{

        private Node left;
        private Node right;
        private int value;


        public Node(int value) {
            this.value = value;
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

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void insert(Node node) {
            if (node.value >this.value){
                if (right == null){
                    this.right = node;
                }else {
                    this.right.insert(node);
                }
            } else if (node.value < this.value) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.insert(node);
                }
            }
        }

        private Node search(int number) {
            if (number == this.value){
                return this;
            }
            if (number < this.value){
                if (left == null){
                    return null;
                } else {
                    return left.search(number);
                }
            }
            else {
                if (right == null){
                    return null;
                } else {
                    return right.search(number);
                }
            }
        }

        public void viewAllNodes() {
            if (left != null){
                left.viewAllNodes();
            }
            System.out.print("Value: " + value + " ");
            if (right != null){
                right.viewAllNodes();
            }
        }

        public boolean isLeaf(){
            return (left == null && right == null);
        }


        @Override
        public String toString() {
            return "Value: " + value;
        }
    }

    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node node){
        root = node;
    }

    public BinarySearchTree(int number) {
        root = new Node(number);
    }

    public void insert(Node node){
        if (root == null){
            root = node;
            return;
        }
        root.insert(node);
    }

    public void insert(int value){
        Node n = new Node(value);
        insert(n);
    }

    public Node search(int number) {
        if (root == null){
            return null;
        }
        return root.search(number);
    }

    public void viewTree(){
        if (root != null){
            root.viewAllNodes();
        }
    };

    private Node maximumValue(Node node){
        if(node.right == null){
            return node;
        }
        return maximumValue(node.getRight());
    }

    private Node minimumValue(Node node){
        if(node.left == null){
            return node;
        }
        return minimumValue(node.getLeft());
    }

    public Node father(int number){
        Node tmp = search(number);
        if (root != null){
            return father(root, tmp);
        } else {
            return null;
        }
    };

    private Node father(Node root, Node node){
        if (root.getValue() > node.getValue()){
            if(root.getLeft().getValue() != node.value){
                return father(root.left, node);
            }
            return root;
        } else if (root.getValue() < node.getValue() ){
            if(root.getRight().getValue() != node.value){
                return father(root.right, node);
            }
            return root;
        } else {
            return null;
        }
    }

    public void remove(int number) {

        remove(root, number);
    }

    private void removeRoot(){
        if (this.root.isLeaf()){
            this.root = null;
        } else if (this.root.left == null){
            this.root = this.root.right;
        } else if (this.root.right == null){
            this.root = this.root.left;
        } else {
            Node auxiliar;
            auxiliar = this.root.left;
            maximumValue(auxiliar).right = this.root.right;
            this.root = auxiliar;
        }
        return;
    }

    private void removeChild(Node father, Node root){
        if (root.isLeaf()){
            if (father.right == root) {
                father.right = null;
            } else {
                father.left = null;
            }
        } else if (father.left == null) {
            father.right = root.right;
        } else if (father.right == null) {
            father.left = root.left;
        } else {
            BinarySearchTree tree = new BinarySearchTree(root);
            tree.removeRoot();
            tree.viewTree();
            if (father.left == root) {
                father.left = tree.root;
            } if (father.right == root) {
                father.right = tree.root;
            }
        }
    }

    private void remove(Node root, int number) {
        Node father;
        if (this.root == null){
            return;
        } if (root == null){
            System.out.println("Value not found.");
        } else if (root.getValue() < number){
            remove(root.getRight(), number);
        } else if (root.getValue() > number){
            remove(root.getLeft(), number);
        } else {
            father = father(number);
            if (father == null){
                removeRoot();
                return;
            } else {
                removeChild(father, root);
                return;
            }
        }
    }
}
