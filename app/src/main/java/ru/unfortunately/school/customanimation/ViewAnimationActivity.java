package ru.unfortunately.school.customanimation;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_animation_layout);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_and_scale);
        final View imageView = findViewById(R.id.image);

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.clearAnimation();
                imageView.startAnimation(animation);
            }
        });
    }
}
