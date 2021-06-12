package com.broooapps.curvegraphview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.broooapps.graphview.CurveGraphConfig;
import com.broooapps.graphview.CurveGraphView;
import com.broooapps.graphview.models.GraphData;
import com.broooapps.graphview.models.PointMap;

public class MainActivity extends AppCompatActivity {

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    CurveGraphView curveGraphView2;
    CurveGraphView curveGraphView;
    String l,r,n,a;
    int Left,Right,N,A[]=new int[100];

    public double f(double x){//вычисление значения полинома
        double y=A[N-1],xN=x;
        for(int i=N-2;i>=0;i--) {
            y += A[i] * xN;
            xN*=x;
        }
        return y;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //получение данных из MainActivity2
        Bundle arguments = getIntent().getExtras();
        l= arguments.getString("L");
        r= arguments.getString("R");
        n= arguments.getString("N");
        a= arguments.getString("A");
        TextView tv4=(TextView) findViewById(R.id.textView4);
        tv4.setText(l+r+n+a);//отладочный вывод - проверка правиьлности передачи
        Left=Integer.parseInt(l);
        Right=Integer.parseInt(r);
        N=Integer.parseInt(n);
//получение коэффициентов полинома
        String strArr[] = a.split(" ");
        for (int i = 0; i < strArr.length; i++) {
            A[i] = Integer.parseInt(strArr[i]);
        }
        //*********************************

        curveGraphView = findViewById(R.id.cgv);

        curveGraphView.configure(
                new CurveGraphConfig.Builder(this)
                        .setAxisColor(R.color.Blue)                                             // Set number of values to be displayed in X ax
                        .setVerticalGuideline(4)                                                // Set number of background guidelines to be shown.
                        .setHorizontalGuideline(2)
                        .setGuidelineColor(R.color.Red)                                         // Set color of the visible guidelines.
                        .setNoDataMsg(" No Data ")                                              // Message when no data is provided to the view.
                        .setxAxisScaleTextColor(R.color.Black)                                  // Set X axis scale text color.
                        .setyAxisScaleTextColor(R.color.Black)                                  // Set Y axis scale text color
                        .setAnimationDuration(2000)                                             // Set Animation Duration
                        .build()
        );


        PointMap pointMap = new PointMap();
/*
        pointMap.addPoint(1, 200);
        pointMap.addPoint(3, 400);
        pointMap.addPoint(4, 100);
        pointMap.addPoint(5, 600);
*/

        //строим прямую*****************************************************************************
        for(int i=0;i<100;i++) pointMap.addPoint(i,i*5);

        final GraphData gd = GraphData.builder(this)
                .setPointMap(pointMap)
                .setGraphStroke(R.color.Black)
                .setGraphGradient(R.color.gradientStartColor2, R.color.gradientEndColor2)
                .setStraightLine(true)
                .animateLine(true)
                .setPointColor(R.color.Red)
                .setPointRadius(1)
                .build();

        PointMap p2 = new PointMap();
/*
        p2.addPoint(0, 440);
        p2.addPoint(1, 0);
        p2.addPoint(2, 100);
        p2.addPoint(3, 0);
        p2.addPoint(4, 400);
        p2.addPoint(5, 200);
*/

        //строим синус******************************************************************************
        for(int i=0;i<100;i++) p2.addPoint(i,(int)(500*Math.sin(Math.PI*i/100.)) );

        final GraphData gd2 = GraphData.builder(this)
                .setPointMap(p2)
                .setGraphStroke(R.color.Green)
                .setGraphGradient(R.color.gradientStartColor, R.color.gradientEndColor)
                .setStraightLine(false)
                .animateLine(true)
                .build();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                curveGraphView.setData(100, 600, gd, gd2);
            }
        }, 250);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
