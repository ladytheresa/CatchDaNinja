package umn.ac.utsmobile00000018326;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
//import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView ninja;
    Button TombolStart, TombolProfile, TombolQuit;

    private ConstraintLayout mMainLayout;
    private int maxTranslationX;
    private int maxTranslationY;
    //private AnimatorSet mAnimatorSet = new AnimatorSet();
    private boolean clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ninja = findViewById(R.id.ninja);
        ninja.setBackgroundResource(R.drawable.ninja);
        mMainLayout = findViewById(R.id.main_layout);
        TombolStart = findViewById(R.id.TombolStart);
        TombolProfile = findViewById(R.id.TombolProfile);
        TombolQuit = findViewById(R.id.TombolQuit);


        TombolStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent play = new Intent(MainActivity.this, Play.class);
                //startActivityForResult(play, 1);
                int i = 0;

                //TombolStart.setVisibility(View.INVISIBLE);
                //TombolProfile.setVisibility(View.INVISIBLE);
                 startAnimation();


            }

            private void startAnimation() {
                if(!clear){

                    Random r = new Random();

                    maxTranslationX = mMainLayout.getWidth() - (ninja.getLeft() + ninja.getWidth());
                    maxTranslationY = mMainLayout.getHeight() - (ninja.getTop() + ninja.getHeight());

                    int translationX = maxTranslationX - r.nextInt(mMainLayout.getWidth());
                    int translationY = maxTranslationY - r.nextInt(mMainLayout.getHeight());

                    ObjectAnimator animationX = ObjectAnimator.ofFloat(ninja, "translationX", translationX);
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(ninja, "translationY", translationY);

                    animationX.setDuration(50);
                    animationX.setStartDelay(100);
                    animationX.start();

                    animationY.setDuration(50);
                    animationY.setStartDelay(100);
                    animationY.start();

                }

                ninja.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        clear = true;
                        TombolQuit.setVisibility(View.VISIBLE);

                    }
                });



            }
        });

        TombolProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent lihatprofile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivityForResult(lihatprofile, 1);

            }
        });


    }
}