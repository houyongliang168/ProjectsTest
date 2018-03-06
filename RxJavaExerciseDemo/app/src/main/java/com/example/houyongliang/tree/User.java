package com.example.houyongliang.tree;

import java.util.Collections;
import java.util.List;

/**
 * Created by houyongliang on 2018/2/24.
 * 测试 二叉树
 */

public class User {
    //这个friends是该用户的直接好友，也就是一度好友、
    private List<User> friends;
    private String name;

    //获取好友列表
    public List<User> getFriends(){
        return Collections.unmodifiableList(friends);
    }

    public String getUserName(){
        return name;
    }


}
