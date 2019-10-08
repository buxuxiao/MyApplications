package com.example.myapplication.object_animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeAnimator;
import android.animation.TypeConverter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnimationTestActivity extends AppCompatActivity {

    private ImageView imageView2;
    private CircleView circleView;

    private ListView listView;

    private String[] methods = {
            "alphaAnimate",
            "alphaAnimate_simple",
            "translationAnimate",
            "scaleAnimate",
            "rotateAnimate",
            "wave",
            "wave_and_alpha",
            "noViewAnimation",
            "circle_move",
            "circle_multi",
            "customeProperty",
            "custom_value",
            "listener",
            "valueAnimatorUpdate",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);

        initView();
        initData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, methods));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String method = methods[position];
                try {
                    Method m = AnimationTestActivity.class.getDeclaredMethod(method);
                    m.setAccessible(true);
                    m.invoke(AnimationTestActivity.this);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });


        imageView2 = (ImageView) findViewById(R.id.imageView2);
        circleView = (CircleView) findViewById(R.id.circleView);

    }

    private void initData() {

    }

    private void alphaAnimate() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView2, "alpha", 1.0f, 0.5f)
                .setDuration(1000);
        animator.start();
    }

    private void alphaAnimate_simple() {
        imageView2.animate().alpha(0.4f).setDuration(500).start();
    }

    private void translationAnimate() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView2, "translationX", 0f, 200f)
                .setDuration(1000);
        animator.start();
    }

    private void scaleAnimate() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView2, "scaleX", 1f, 3f);
        animator.start();
    }

    private void rotateAnimate() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView2, "rotation", 0f, 360f)
                .setDuration(1000);
        animator.start();
    }

    private void wave() {
        ObjectAnimator animator = ObjectAnimator.ofInt(circleView, "radius", 0, circleView.getWidth())
                .setDuration(1000);
        animator.start();
    }

    private void wave_and_alpha() {
        ObjectAnimator animator = ObjectAnimator.ofInt(circleView, "radius", 0, circleView.getWidth());
        ObjectAnimator alpha = ObjectAnimator.ofFloat(circleView, "alpha", 1.0f, -1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(4000).play(animator).with(alpha);
        set.start();
    }

    private void noViewAnimation() {
        Circle circle = new Circle();
        ObjectAnimator animator = ObjectAnimator.ofInt(circle, "radius", 0, 500)
                .setDuration(1000);
        animator.start();
    }

    private void circle_move() {
        Path path = new Path();
        path.moveTo(50, 50);
        path.lineTo(circleView.getWidth() - 50, 50);
        path.lineTo(circleView.getWidth() - 50, circleView.getHeight() - 50);
        path.lineTo(50, circleView.getHeight() - 50);
        path.close();
        ObjectAnimator animator = ObjectAnimator.ofInt(circleView, "xpos", "ypos", path);
        animator.setDuration(4000);
        animator.start();
    }

    private void circle_multi() {
        int[][] values = {
                {1, 2,},
                {4, 5,}
        };
        ObjectAnimator animator = ObjectAnimator.ofMultiInt(new Circle(), "abc", values);
        animator.setDuration(2000);
        animator.start();
    }

    private void customeProperty() {
        ObjectAnimator animator = ObjectAnimator.ofInt(circleView, new Property<CircleView, Integer>(Integer.class, "radius") {
            @Override
            public Integer get(CircleView object) {
                return object.getR();
            }

            @Override
            public void set(CircleView object, Integer value) {
                object.setR(value);
            }
        }, 0, 500)
                .setDuration(1000);
        animator.start();
    }

    private void custom_value() {
        ObjectAnimator animator = ObjectAnimator.ofObject(new Circle(), new Property<Circle, PointF>(PointF.class, "1") {
            @Override
            public PointF get(Circle object) {
                return null;
            }

            @Override
            public void set(Circle object, PointF value) {
                object.setPointF(value);
            }
        }, new TypeConverter<RectF, PointF>(RectF.class, PointF.class) {
            @Override
            public PointF convert(RectF value) {
                return new PointF(value.left, value.top);
            }
        }, new TypeEvaluator<RectF>() {
            @Override
            public RectF evaluate(float fraction, RectF startValue, RectF endValue) {
                if (fraction < 0.5f) {
                    return startValue;
                } else {
                    return endValue;
                }
            }
        }, new RectF(1, 2, 3, 4), new RectF(5, 6, 7, 8));
        animator.setDuration(500);
        animator.start();
    }

    private void listener() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView2, "alpha", 1.0f, 0.5f)
                .setDuration(5000);

        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.addPauseListener(new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animation) {

            }

            @Override
            public void onAnimationResume(Animator animation) {

            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });



        animator.start();
    }

    private void valueAnimatorUpdate() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("abc","onAnimationUpdate="+animation.getAnimatedValue());

            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }

    private void timeTest(){
        TimeAnimator timeAnimator=new TimeAnimator();
        timeAnimator.setTimeListener(new TimeAnimator.TimeListener() {
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {

            }
        });
    }
}
