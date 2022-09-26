package com.xpw.basiclearn.controller;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.xpw.basiclearn.pojo.MyPaint;
import com.xpw.basiclearn.pojo.MyPath;
import com.xpw.basiclearn.pojo.Pointer;

import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @date 2022/9/23 17:32
 * @read happy
 */
public interface RecordPath {
    void setPath(MotionEvent event, MyPath myPath, MyPaint myPaint, Pointer pointer, Map<Integer, Pointer> pointerMap);

    void changePath(MotionEvent event, Map<Integer, Pointer> pointerMap);

    void savePath(MotionEvent event, Map<Integer, Pointer> pointerMap, List<MyPaint> myPaintList, List<MyPath> myPathList, Bitmap drawBitmap);

    void removePath(MotionEvent event, Map<Integer, Pointer> pointerMap);

    void clear(Map<Integer, Pointer> pointerMap);
}
