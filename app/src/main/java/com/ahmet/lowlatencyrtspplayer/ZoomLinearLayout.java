package com.ahmet.lowlatencyrtspplayer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Seymur on 4/23/2018.
 */
public class ZoomLinearLayout extends LinearLayout implements ScaleGestureDetector.OnScaleGestureListener {

    private enum Mode {
        NONE,
        DRAG,
        ZOOM
    }

    Context context;

    private static final float MIN_ZOOM = 1.0f;
    private static final float MAX_ZOOM = 4.0f;

    private Mode mode = Mode.NONE;
    private float scale = 1.0f;
    private float lastScaleFactor = 0f;

    private float startX = 0f;
    private float startY = 0f;

    private float dx = 0f;
    private float dy = 0f;
    private float prevDx = 0f;
    private float prevDy = 0f;

    private boolean canZoom = false;

    public ZoomLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public ZoomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZoomLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setCanZoom(boolean canZoom){
        this.canZoom = canZoom;
        if(!this.canZoom)
            resetScaleAndTranslation();
    }
    public boolean getCanZoom(){
        return this.canZoom;
    }
    @SuppressLint("ClickableViewAccessibility")
    public void init(Context context) {
        final ScaleGestureDetector scaleDetector = new ScaleGestureDetector(context, this);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (scale > MIN_ZOOM) {
                            mode = Mode.DRAG;
                            startX = motionEvent.getX() - prevDx;
                            startY = motionEvent.getY() - prevDy;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == Mode.DRAG) {
                            dx = motionEvent.getX() - startX;
                            dy = motionEvent.getY() - startY;
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mode = Mode.ZOOM;
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = Mode.DRAG;
                        break;
                    case MotionEvent.ACTION_UP:
                        mode = Mode.NONE;
                        prevDx = dx;
                        prevDy = dy;
                        break;
                }
                scaleDetector.onTouchEvent(motionEvent);

                if ((mode == Mode.DRAG && scale >= MIN_ZOOM) || mode == Mode.ZOOM) {
                    if(canZoom) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        float maxDx = (child().getWidth() - (child().getWidth() / scale)) / 2 * scale;
                        float maxDy = (child().getHeight() - (child().getHeight() / scale)) / 2 * scale;
                        dx = Math.min(Math.max(dx, -maxDx), maxDx);
                        dy = Math.min(Math.max(dy, -maxDy), maxDy);
                        applyScaleAndTranslation();
                    }
                }

                return true;
            }
        });
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleDetector) {
        return true;
    }

    @Override
    public boolean onScale(ScaleGestureDetector scaleDetector) {
        float scaleFactor = scaleDetector.getScaleFactor();
        if (lastScaleFactor == 0 || (Math.signum(scaleFactor) == Math.signum(lastScaleFactor))) {
            scale *= scaleFactor;
            scale = Math.max(MIN_ZOOM, Math.min(scale, MAX_ZOOM));
            lastScaleFactor = scaleFactor;
        } else {
            lastScaleFactor = 0;
        }
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleDetector) {
    }

    public void applyScaleAndTranslation() {
        this.setScaleX(scale);
        this.setScaleY(scale);
        this.setTranslationX(dx);
        this.setTranslationY(dy);
        child().setScaleX(scale);
        child().setScaleY(scale);
        child().setTranslationX(dx);
        child().setTranslationY(dy);
    }
    public void resetScaleAndTranslation() {
        scale = 1f;
        dx = dy = 0f;
        prevDx = prevDy = 0f;
        applyScaleAndTranslation();
    }


    private View child() {
        return getChildAt(0);
    }

}