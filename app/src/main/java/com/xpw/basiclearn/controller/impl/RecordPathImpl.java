package com.xpw.basiclearn.controller.impl;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.xpw.basiclearn.controller.RecordPath;
import com.xpw.basiclearn.pojo.MyPaint;
import com.xpw.basiclearn.pojo.MyPath;
import com.xpw.basiclearn.pojo.Pointer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 */
public class RecordPathImpl implements RecordPath {
    @Override
    public void setPath(MotionEvent event, MyPath myPath, MyPaint myPaint, Pointer pointer, Map<Integer, Pointer> pointerMap) {
        if (pointerMap!=null){
            synchronized (pointerMap) {
                int count = event.getPointerCount();
                if (!pointerMap.isEmpty()) {
                    int i =0;
                    for (Map.Entry<Integer, Pointer> map : pointerMap.entrySet()) {
                        map.getValue().getMyPath().quadTo(
                                map.getValue().getMyPath().getPreX(),
                                map.getValue().getMyPath().getPreY(),
                                (event.getX(i) + map.getValue().getMyPath().getPreX()) / 2,
                                (event.getY(i) + map.getValue().getMyPath().getPreY()) / 2);
                        map.getValue().getMyPath().setPreX(event.getX(i));
                        map.getValue().getMyPath().setPreY(event.getY(i));
                        i++;
                    }
                }
                for (int i = 0; i < count; i++) {
                    if (!pointerMap.containsKey(event.getPointerId(i))) {

                        myPaint = new MyPaint();
                        myPath = new MyPath();

                        myPath.setPreX(event.getX(i));
                        myPath.setPreY(event.getY(i));
                        myPath.moveTo(event.getX(i), event.getY(i));
                        myPaint.setAlpha(1);
                        myPaint.setColor(Color.WHITE);
                        myPaint.setAntiAlias(true);
                        myPaint.setStyle(Paint.Style.STROKE);

                        pointer = new Pointer();
                        pointer.init(myPath, myPaint);
                        pointerMap.put(i, pointer);
                    }
                }
            }
            }

    }

    @Override
    public void changePath(MotionEvent event, Map<Integer, Pointer> pointerMap) {
        synchronized (pointerMap){
            if (!pointerMap.isEmpty()) {
                int i =0;
                for (Map.Entry<Integer, Pointer> map : pointerMap.entrySet()) {
                    map.getValue().getMyPath().quadTo(
                            map.getValue().getMyPath().getPreX(),
                            map.getValue().getMyPath().getPreY(),
                            (event.getX(i) + map.getValue().getMyPath().getPreX()) / 2,
                            (event.getY(i) + map.getValue().getMyPath().getPreY()) / 2);
                    map.getValue().getMyPath().setPreX(event.getX(i));
                    map.getValue().getMyPath().setPreY(event.getY(i));
                    i++;
                }
            }
        }



    }

    @Override
    public void savePath(MotionEvent event, Map<Integer, Pointer> pointerMap, List<MyPaint> myPaintList, List<MyPath> myPathList, Bitmap drawBitmap) {

    }

    @Override
    public void removePath(MotionEvent event, Map<Integer, Pointer> pointerMap) {
       synchronized (pointerMap){
           pointerMap.remove(event.getPointerId(event.getActionIndex()));
           Log.d(TAG, "removePath: "+event.getPointerId(event.getActionIndex()));
           Log.d(TAG, "removePath: pointerMap "+pointerMap.toString());
       }
    }

    @Override
    public void clear(Map<Integer, Pointer> pointerMap) {
        pointerMap.clear();
    }

}
