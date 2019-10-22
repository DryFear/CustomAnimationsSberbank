package ru.unfortunately.school.customanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AnimationDrawableActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_drawable_layout);
        final ImageView imageView = findViewById(R.id.imege_view);
        ((AnimationDrawable) imageView.getDrawable()).start();
    }
}
