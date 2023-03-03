package com.ahmet.lowlatencyrtspplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.alexvas.rtsp.widget.RtspSurfaceView;

public class MainActivity extends AppCompatActivity {
    RtspSurfaceView surfaceViewRight,surfaceViewLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceViewRight = findViewById(R.id.surfaceViewRight);
        surfaceViewLeft = findViewById(R.id.surfaceViewLeft);

        Uri uri1 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");
        Uri uri2 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");
        Uri uri3 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");
        Uri uri4 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");
        Uri uri5 = Uri.parse("rtsp://192.168.1.10:554/user=admin&password=&channel=1&stream=0.sdp?");

        surfaceViewRight.init(uri1,"","");
        surfaceViewLeft.init(uri2,"","");

        surfaceViewRight.start(true, true);
        surfaceViewLeft.start(true,true);

    }
}