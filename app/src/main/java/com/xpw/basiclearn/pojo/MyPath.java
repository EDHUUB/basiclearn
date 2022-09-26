package com.xpw.basiclearn.pojo;

import android.graphics.Path;

import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * @author sunkai
 * @date 2022/9/23 16:55
 * @read happy
 */

public class MyPath extends Path implements Serializable {


    private float preX;
    private float preY;
    private float curX;
    private float curY;

    public MyPath() {
    }

    public MyPath(@Nullable Path src) {
        super(src);
    }

    public float getPreX() {
        return preX;
    }

    public void setPreX(float preX) {
        this.preX = preX;
    }

    public float getPreY() {
        return preY;
    }

    public void setPreY(float preY) {
        this.preY = preY;
    }

    public float getCurX() {
        return curX;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public float getCurY() {
        return curY;
    }

    public void setCurY(float curY) {
        this.curY = curY;
    }
}
