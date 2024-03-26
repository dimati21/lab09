package gr313.ladvinskiy.lab09;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import math.interp;

public class MainActivity extends AppCompatActivity {
    String func = "cos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySurface s = findViewById(R.id.mySurface);

        s.n = 100;
        s.x = new float[s.n];
        s.y = new float[s.n];

        for (int i = 0; i < s.n; i++)
        {
            s.x[i] = interp.map(i, 0, s.n - 1, 0.0f, (float) Math.PI * 4.0f);
            s.y[i] = (float) Math.cos(s.x[i]);
        }
        s.update();
        s.invalidate();
    }

    public void on_createDialog_click(View v){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_alert);
        RadioGroup rgr = (RadioGroup) dialog.findViewById(R.id.radgroup);

        rgr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == 0){
                    func = "cos";
                    drawgraph();
                    dialog.dismiss();
                }
                if (checkedId == 1){
                    func = "sin";
                    drawgraph();
                    dialog.dismiss();
                }
                if (checkedId == 2){
                    func = "tan";
                    drawgraph();
                    dialog.dismiss();
                }
                if (checkedId == 3){
                    func = "acos";
                    drawgraph();
                    dialog.dismiss();
                }

                if (checkedId == 4){
                    func = "asin";
                    drawgraph();
                    dialog.dismiss();
                }

            }
        });
//        Button but_sin = (Button) dialog.findViewById(R.id.radsin);
//        but_sin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                func = "sin";
//                drawgraph();
//                dialog.dismiss();
//            }
//        });
//        Button but_cos = (Button) dialog.findViewById(R.id.radcos);
//        but_cos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                func = "cos";
//                drawgraph();
//                dialog.dismiss();
//            }
//        });
//        Button but_tan = (Button) dialog.findViewById(R.id.radtan);
//        but_tan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                func = "tan";
//                drawgraph();
//                dialog.dismiss();
//            }
//        });
//        Button but_acos = (Button) dialog.findViewById(R.id.radacos);
//        but_acos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                func = "acos";
//                drawgraph();
//                dialog.dismiss();
//            }
//        });
//        Button but_asin = (Button) dialog.findViewById(R.id.radasin);
//        but_asin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                func = "asin";
//                drawgraph();
//                dialog.dismiss();
//            }
//        });
        dialog.show();
    }
    protected void drawgraph(){
        MySurface s = findViewById(R.id.mySurface);
        s.invalidate();
        s.n = 100;
        s.x = new float[s.n];
        s.y = new float[s.n];

        for (int i = 0; i < s.n; i++)
        {
            s.x[i] = interp.map(i, 0, s.n - 1, 0.0f, (float) Math.PI * 4.0f);
            if (func == "cos") s.y[i] = (float) Math.cos(s.x[i]);
            if (func == "sin") s.y[i] = (float) Math.sin(s.x[i]);
            if (func == "tan") s.y[i] = (float) Math.tan(s.x[i]);
            if (func == "acos") s.y[i] = (float) Math.acos(s.x[i]);
            if (func == "asin") s.y[i] = (float) Math.asin(s.x[i]);
        }
        s.update();
        s.invalidate();
    }
}