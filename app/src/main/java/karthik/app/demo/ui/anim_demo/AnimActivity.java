package karthik.app.demo.ui.anim_demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import karthik.app.demo.R;

/**
 * Created by NiCK on 1/9/2018.
 */

public class AnimActivity extends AppCompatActivity {


    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;

    private  DisplayMetrics metrics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);
        initViewStyle();
        initViewsDown();
        animateIv2();
        animateTextview1();

        getSharedContent();
    }



    private void initViewStyle() {
        tv1.setTypeface(tv1.getTypeface(), Typeface.BOLD_ITALIC);
        tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);
    }


    private void initViewsDown() {

        metrics = getResources().getDisplayMetrics();

        ObjectAnimator objTv1 = ObjectAnimator.ofFloat(tv1, "translationY", getDeviceHeight());
        objTv1.setDuration(0);

        ObjectAnimator objTv2 = ObjectAnimator.ofFloat(tv2, "translationY", getDeviceHeight());
        objTv2.setDuration(0);

        ObjectAnimator objTv3 = ObjectAnimator.ofFloat(tv3, "translationY", getDeviceHeight());
        objTv3.setDuration(0);

        AnimatorSet animDown = new AnimatorSet();
        animDown.playTogether(objTv1,objTv2,objTv3);
        animDown.start();


    }


    private void animateIv2() {

        iv2.setScaleX(0.5f);
        iv2.setScaleY(0.5f);

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(iv2, "scaleX", 1.0f);
        scaleDownX.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(iv2, "scaleY", 1.0f);
        scaleDownY.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleDownX.setDuration(500);
        scaleDownY.setDuration(500);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        scaleDown.start();

    }


    private void animateTextview1() {

        ObjectAnimator tv1Animator = ObjectAnimator.ofFloat(tv1, "translationY", 0);
        tv1Animator.setDuration(200);
        tv1Animator.setInterpolator(new AccelerateDecelerateInterpolator());
        tv1Animator.start();
        tv1Animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                animateTextview2();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void animateTextview2() {

        ObjectAnimator tv2Animator = ObjectAnimator.ofFloat(tv2, "translationY", 0);
        tv2Animator.setDuration(200);
        tv2Animator.setInterpolator(new AccelerateDecelerateInterpolator());
        tv2Animator.start();
        tv2Animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                animateTextview3();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }


    private void animateTextview3() {

        ObjectAnimator tv3Animator = ObjectAnimator.ofFloat(tv3, "translationY", 0);
        tv3Animator.setDuration(200);
        tv3Animator.setInterpolator(new AccelerateDecelerateInterpolator());
        tv3Animator.start();
        tv3Animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void getSharedContent() {
        String receivedAction=getIntent().getAction();
        if(receivedAction.equals(Intent.ACTION_SEND)){

            String receivedText=getIntent().getStringExtra(Intent.EXTRA_TEXT);

            if (receivedText != null) {
                tv3.setText(receivedText);
            }
        }

    }


    private int getDeviceHeight(){
        return metrics.heightPixels;
    }
}
