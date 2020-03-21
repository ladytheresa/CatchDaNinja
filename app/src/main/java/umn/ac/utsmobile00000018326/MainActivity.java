package umn.ac.utsmobile00000018326;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView ninja;
    TextView judul;
    Button TombolStart, TombolProfile, TombolQuit, TombolAgain;
    ObjectAnimator animationX;
    ObjectAnimator animationY;

    private ConstraintLayout mMainLayout;
    private int maxTranslationX, maxTranslationY, translationX, translationY;
    private boolean clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ninja = findViewById(R.id.ninja);
        ninja.setBackgroundResource(R.drawable.ninja);
        judul = findViewById(R.id.JudulGame);
        mMainLayout = findViewById(R.id.main_layout);
        TombolStart = findViewById(R.id.TombolStart);
        TombolProfile = findViewById(R.id.TombolProfile);
        TombolAgain = findViewById(R.id.TombolAgain);
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
                        TombolAgain.setVisibility(View.VISIBLE);
                        clear = true;
                        MP_HIT.start();
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

        TombolAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clear = false;
                TombolQuit.setVisibility(View.GONE);
                TombolAgain.setVisibility(View.GONE);
                TombolStart.setVisibility(View.VISIBLE);
                TombolProfile.setVisibility(View.VISIBLE);
                judul.setVisibility(View.VISIBLE);
                ninja.setX((mMainLayout.getWidth()/2)-(ninja.getWidth()/2));
                ninja.setY((mMainLayout.getHeight()/2)-(ninja.getHeight()/2)-200);
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