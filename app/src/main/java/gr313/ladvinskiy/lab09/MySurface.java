package gr313.ladvinskiy.lab09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;

import math.arr;
import math.interp;

public class MySurface extends SurfaceView{
    Paint p;
    float tx, ty;
    boolean tt;
    float ax0, ax1, ay0, ay1;
    float xmin;
    float xmax;
    float ymin;
    float ymax;
    float[] x;
    float[] y;
    int n;
    float XMIN;
    float XMAX ;
    public void update(){
        xmin = arr.min(x, n);
        xmax = arr.max(x, n);
        ymin = arr.min(y, n);
        ymax = arr.max(y, n);
        XMIN = xmin;
        XMAX = xmax;
    }

    public MySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setColor(Color.RED);

        setWillNotDraw(false);
    }
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        int w = canvas.getWidth();
        int h = canvas.getHeight();

        float x0 = 0.0f, y0 = 0.0f;
        for (int i = 0; i < n; i++)
        {
            float x1 = interp.map(x[i], xmin, xmax, 0, w - 1);
            float y1 = interp.map(y[i], ymin, ymax, h - 1, 0);

            if (i > 0) canvas.drawLine(x0, y0, x1, y1, p);
            x0 = x1;
            y0 = y1;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int act = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (act)
        {
            case MotionEvent.ACTION_DOWN:
                tx = x;
                ty = y;
                tt = true;
                return true;
            case MotionEvent.ACTION_UP:
                tt=false;
                return true;
            case MotionEvent.ACTION_MOVE:
            {
                float dx = x - tx;
                float dy = y - ty;
                dx *= -0.01f;
                dy *= (ay1 - ay0) / getWidth();
                ax0 += dx;
                ax1 += dx;
                ay0 += dy;
                ay1 += dy;
                tx = x;
                ty = y;
                invalidate();
                return true;
            }
        }
        return false;
    }
    public void zoom_plus(float numb) {
        if (xmin + numb < xmax - numb) {
            xmax -= numb;
            xmin += numb;
        }
    }

    public void zoom_minus(float numb) {
        if (xmin - numb > XMIN && xmax + numb < XMAX) {
            xmax += numb;
            xmin -= numb;
        }
        else {
            xmax = XMAX;
            xmin = XMIN;
        }
    }
}
