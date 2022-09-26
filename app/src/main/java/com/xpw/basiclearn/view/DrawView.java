package com.xpw.basiclearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.xpw.basiclearn.controller.DrawPath;
import com.xpw.basiclearn.controller.RecordPath;
import com.xpw.basiclearn.controller.impl.DrawPathImpl;
import com.xpw.basiclearn.controller.impl.RecordPathImpl;
import com.xpw.basiclearn.pojo.MyPaint;
import com.xpw.basiclearn.pojo.MyPath;
import com.xpw.basiclearn.pojo.Pointer;
import com.xpw.basiclearn.until.CloneUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DrawView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener, Runnable {

    private static final String TAG = "DrawView";
    private String originalJsonStr;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Bitmap bgBitmap;
    private Bitmap drawBitmap;
    private MyPaint myPaint;
    private MyPath myPath;
    private Pointer pointer;
    private List<MyPaint> myPaintList;
    private List<MyPath> myPathList;
    private Map<Integer, Pointer> pointerMap;
    private RecordPath recordPathControl;
    private DrawPath drawPathControl;
    private Boolean isDrawing;
    private Boolean isSetBg;
    private Map<Integer, Pointer> pointerMapCache;
    private Bitmap bitmapCache;



    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setOnTouchListener(this);

    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        isDrawing = true;
        initView();
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        recordPath(v, event);
        return true;
    }

    @Override
    public void run() {
        while (isDrawing) {
            drawPath();
        }
    }

    private void recordPath(View v, MotionEvent event) {

        switch (event.getAction() & event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                recordPathControl.setPath(event, myPath, myPaint, pointer, pointerMap);
                break;
            case MotionEvent.ACTION_MOVE:
                recordPathControl.changePath(event, pointerMap);
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                recordPathControl.savePath(event, pointerMap, myPaintList, myPathList, drawBitmap);
                recordPathControl.removePath(event, pointerMap);
                break;
        }

    }

    private void initView() {
        surfaceHolder = getHolder();
        canvas = getHolder().lockCanvas();
        bgBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        drawBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        pointerMap = new HashMap<>();
        pointerMapCache = new HashMap<>();
        //todo myPaint myPath
        myPaintList = new ArrayList<>();
        myPathList = new ArrayList<>();
        recordPathControl = new RecordPathImpl();
        drawPathControl = new DrawPathImpl();
        isSetBg = false;
        surfaceHolder.unlockCanvasAndPost(canvas);


    }

    private void drawPath() {
        try {
            canvas = surfaceHolder.lockCanvas();
            if (!pointerMap.isEmpty()&&pointerMap!=null) {
                synchronized (pointerMap){
                    for (Integer i : pointerMap.keySet()) {
                        Pointer pointerTemp = new Pointer(pointerMap.get(i));
                        pointerMapCache.put(i, pointerTemp);
                    }

                }

//                pointerMapCache = CloneUtil.clone(pointerMap);
//                Log.d(TAG, "drawPath:pointerMap "+pointerMap.toString());
//                Log.d(TAG, "drawPath:pointerMapCache "+pointerMapCache.toString());
//                if (pointerMapCache.get(0).getMyPath().isEmpty()){
//                    Log.d(TAG, "drawPath: 23232323232323232323");
//                }
            }
            Log.d(TAG, "drawPath: pointerMapCache "+ pointerMapCache.toString());
//            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            drawPathControl.drawBeisiaePath(canvas, pointerMapCache );


//            pointerMapCache = new HashMap<>();

//            if (!pointerMap.isEmpty()) {
//                if (pointerMapCache == null) {
//                    pointerMapCache = new HashMap<>();
//                    for (Integer i : pointerMap.keySet()) {
//                        Pointer pointerTemp = new Pointer(pointerMap.get(i));
//                        pointerMapCache.put(i, pointerTemp);
//                        Log.d(TAG, "drawPath:pointerMapCache " + i + "====" + pointerMapCache.toString());
//                    }
//                } else {
//                    for (Integer i : pointerMap.keySet()) {
//                        Pointer pointerTemp = new Pointer(pointerMap.get(i));
//                        pointerMapCache.put(i, pointerTemp);
//                        Log.d(TAG, "drawPath:pointerMapCache " + i + "====" + pointerMapCache.toString());
//                    }
//                }
//                drawPathControl.drawBeisiaePath(canvas, pointerMapCache);
//            }


        } catch (Exception e) {
            Log.d(TAG, "drawPath: " + e);
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

    }


}
