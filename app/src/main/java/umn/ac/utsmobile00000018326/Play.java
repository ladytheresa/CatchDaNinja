package umn.ac.utsmobile00000018326;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Play extends AppCompatActivity {
    ImageView ninja;
    private ConstraintLayout PlayLayout;
    private int maxTranslationX;
    private int maxTranslationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ninja = findViewById(R.id.ninja);
        ninja.setBackgroundResource(R.drawable.ninja);
        PlayLayout = findViewById(R.id.play);

        Random r = new Random();

            maxTranslationX = PlayLayout.getWidth() - (ninja.getLeft()+ninja.getWidth());
            maxTranslationY = PlayLayout.getHeight() - (ninja.getTop()+ninja.getHeight());

            int translationX = maxTranslationX - r.nextInt(PlayLayout.getWidth());
            int translationY = maxTranslationY - r.nextInt(PlayLayout.getHeight());

            ObjectAnimator animationX = ObjectAnimator.ofFloat(ninja, "translationX", translationX);
            ObjectAnimator animationY = ObjectAnimator.ofFloat(ninja, "translationY", translationY);
            animationX.setDuration(25);
            animationY.setDuration(25);
            animationX.start();
            animationY.start();


        };


    }

