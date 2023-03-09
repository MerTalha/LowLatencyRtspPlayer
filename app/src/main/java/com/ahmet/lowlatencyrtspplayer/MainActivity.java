package com.ahmet.lowlatencyrtspplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alexvas.rtsp.widget.RtspSurfaceView;

public class MainActivity extends AppCompatActivity {
    RtspSurfaceView surfaceViewRight,surfaceViewLeft;
    Button leftButton1,leftButton2,leftButton3,leftButton4,leftButtonRefresh,rightButton1,rightButton2,rightButton3,rightButton4,rightButtonRefresh,rightButtonStop,leftButtonStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceViewRight = findViewById(R.id.surfaceViewRight);
        surfaceViewLeft = findViewById(R.id.surfaceViewLeft);
        leftButton1 = findViewById(R.id.left1);
        leftButton2 = findViewById(R.id.left2);
        leftButton3 = findViewById(R.id.left3);
        leftButton4 = findViewById(R.id.left4);
        leftButtonRefresh = findViewById(R.id.leftRefresh);
        leftButtonStop = findViewById(R.id.leftStop);
        rightButton1 = findViewById(R.id.right1);
        rightButton2 = findViewById(R.id.right2);
        rightButton3 = findViewById(R.id.right3);
        rightButton4 = findViewById(R.id.right4);
        rightButtonRefresh = findViewById(R.id.righRefresh);
        rightButtonStop = findViewById(R.id.rightStop);



        Uri uri1 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");
        Uri uri2 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=2&stream=0.sdp?");
        Uri uri3 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=3&stream=0.sdp?");
        Uri uri4 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=4&stream=0.sdp?");
        Uri uri5 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=5&stream=0.sdp?");

        surfaceViewRight.init(uri1,"","");
        surfaceViewLeft.init(uri2,"","");

        surfaceViewRight.start(true, true);
        surfaceViewLeft.start(true,true);
        leftButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewLeft.isStarted()){
                    surfaceViewLeft.stop();
                    Toast.makeText(MainActivity.this, "Left Camera 1 Stop and Start", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewLeft.init(uri1,"","");
                    surfaceViewLeft.start(true,true);
                }

            }
        });
        leftButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewLeft.isStarted()){
                    surfaceViewLeft.stop();
                    Toast.makeText(MainActivity.this, "Left Camera 2 Stop and Start", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewLeft.init(uri2,"","");
                    surfaceViewLeft.start(true,true);
                }


            }
        });
        leftButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewLeft.isStarted()){
                    surfaceViewLeft.stop();
                    Toast.makeText(MainActivity.this, "Left Camera 3 Stop and Start", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewLeft.init(uri3,"","");
                    surfaceViewLeft.start(true,true);
                }

            }
        });
        leftButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewLeft.isStarted()){
                    surfaceViewLeft.stop();
                    Toast.makeText(MainActivity.this, "Left Camera 4 Stop and Start", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewLeft.init(uri4,"","");
                    surfaceViewLeft.start(true,true);
                }

            }
        });
        leftButtonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewLeft.isStarted()){
                    surfaceViewLeft.stop();
                    Toast.makeText(MainActivity.this, "Left Camera Stop and Refresh", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewLeft.start(true,true);
                }

            }
        });


        rightButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewRight.isStarted()){
                    surfaceViewRight.stop();
                    Toast.makeText(MainActivity.this, "Right Camera 1 Stop and Start", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewRight.init(uri1,"","");
                    surfaceViewRight.start(true,true);
                }

            }
        });
        rightButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewRight.isStarted()){
                    surfaceViewRight.stop();

                    Toast.makeText(MainActivity.this, "Right Camera 2 Stop and Start", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewRight.init(uri2,"","");
                    surfaceViewRight.start(true,true);
                }

            }
        });
        rightButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewRight.isStarted()){
                    surfaceViewRight.stop();
                    Toast.makeText(MainActivity.this, "Right Camera 3 Stop and Start", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewRight.init(uri3,"","");
                    surfaceViewRight.start(true,true);
                }

            }
        });
        rightButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewRight.isStarted()){
                    surfaceViewRight.stop();
                    Toast.makeText(MainActivity.this, "Right Camera 4 Stop and Start", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewRight.init(uri4,"","");
                    surfaceViewRight.start(true,true);
                }
            }
        });
        rightButtonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewRight.isStarted()){
                    surfaceViewRight.stop();
                    Toast.makeText(MainActivity.this, "Right Camera  Stop and Refresh", Toast.LENGTH_SHORT).show();

                }
                else{
                    surfaceViewRight.start(true,true);
                }

            }
        });
        rightButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewRight.isStarted()) {
                    surfaceViewRight.stop();
                    Toast.makeText(MainActivity.this, "Right Camera  Stop", Toast.LENGTH_SHORT).show();
                }
            }
        });
        leftButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surfaceViewLeft.isStarted()) {
                    surfaceViewLeft.stop();
                    Toast.makeText(MainActivity.this, "Left Camera  Stop", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}