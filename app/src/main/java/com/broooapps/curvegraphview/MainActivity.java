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
    String l,r, n1, a1, n2, a2;
    int left, right, N1, N2, A1[]=new int[100],A2[]=new int[100];

    public double f(double x,int N, int [] A){//вычисление значения полинома
        double y=A[N-1],xN=x;
        for(int i=N-2;i>=0;i--) {
            y += A[i] * xN;
            xN*=x;
        }
//        y=2*x;
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
        n1 = arguments.getString("N1");
        a1 = arguments.getString("A1");
        n2 = arguments.getString("N2");
        a2 = arguments.getString("A2");
/*
        if(l=="") l="1";
        if(r=="") r="2";
        if(n=="") n="1";
        if(a=="") a="1 1";
*/
/*
        l="0";
        r="1";
        n1="3";
        a1="4 0 0";
        n2="3";
        a2="6 0 0";
*/

        TextView tv4=(TextView) findViewById(R.id.textView4);
        tv4.setText(l+r+ n1 + a1);//отладочный вывод - проверка правиьлности передачи
        left =Integer.parseInt(l);
        right =Integer.parseInt(r);
        N1 =Integer.parseInt(n1)+1;
        N2 =Integer.parseInt(n2)+1;
//получение коэффициентов полинома
        String [] strArr;
        strArr = a1.split(" ");
        for (int i = 0; i < strArr.length; i++) {
            A1[i] = Integer.parseInt(strArr[i]);
        }
        strArr = a2.split(" ");
        for (int i = 0; i < strArr.length; i++) {
            A2[i] = Integer.parseInt(strArr[i]);
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



        PointMap p1 = new PointMap();
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
        double x=left,y,h=(right-left)/100.0;
        Double yy;
        for(int i=0;i<100;i++) {

//            p2.addPoint(i,(int)(500*Math.sin(Math.PI*i/100.)) );
            //y=200 * x;
            y=200*f(x,N1,A1);
            p1.addPoint(i, (int)(y));
            y=200*f(x,N2,A2);
            p2.addPoint(i, (int)(y));
            //x+=(int)(500*Math.sin(Math.PI*i/100.));
           x+=h;
            yy=y;
            String s="y="+yy.toString();
            tv4.setText(s);
//            p2.addPoint(i,i*4);
        }
        final GraphData gd = GraphData.builder(this)
                .setPointMap(p1)
                .setGraphStroke(R.color.Black)
                //   .setGraphGradient(R.color.gradientStartColor2, R.color.gradientEndColor2)
                .setStraightLine(true)
                .animateLine(true)
                .setPointColor(R.color.Red)
                .setPointRadius(1)
                .build();
        final GraphData gd2 = GraphData.builder(this)
                .setPointMap(p2)
                .setGraphStroke(R.color.Green)
//                .setGraphGradient(R.color.gradientStartColor, R.color.gradientEndColor)
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
