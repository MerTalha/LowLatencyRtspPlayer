package com.ahmet.lowlatencyrtspplayer;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alexvas.rtsp.widget.RtspSurfaceView;

public class MainActivity extends AppCompatActivity  {
    RtspSurfaceView surfaceViewLeft;
    Button leftButton1,leftButton2,leftButton3,leftButton4,leftButtonRefresh,leftButtonStop, btnZoomOut, btnZoomIn;

    ZoomLinearLayout zoomLayout; // ZoomLinearLayout sınıfının örneği

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceViewLeft = findViewById(R.id.surfaceViewLeft);
        leftButton1 = findViewById(R.id.left1);


        Uri uri1 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");
        Uri uri2 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=2&stream=0.sdp?");
        Uri uri3 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=3&stream=0.sdp?");
        Uri uri4 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=4&stream=0.sdp?");

        surfaceViewLeft.init(uri2,"","");

        surfaceViewLeft.start(true,true);

        zoomLayout = findViewById(R.id.zoomLayout); // ZoomLinearLayout'ı XML'den alın
        zoomLayout.setCanZoom(true); // Başlangıçta zoom yeteneğini açın

        RtspSurfaceView surfaceView = new RtspSurfaceView(this);
        zoomLayout.addView(surfaceView);


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

    }


}