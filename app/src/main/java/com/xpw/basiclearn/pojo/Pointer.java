package com.xpw.basiclearn.pojo;

import java.io.Serializable;

/**
 * @author sunkai
 * @date 2022/9/23 16:23
 * @read happy
 */
public class Pointer implements Serializable {
    private MyPath myPath;
    private MyPaint myPaint;

    public Pointer() {
//        myPaint = new MyPaint();
//        myPath = new MyPath();
        super();
    }

    public Pointer(MyPath myPath, MyPaint myPaint) {
        this.myPath = myPath;
        this.myPaint = myPaint;
    }

    public Pointer(Pointer pointer) {
        this.myPath=pointer.getMyPath();
        this.myPaint=pointer.getMyPaint();
    }

    public void init(MyPath myPath, MyPaint myPaint) {
        this.myPath = myPath;
        this.myPaint = myPaint;
    }

    public MyPath getMyPath() {
        return myPath;
    }

    public void setMyPath(MyPath myPath) {
        this.myPath = myPath;
    }

    public MyPaint getMyPaint() {
        return myPaint;
    }

    public void setMyPaint(MyPaint myPaint) {
        this.myPaint = myPaint;
    }
}
