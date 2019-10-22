package ru.unfortunately.school.customanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ObjectAnimationsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_animations_layout);

        ImageView view = findViewById(R.id.image_view);

        ObjectAnimator scaleXanimator = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f);
        ObjectAnimator scaleYanimator = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f);
        configure(scaleXanimator).start();
        configure(scaleYanimator).start();
    }

    private Animator configure(ObjectAnimator animator) {
        animator.setDuration(1000);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.setRepeatCount(20);
        return animator;
    }
}
