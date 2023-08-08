package com.ahmet.lowlatencyrtspplayer;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alexvas.rtsp.widget.RtspSurfaceView;

public class MainActivity extends AppCompatActivity {
    RtspSurfaceView surfaceViewLeft;
    Button leftButton1,leftButton2,leftButton3,leftButton4,leftButtonRefresh,leftButtonStop, btnZoomOut, btnZoomIn;
    private float scaleFactor = 1.0f;
    private float MAX_SCALE_FACTOR = 1.5f;
    private float MIN_SCALE_FACTOR = 1.0f;
    private PointF lastTouchPoint = new PointF();
    private PointF startTouchPoint = new PointF();
    private float minX, maxX, minY, maxY;
    private boolean isDragging = false;
    private boolean isZoomed = false;
    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceViewLeft = findViewById(R.id.surfaceViewLeft);
        leftButton1 = findViewById(R.id.left1);
        leftButton2 = findViewById(R.id.left2);
        leftButton3 = findViewById(R.id.left3);
        leftButton4 = findViewById(R.id.left4);
        btnZoomOut = findViewById(R.id.btnZoomOut);
        btnZoomIn = findViewById(R.id.btnZoomIn);
        leftButtonRefresh = findViewById(R.id.leftRefresh);
        leftButtonStop = findViewById(R.id.leftStop);

        Uri uri1 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");
        Uri uri2 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=2&stream=0.sdp?");
        Uri uri3 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=3&stream=0.sdp?");
        Uri uri4 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=4&stream=0.sdp?");
        Uri uri5 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=5&stream=0.sdp?");

        surfaceViewLeft.init(uri2,"","");

        surfaceViewLeft.start(true,true);

        btnZoomOut.setOnClickListener(view -> {
            surfaceViewLeft.setScaleX(1.0f);
            surfaceViewLeft.setScaleY(1.0f);
        });

        btnZoomIn.setOnClickListener(view -> {
            surfaceViewLeft.setScaleX(1.5f);
            surfaceViewLeft.setScaleY(1.5f);
        });


        // Ekran boyutunu al
        surfaceViewLeft.setOnTouchListener((v, event) -> {
            int action = event.getActionMasked();
            PointF currentTouchPoint = new PointF(event.getX(), event.getY());
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    isDragging = false;
                    startTouchPoint.set(currentTouchPoint);
                    lastTouchPoint.set(currentTouchPoint);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (event.getPointerCount() == 1 && !isDragging && isZoomed) {
                        isDragging = true;
                    }
                    if (event.getPointerCount() == 1 && isDragging && isZoomed) {
                        float dx = currentTouchPoint.x - lastTouchPoint.x;
                        float dy = currentTouchPoint.y - lastTouchPoint.y;
                        float newX = surfaceViewLeft.getTranslationX() + dx;
                        float newY = surfaceViewLeft.getTranslationY() + dy;

                        // Sınırları uygula
                        newX = clamp(newX, minX, maxX);
                        newY = clamp(newY, minY, maxY);

                        surfaceViewLeft.setTranslationX(newX);
                        surfaceViewLeft.setTranslationY(newY);
                    }
                    lastTouchPoint.set(currentTouchPoint);
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    isZoomed = true; // Zoom başladığında işaretle
                    isDragging = false; // Zoom esnasında kaydırmayı engelle
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    isDragging = false;
                    break;
                case MotionEvent.ACTION_UP:
                    if (!isDragging && isZoomed) {
                        // Zoom işlemi sonlandı
                    }
                    isDragging = false;
                    break;
            }
            return true;
        });

        leftButton1.setOnClickListener(v -> {
            if (surfaceViewLeft.isStarted()){
                surfaceViewLeft.stop();
                Toast.makeText(MainActivity.this, "Left Camera 1 Stop and Start", Toast.LENGTH_SHORT).show();

            }
            else{
                surfaceViewLeft.init(uri1,"","");
                surfaceViewLeft.start(true,true);
            }

        });
        leftButton2.setOnClickListener(v -> {
            if (surfaceViewLeft.isStarted()){
                surfaceViewLeft.stop();
                Toast.makeText(MainActivity.this, "Left Camera 2 Stop and Start", Toast.LENGTH_SHORT).show();

            }
            else{
                surfaceViewLeft.init(uri2,"","");
                surfaceViewLeft.start(true,true);
            }
        });

        leftButton3.setOnClickListener(v -> {
            if (surfaceViewLeft.isStarted()){
                surfaceViewLeft.stop();
                Toast.makeText(MainActivity.this, "Left Camera 3 Stop and Start", Toast.LENGTH_SHORT).show();

            }
            else{
                surfaceViewLeft.init(uri3,"","");
                surfaceViewLeft.start(true,true);
            }
        });

        leftButton4.setOnClickListener(v -> {
            if (surfaceViewLeft.isStarted()){
                surfaceViewLeft.stop();
                Toast.makeText(MainActivity.this, "Left Camera 4 Stop and Start", Toast.LENGTH_SHORT).show();

            }
            else{
                surfaceViewLeft.init(uri4,"","");
                surfaceViewLeft.start(true,true);
            }
        });

        leftButtonRefresh.setOnClickListener(v -> {
            if (surfaceViewLeft.isStarted()){
                surfaceViewLeft.stop();
                Toast.makeText(MainActivity.this, "Left Camera Stop and Refresh", Toast.LENGTH_SHORT).show();

            }
            else{
                surfaceViewLeft.start(true,true);
            }
        });

        leftButtonStop.setOnClickListener(v -> {
            if (surfaceViewLeft.isStarted()) {
                surfaceViewLeft.stop();
                Toast.makeText(MainActivity.this, "Left Camera  Stop", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private float clamp(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

    private float getFingerSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }
}