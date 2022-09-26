package com.xpw.basiclearn.controller.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.xpw.basiclearn.controller.DrawPath;
import com.xpw.basiclearn.pojo.Pointer;

import java.util.Map;

/**
 * @author sunkai
 * @date 2022/9/23 16:24
 * @read happy
 */
public class DrawPathImpl implements DrawPath {
    private final String TAG = "DrawPathImpl";
    @Override
    public void drawBg(Bitmap bgBitmap) {

    }

    @Override
    public void drawBeisiaePath(Canvas canvas, Map<Integer, Pointer> pointerMap) {
        for (Map.Entry<Integer, Pointer> map:pointerMap.entrySet()){
            canvas.drawPath(map.getValue().getMyPath(),map.getValue().getMyPaint());
        }

    }
}
