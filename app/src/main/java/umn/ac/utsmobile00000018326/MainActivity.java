package umn.ac.utsmobile00000018326;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView ninja;
    TextView judul;
    Button TombolStart, TombolProfile, TombolQuit, Miss;

    private ConstraintLayout mMainLayout;
    private int maxTranslationX;
    private int maxTranslationY;
    private int translationX;
    private int translationY;
    private boolean clear = false;
    ObjectAnimator animationX;
    ObjectAnimator animationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ninja = findViewById(R.id.ninja);
        judul = findViewById(R.id.JudulGame);
        ninja.setBackgroundResource(R.drawable.ninja);
        mMainLayout = findViewById(R.id.main_layout);
        TombolStart = findViewById(R.id.TombolStart);
        TombolProfile = findViewById(R.id.TombolProfile);
        TombolQuit = findViewById(R.id.TombolQuit);
        animationX = ObjectAnimator.ofFloat(ninja, "translationX", 5);
        animationY = ObjectAnimator.ofFloat(ninja, "translationY", 5);
        final MediaPlayer MP_HIT = MediaPlayer.create(this, R.raw.hit);
        final MediaPlayer MP_START = MediaPlayer.create(this, R.raw.start);
        final MediaPlayer MP_MISS = MediaPlayer.create(this, R.raw.miss);


        TombolStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TombolStart.setVisibility(View.INVISIBLE);
                TombolProfile.setVisibility(View.INVISIBLE);
                judul.setVisibility(View.INVISIBLE);
                startAnimation();
                MP_START.start();

                ninja.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        TombolQuit.setVisibility(View.VISIBLE);
                        clear = true;
                        MP_HIT.start();

                        MP_HIT.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer MP_HIT) {
                                MP_HIT.release();
                            }
                        });

                        MP_START.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer MP_START) {
                                MP_START.release();
                            }
                        });
                    }
                });
                mMainLayout.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(!clear){MP_MISS.start();}

                    }
                });


            }
        });



        TombolQuit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                System.exit(0);

            }
        });

        TombolProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent lihatprofile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivityForResult(lihatprofile, 1);

            }
        });


    } // end of onCreate


    private void newCoordinates() {
        Random r = new Random();
        maxTranslationX = mMainLayout.getWidth() - (ninja.getLeft() + ninja.getWidth());
        maxTranslationY = mMainLayout.getHeight() - (ninja.getTop() + ninja.getHeight());
        translationX = maxTranslationX - r.nextInt(mMainLayout.getWidth());
        translationY = maxTranslationY - r.nextInt(mMainLayout.getHeight());
        animationX = ObjectAnimator.ofFloat(ninja, "translationX", translationX);
        animationY = ObjectAnimator.ofFloat(ninja, "translationY", translationY);

    }

    private void startAnimation() {
        newCoordinates();

        animationX.setDuration(380);
        animationX.setStartDelay(10);
        animationX.start();

        animationY.setDuration(380);
        animationY.setStartDelay(10);
        animationY.start();


        animationX.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if(!clear) {startAnimation();}
            }
        });

    }


}