package com.xpw.basiclearn.pojo;

import android.graphics.Paint;

import java.io.Serializable;


/**
 * @author sunkai
 */

public class MyPaint extends Paint implements Serializable {
    private String type;


    public MyPaint() {
    }

    public MyPaint(int flags) {
        super(flags);
    }

    public MyPaint(Paint paint) {
        super(paint);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
