package br.com.juliana.loureiro.projetofinalahp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {
    private ImageView imgsplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        declaraObjetos();

        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                exibirTelaPrincipal();
            }
        }, 2000);

    }

    private void declaraObjetos() {
        imgsplash = findViewById(R.id.imgsplash);

        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE); //Repeat animation indefinitely
        anim.setDuration(700); //Put desired duration per anim cycle here, in milliseconds

        imgsplash.startAnimation(anim);
    }

    private void exibirTelaPrincipal() {
        Intent intent = new Intent(SplashScreenActivity.this,
                TelaPrincipal.class);
        startActivity(intent);
        finish();
    }

}