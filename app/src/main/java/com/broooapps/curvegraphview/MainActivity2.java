package com.broooapps.curvegraphview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText l,r,n,a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        l=(EditText)findViewById(R.id.textL);
        r=(EditText)findViewById(R.id.textR);
        n=(EditText)findViewById(R.id.textN);
        a=(EditText)findViewById(R.id.textA);
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("L", l.getText().toString());
        intent.putExtra("R", r.getText().toString());
        intent.putExtra("N", n.getText().toString());
        intent.putExtra("A", a.getText().toString());
        startActivity(intent);
    }
}