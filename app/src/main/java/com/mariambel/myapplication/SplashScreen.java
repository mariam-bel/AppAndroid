package com.mariambel.myapplication;

import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class SplashScreen extends AppCompatActivity {

    ImageView fondo;
    ImageView splashLogo;
    TextView nombreApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        Animation fade = AnimationUtils.loadAnimation(this,R.anim.fading);

        nombreApp = findViewById(R.id.splashAppName);
        nombreApp.startAnimation(fade);


        fondo = findViewById(R.id.splashImagenFondo);
        Glide.with(this)
                .load("https://images.unsplash.com/photo-1637750586228-179a1a7aef84?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=687")
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .centerCrop()
                .into(fondo);

        launchNextActivity();
    }

    public void launchNextActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
            }
        }, 2000);
    }

}