package com.example.administrator.preparedregistry;


import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by dllo on 16/3/10.
 */
public class MyDrawable extends Drawable {

    private Paint mPaint;   //画笔
    private Bitmap mBitmap; //要绘制的图片对象
    private int mWidth;    //图片宽

    public MyDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mPaint = new Paint();
        // 建立 BitmapShader 对象    1.要绘制的bitmap
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);  // 绑定图片和画笔

        mWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());  // 图片宽 取宽高最小值
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {      // 控制画笔的
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {  // 控制画笔的  颜色滤镜
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {    // 透明度
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mWidth;
    }
}
