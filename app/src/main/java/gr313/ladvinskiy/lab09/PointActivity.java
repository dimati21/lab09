package gr313.ladvinskiy.lab09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PointActivity extends AppCompatActivity {

    EditText txt_points;
    EditText txt_xmax;
    EditText txt_xmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        txt_points = findViewById(R.id.ETpoints);
        txt_xmax = findViewById(R.id.ETxmax);
        txt_xmin = findViewById(R.id.ETxmin);
    }

    public void on_create_click(View v){
        float xMin, xMax;
        int points;

        try {
            xMin = Float.parseFloat(txt_xmin.getText().toString());
            xMax = Float.parseFloat(txt_xmax.getText().toString());
            points = Integer.parseInt(txt_points.getText().toString());
        }
        catch (Exception ex)
        {

            Toast.makeText(this, "Некорректные значения", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("points",points);
        intent.putExtra("xMin", xMin);
        intent.putExtra("xMax", xMax);

        startActivityForResult(intent, 12345);

    }
}