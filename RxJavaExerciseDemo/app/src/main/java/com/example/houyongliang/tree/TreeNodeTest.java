package com.example.houyongliang.tree;

/**
 * Created by houyongliang on 2018/2/24.
 */

import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树节点
 */
public class TreeNodeTest {


    public class TreeNode {
        TreeNode left;
        TreeNode right;
        TreeNode next;
        int value;

    }

    /**
     * 中序
     */
    public void printInoderTree(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        //递归调用printTree
        printInoderTree(root.left);
        System.out.println(root.value);
        printInoderTree(root.right);
    }

    /**
     * 中序
     */
    public void printProrderTree(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        //递归调用printTree
        System.out.println(root.value);
        printInoderTree(root.left);
        printInoderTree(root.right);
    }

    /**
     * 翻转二叉树
     */
    public TreeNode reverseBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        //把左子树翻转
        TreeNode left = reverseBinaryTree(root.left);
        TreeNode right = reverseBinaryTree(root.right);
        //把左右字树分别赋值给 root 节点，但是是翻转过来的顺序
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 把二叉树铺平
     */
    public TreeNode flatten(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            //用递归的意思，把左右先铺平
            TreeNode left = flatten(root.left);
            TreeNode right = flatten(root.right);
            //把左指针和有指针指向空
            root.left = null;
            root.right = null;
            //假如左子树 生成的链表为空 ，那么忽略它，把右子树 生成的链表指向根节点的 右指针
            if (left == null) {
                root.right = right;
                return root;
            }
            //如果左子树 生成的链表不为空，那么用 while 循环获取 最后一个节点，并且他的右指针要指向右子树生成的链表的头节点
            root.right = left;
            TreeNode lastLeft = left;
            while (lastLeft != null && lastLeft.right != null) {
                lastLeft = lastLeft.right;
            }
            lastLeft.right = right;
            return root;

        }
    }

    //view 的 findviewbyid() 运行思路
    // 返回一个在vg下面的一个View，id为方法的第二个参数
    public static View find(ViewGroup vg, int id) {
        if (vg == null) return null;
        int size = vg.getChildCount();
        //循环遍历所有孩子
        for (int i = 0; i < size; i++) {
            View v = vg.getChildAt(i);
            //如果当前孩子的id相同，那么返回
            if (v.getId() == id) return v;
            //如果当前孩子id不同，但是是一个ViewGroup，那么我们递归往下找
            if (v instanceof ViewGroup) {
                //递归
                View temp = find((ViewGroup) v, id);
                //如果找到了，就返回temp，如果没有找到，继续当前的for循环
                if (temp != null) {
                    return temp;
                }
            }
        }
        //到最后还没用找到，代表该ViewGroup vg 并不包含一个有该id的孩子，返回空
        return null;
    }

    /**
     * 二叉树的层序处理(广度优先)
     */
    public void printTree(TreeNode root){
        if (root==null){
            return ;
        }
        //linkedList 是链表结构  arraylist 是数组结构
        Queue<TreeNode> queue=new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode current=queue.poll();
            System.out.println(current.toString());
            if(current.left!=null){
                queue.add(current.left);
            }
            if(current.right!=null){
                queue.add(current.right);
            }
        }

    }
    /**
     * 链接二叉树的Next节点  .next 有疑惑
     */
    public void nextSibiling(TreeNode node){
        if(node==null){
            return;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(node);
        //这个level 没有实际用用处，但是可以告诉大家怎么判断当前 node 是第几层
        int level=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            //用这个for 循环，可以保证for 循环里面对queue 不管加多少个子节点，我只处理当前层节里面的节点
            for (int i = 0; i < size; i++) {
                //把当前第一个节点拿出来
                TreeNode current=queue.poll();
                //把子节点加到 queue 里面
                if(current.left!=null){
                    queue.add(current.left);
                }
                if(current.right!=null){
                    queue.add(current.right);
                }
                if(i!=size-1){
                    //peek 只是提取当前队列中的第一个节点，但是并不把他从队列中拿出来
//                    current.next=  queue.peek();
                    current.right=  queue.peek();
                }
            }
            level++;
        }
    }
}
