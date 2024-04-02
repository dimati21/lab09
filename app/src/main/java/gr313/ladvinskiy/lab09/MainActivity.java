package gr313.ladvinskiy.lab09;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import math.interp;

public class MainActivity extends AppCompatActivity {
    String func = "cos";
    float xMin;
    float xMax;
    int points;
    MySurface s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        s = findViewById(R.id.mySurface);

        xMin = intent.getFloatExtra("xMin", 0.0f);
        xMax = intent.getFloatExtra("xMax", 0.0f);
        s.n = intent.getIntExtra("points", 0);
        s.x = new float[s.n];
        s.y = new float[s.n];
        drawgraph();
    }

    public void on_createDialog_click(View v){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_alert);
        RadioGroup rgr = (RadioGroup) dialog.findViewById(R.id.radgroup);
        rgr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (dialog.findViewById(R.id.radcos).getId() == checkedId){
                    func = "cos";
                }
                if (dialog.findViewById(R.id.radsin).getId() == checkedId){
                    func = "sin";
                }
                if (dialog.findViewById(R.id.radtan).getId() == checkedId){
                    func = "tan";
                }
                if (dialog.findViewById(R.id.radacos).getId() == checkedId){
                    func = "acos";
                }

                if (dialog.findViewById(R.id.radasin).getId() == checkedId){
                    func = "asin";
                }
                drawgraph();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    protected void drawgraph(){

        MySurface s = findViewById(R.id.mySurface);
        s.invalidate();
        s.x = new float[s.n];
        s.y = new float[s.n];
        for (int i = 0; i < s.n; i++)
        {
            s.x[i] = interp.map(i, 0, s.n - 1, xMin, xMax);
            if (func == "cos") s.y[i] = (float) Math.cos(s.x[i]);
            if (func == "sin") s.y[i] = (float) Math.sin(s.x[i]);
            if (func == "tan") s.y[i] = (float) Math.tan(s.x[i]);
            if (func == "acos") s.y[i] = (float) Math.acos(s.x[i]);
            if (func == "asin") s.y[i] = (float) Math.asin(s.x[i]);
        }
        s.update();
        s.invalidate();
    }
    public void on_btnPoints_click(View v){
        finish();
    }
    public void btnPlus(View v)
    {
        s.zoom_plus(2.f);
        s.invalidate();
    }

    public void btnMinus(View v)
    {
        s.zoom_minus(2.f);
        s.invalidate();
    }
}