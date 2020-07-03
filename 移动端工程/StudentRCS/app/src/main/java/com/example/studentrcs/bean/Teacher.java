package com.example.studentrcs.bean;

import org.litepal.crud.LitePalSupport;

//教师类
public class Teacher extends LitePalSupport {

    //教师号
    private int tid;

    //密码
    private int pwd;

    //教师名
    private String tname;

    public int getTid(){
        return tid;
    }

    public void setTid(int tid){
        this.tid = tid;
    }

    public int getPwd(){
        return pwd;
    }

    public void setPwd(int pwd){
        this.pwd = pwd;
    }

    public String getTname(){
        return tname;
    }

    public void setTname(String tname){
        this.tname = tname;
    }

}
