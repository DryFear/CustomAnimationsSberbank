package ru.unfortunately.school.customanimation;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FinanceProgressView extends View {

    private static final String TAG = "FinanceProgressView";

    private static final int DEFAULT_COLOR = Color.BLACK;
    private static final int MAX_PROGRESS = 100;
    private static final int MAX_ANGLE = 360;
    private static final int REQUESTED_SIZE = 300;
    private static final float RATIO_STROKE_WIDTH = 64f/700f;

    private int mStrokeWidth = 50;

    private int mProgress;
    private int mColor;
    private int mTextSize;

    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint();

    private RectF mProgressRect = new RectF(0, 0, REQUESTED_SIZE - mStrokeWidth, REQUESTED_SIZE - mStrokeWidth);
    private Rect mTextBounds = new Rect();


    public FinanceProgressView(Context context) {
        this(context, null);
    }

    public FinanceProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure() called with: widthMeasureSpec = [" + MeasureSpec.toString(widthMeasureSpec) + "], heightMeasureSpec = [" + MeasureSpec.toString(heightMeasureSpec) + "]");
        getTextBounds(formatString(MAX_PROGRESS));
        Log.d(TAG, "onMeasure() called with: stroke_width = [" + mStrokeWidth + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
        final int size = REQUESTED_SIZE;
        final int width = resolveSize(size, widthMeasureSpec);
        final int height = resolveSize(size, heightMeasureSpec);
        mProgressRect.right = width - mStrokeWidth;
        mProgressRect.bottom = height - mStrokeWidth;
        setMeasuredDimension(width, height);
    }


//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh){
//        mProgressRect.right = w;
//        mProgressRect.bottom = h;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mStrokeWidth / 2, mStrokeWidth / 2);
        canvas.drawArc(mProgressRect, -90f, mProgress * MAX_ANGLE / MAX_PROGRESS, false, mCirclePaint);
        final String progressString = formatString(mProgress);
        getTextBounds(progressString);
        float x = mProgressRect.width() / 2f - mTextBounds.width() / 2f - mTextBounds.left;
        float y = mProgressRect.height() / 2f + mTextBounds.height() / 2f - mTextBounds.bottom;
        canvas.drawText(progressString, x, y, mTextPaint);

    }

    private void getTextBounds(@NonNull String progressString){
        mTextPaint.getTextBounds(progressString, 0, progressString.length(), mTextBounds);
    }

    private String formatString(int progress){
        return String.format(getResources().getString(R.string.progress_template), progress);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        extractAttributes(context, attrs);
        configureCirclePaint();
        configureTextPaint();
    }

    private void configureTextPaint() {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mColor);
    }

    private void configureCirclePaint() {
        mCirclePaint.setStyle(Style.STROKE);
        mCirclePaint.setColor(mColor);
        mCirclePaint.setStrokeWidth(mStrokeWidth);
    }

    private void extractAttributes(@NonNull Context context, @Nullable AttributeSet attrs) {
        final Resources.Theme theme = context.getTheme();
        Log.d(TAG, "Progress = " + mProgress + ", Color = " + mColor + ", Text size = " + mTextSize);
        final TypedArray typedArray = theme.obtainStyledAttributes(attrs, R.styleable.FinanceProgressView, R.attr.financeProgressStyle, 0);
        try {
            mProgress = typedArray.getInteger(R.styleable.FinanceProgressView_progress, DEFAULT_COLOR);
            mColor = typedArray.getColor(R.styleable.FinanceProgressView_color, Color.RED);
            mTextSize = typedArray.getDimensionPixelSize(R.styleable.FinanceProgressView_text_size,
                    getResources().getDimensionPixelSize(R.dimen.default_text_size));
            Log.d(TAG, "Progress = " + mProgress + ", Color = " + mColor + ", Text size = " + mTextSize);
        }finally {
            typedArray.recycle();
        }
    }


    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int mProgress) {
        this.mProgress = mProgress;
        invalidate();;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }
}
