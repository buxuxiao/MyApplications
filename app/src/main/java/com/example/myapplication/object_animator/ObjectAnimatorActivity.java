package com.example.myapplication.object_animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.myapplication.R;

public class ObjectAnimatorActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);

        initView();
        initData();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);

        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate1();
            }
        });

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate2();
            }
        });

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate3();
            }
        });

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate4();
            }
        });

    }

    private void initData() {

    }

    private void animate1() {
        float x=imageView.getX();
        ObjectAnimator   move = ObjectAnimator//创建实例
                .ofFloat(imageView, "X", x,x+300)
                .setDuration(3000);//设置时常
        ObjectAnimator   colorAnimator = ObjectAnimator//创建实例
                //(View,属性名,初始化值,结束值)
                .ofInt(imageView, "BackgroundColor", 0xff0000ff, 0xffF2BA38)
                .setDuration(3000);

        ObjectAnimator translate = ObjectAnimator.ofFloat(imageView, "translationY", 10f, 100f);
        translate.setDuration(1000);
        translate.setRepeatCount(1);
        translate.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.4f,1.0f);
        alpha.setDuration(1000);

        AnimatorSet animationSet=new AnimatorSet();
        animationSet.play(translate)
                .with(alpha)
                .after(colorAnimator)
                 .before(move);
      //  animationSet.start();
        alpha.start();
    }

    private void animate2() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 3f);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("abc", "ValueAnimator value=" + animation.getAnimatedValue());

            }
        });
        valueAnimator.start();
    }

    private void animate3() {
        ValueAnimator.ofObject(new TypeEvaluator<Dog>() {

            @Override
            public Dog evaluate(float fraction, Dog startValue, Dog endValue) {
                Log.d("abc1", "fraction=" + fraction + "startValue=" + startValue + " endValue=" + endValue);
                return startValue;
            }
        }, new Dog(1), new Dog(3)).setDuration(500).start();
    }

    class Dog {
        int value;

        Dog(int v) {
            this.value = v;
        }

        @Override
        public String toString() {
            return "Dog value=" + value;
        }
    }

    private void animate4() {
        TimeAnimator timeAnimator = new TimeAnimator();
        timeAnimator.setTimeListener(new TimeAnimator.TimeListener() {
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                Log.d("abc", String.format("TimeAnimator totalTime=%ddeltaTime=%d", totalTime, deltaTime));
                if (totalTime > 5000) {
                    animation.end();
                }
            }
        });
        timeAnimator.start();
    }

    class MyIntercept implements TimeInterpolator {
        @Override
        public float getInterpolation(float input) {
            if (input < 0.5) {
                return input * 2;
            } else {
                return 1 - (input - 0.5f) * 2;
            }
        }
    }


}
