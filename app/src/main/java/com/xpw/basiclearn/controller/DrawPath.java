package com.xpw.basiclearn.controller;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.xpw.basiclearn.pojo.Pointer;

import java.util.Map;

/**
 * @author sunkai
 * @date 2022/9/23 16:23
 * @read happy
 */
public interface DrawPath {
    void drawBg(Bitmap bgBitmap);

    void drawBeisiaePath(Canvas canvas, Map<Integer, Pointer> pointerMap);
}
