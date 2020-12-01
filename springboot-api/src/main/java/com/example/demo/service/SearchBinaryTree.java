package com.example.demo.service;

/**
 * Created by fb on 2020/7/6
 */
public class SearchBinaryTree {

        //根结点
        public TreeNode root;

        public class TreeNode {
                public int index;
                public int data;
                public TreeNode leftChild;
                public TreeNode rightChild;
                public TreeNode parent;// 父节点

                public TreeNode(int index, int data) {
                        this.data = data;
                }
        }

        /**
         * 二叉树左边孩子都比根结点小，右边孩子都比根结点大
         *
         */
        public TreeNode buildSearchTree(int index, int data) {
                TreeNode node = null;
                TreeNode parentNode = null;
                if (root == null) {
                        node = new TreeNode(index, data);
                        root = node;
                }

                node = root;
                // 查找合适的位置
                while (node != null) {
                        parentNode = node;
                        if (node.data > data) {
                                node = node.leftChild;
                        } else if (node.data < data) {
                                node = node.rightChild;
                        } else {
                                return node;
                        }
                }
                //退出while循环时，node为null需要创建
                node = new TreeNode(index, data);
                if (parentNode.data > data) {
                        parentNode.leftChild = node;
                } else if (parentNode.data < data) {
                        parentNode.rightChild = node;
                }
                node.parent = parentNode;
                return node;
        }

        /**
         * 中序遍历二叉树
         * @param node
         */
        public void print(TreeNode node) {
                if (node == null) {
                        return;
                }
                print(node.leftChild);
                System.out.print("　"+node.data);
                print(node.rightChild);

        }

        public static void main(String[] args) {
                SearchBinaryTree binaryTree = new SearchBinaryTree();
                int[] array = { 3, 2, 6, 12, 5, 1, 28, 20, 30, 15 };
                for (int i = 0; i < array.length; i++) {
                        binaryTree.buildSearchTree(i, array[i]);
                }

                binaryTree.print(binaryTree.root);
        }
}
