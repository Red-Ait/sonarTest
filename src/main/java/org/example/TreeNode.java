package org.example;

public class TreeNode {
    int val;
    org.example.TreeNode left;
    org.example.TreeNode right;
    TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, org.example.TreeNode left, org.example.TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
