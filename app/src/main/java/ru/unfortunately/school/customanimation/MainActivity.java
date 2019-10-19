package ru.unfortunately.school.customanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleClick(R.id.custom_view, CustomViewAnimationsActivity.class);
        handleClick(R.id.view_animations, ViewAnimationActivity.class);
        handleClick(R.id.animation_drawable, AnimationDrawableActivity.class);
        handleClick(R.id.object_animations, ObjectAnimationsActivity.class);
        handleClick(R.id.value_animations, ValueAnimationsActivity.class);
    }

    private void handleClick(@IdRes int viewId, final Class<? extends Activity> activityClass) {
        findViewById(viewId).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activityClass));
            }
        });
    }
}
