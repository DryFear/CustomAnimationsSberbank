package ru.unfortunately.school.customanimation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CustomViewAnimationsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_animations_layout);

        View view = findViewById(R.id.finance_progress);
        ObjectAnimator animator = ObjectAnimator.ofInt(view, "progress", 1, 100);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setDuration(1000);
        animator.setRepeatCount(10);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
