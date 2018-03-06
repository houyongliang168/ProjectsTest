package com.example.houyongliang.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by houyongliang on 2018/2/24.
 */

public class TreeTest {

    public static void main(String[] args) {

    }
//获取 好友的好友
    public List<User> getXDegreeFriend(User user,int degree){
        List<User> results = new ArrayList<User>();
        Queue<User> queue = new LinkedList<User>();
        queue.add(user);
        //用于记录已经遍历过的User，因为A是B的好友，那么B也一定是A的好友，他们互相存在于对方的friends列表中。
        HashSet<User> visited = new HashSet<User>();
        // //用一个counter记录当前的层数。
        int counter=degree;
        //      //这里结束while循环的两个条件，一个是层数，一个是queue是否为空，因为万一该当前用户压根就没有那么多层的社交网络，比如他压根就没有朋友。
        while (counter<=1&&!queue.isEmpty()){
            int queueSize=queue.size();
            for (int i = 0; i < queueSize; i++) {
                User currentUser  = queue.poll();
                //  //假如该用户已经遍历过，那么不做任何处理。
                if(!visited.contains(currentUser)){
                    results.add(currentUser);
                    queue.addAll(currentUser.getFriends());
                    visited.add(currentUser);
                }


            }
            counter--;
        }
        return results;
    }
    //迷宫问题  迷宫的最短路径问题

    public int getMinSteps(int[][] matrix){
        int row=matrix.length;
        int col=matrix[0].length;
        //迷宫可以走四个方向 ，这个二位数组代表四个方向 x与 y  的偏移量
        int[][] direction={{0,1},{1,0},{0,-1},{-1,0}};
        HashSet<Integer> visited=new HashSet<>();
        Queue<Integer> queue=new LinkedList<>();
        //把起点加入队列
        queue.add(0);
        int level=0;
            while (!queue.isEmpty()){
                //把该层的节点全部遍历
                int size=queue.size();
                for (int i = 0; i < size; i++) {
                    int current=queue.poll();
                    if(!visited.contains(current)){
                        visited.add(current);
                        //确定该点 x 与 y 的坐标  正方形的
                        int currentX=current /col;
                        int currentY=current % col;
                        //假如该点是终点，那么直接返回level
                        if (currentX == matrix.length - 1 && currentY == matrix[0].length - 1) {
                            return level;
                        }
                        //如果不是 那么我们分别把它的四个方向的节点都尝试加入到队列尾端，也就是下一层中
                        for (int j = 0; j < direction.length; j++) {
                            int tempX=currentX+direction[j][0];
                            int tempY=currentY+direction[j][1];
                            //因为1代表墙壁，我们不能走，只能加数值为0的点
                            if(matrix[tempX][tempY]!=1){
                                int code=tempX*col+tempY;
                                queue.add(code);
                            }
                        }

                    }
                }
                level++;
            }
            return -1;
    }

}
