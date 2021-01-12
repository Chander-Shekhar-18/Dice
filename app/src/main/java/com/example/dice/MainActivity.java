package com.example.dice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 202;

    private ImageView ivPlaceHolder;
    private Button btnRollDIce;
    private Button btnOpenCamera;
    private ImageView imgCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }

        ivPlaceHolder = findViewById(R.id.iv_placeholder);
        imgCam = findViewById(R.id.imageView2);
        btnOpenCamera = findViewById(R.id.btnOpenCamera);
        btnRollDIce = findViewById(R.id.btn_roll_dice);

        final Random random = new Random();

        btnRollDIce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand = random.nextInt(6) + 1;

                if (rand == 1) {
                    ivPlaceHolder.setImageResource(R.drawable.one);
                } else if (rand == 2) {
                    ivPlaceHolder.setImageResource(R.drawable.two);
                } else if (rand == 3) {
                    ivPlaceHolder.setImageResource(R.drawable.three);
                } else if (rand == 4) {
                    ivPlaceHolder.setImageResource(R.drawable.four);
                } else if (rand == 5) {
                    ivPlaceHolder.setImageResource(R.drawable.five);
                } else if (rand == 6) {
                    ivPlaceHolder.setImageResource(R.drawable.six);
                }
            }
        });

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK && null != data) {
                    Bundle bundle = data.getExtras();
                    if (null != bundle) {
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        Glide.with(this).asBitmap().load(bitmap).into(imgCam);
                    }
                }
                break;
            default:
                break;
        }
    }
}







































