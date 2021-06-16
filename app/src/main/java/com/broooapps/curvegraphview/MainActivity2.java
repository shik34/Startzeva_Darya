package com.broooapps.curvegraphview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText l, r, n1, n2, a1, a2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        l=(EditText)findViewById(R.id.textL);
        r=(EditText)findViewById(R.id.textR);
        n1 =(EditText)findViewById(R.id.textN1);
        a1 =(EditText)findViewById(R.id.textA1);
        n2 =(EditText)findViewById(R.id.textN2);
        a2 =(EditText)findViewById(R.id.textA2);
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("L", l.getText().toString());
        intent.putExtra("R", r.getText().toString());
        intent.putExtra("N1", n1.getText().toString());
        intent.putExtra("A1", a1.getText().toString());
        intent.putExtra("N2", n2.getText().toString());
        intent.putExtra("A2", a2.getText().toString());
        startActivity(intent);
    }
}