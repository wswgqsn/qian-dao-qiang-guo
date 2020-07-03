package com.example.studentrcs.bean;

import org.litepal.crud.LitePalSupport;

//学生类
public class Student extends LitePalSupport {

    //学号
    private int sid;

    //姓名
    private String sname;

    //图片路径
    private int path;

    //状态
    private int statu;

    //姓密码
    private int pwd;

    public int getSid(){
        return sid;
    }

    public void setSid(int sid){
        this.sid = sid;
    }

    public String sname(){
        return sname;
    }

    public void setSname(String sname){
        this.sname = sname;
    }

    public int getPath(){
        return path;
    }

    public void setPath(int path){
        this.path = path;
    }

    public int getStatu(){
        return statu;
    }

    public void setStatu(int statu){
        this.statu = statu;
    }

    public int getPwd(){
        return pwd;
    }

    public void setPwd(int pwd){
        this.pwd = pwd;
    }

}
