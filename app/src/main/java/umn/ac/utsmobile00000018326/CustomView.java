package umn.ac.utsmobile00000018326;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomView extends View {
    ImageView ninja;

    private ConstraintLayout mMainLayout;
    private int maxTranslationX;
    private int maxTranslationY;


    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void onDraw(Canvas canvas){
        ninja = findViewById(R.id.ninja);
        ninja.setBackgroundResource(R.drawable.ninja);
        mMainLayout = findViewById(R.id.main_layout);

        super.onDraw(canvas);
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
}
