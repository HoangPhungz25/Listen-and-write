package hoang.deptrai.com.listenandwrite;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import hoang.deptrai.com.listenandwrite.algorithm.ObjectModel_To_DrawChart;

public class ChartResultActivity extends AppCompatActivity {
    ListView lvResult;
    GraphView graphViewResult;
    ObjectModel_To_DrawChart[] objectModel_to_drawCharts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_result);
        Log.d("intent_chart","created");

        Log.d("intent_chart","Get intent");
        Intent intent = getIntent();
        Log.d("intent_chart","Get serializsble");
        objectModel_to_drawCharts = (ObjectModel_To_DrawChart[]) intent.getSerializableExtra("CHART_DATA");
        Log.d("intent_chart","Get serializsble done");

        addControls();

        DataPoint[] dataPoints = new DataPoint[objectModel_to_drawCharts.length];
        Log.d("length",objectModel_to_drawCharts.length+"");
        for(int i=0; i<objectModel_to_drawCharts.length; i++){
            if(objectModel_to_drawCharts[i]!=null){
                dataPoints[i] = new DataPoint(i,objectModel_to_drawCharts[i].getPercent_OfSimilarSubString());
            }else{
                dataPoints[i] = new DataPoint(i,0);
            }
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        series.setColor(Color.GREEN);
        graphViewResult.addSeries(series);

        addEvents();


    }

//    private class DataPoint{
//        int index;
//        int value;
//        public DataPoint(int index, int value){
//            this.index = index;
//            this.value = value;
//        }
//    }

    private void addEvents() {
    }

    private void addControls() {
        lvResult = findViewById(R.id.lvResult);
        graphViewResult = findViewById(R.id.graphViewResult);
        graphViewResult.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        graphViewResult.getViewport().setXAxisBoundsManual(true);
        graphViewResult.getViewport().setMaxX(objectModel_to_drawCharts.length);
        graphViewResult.getViewport().setYAxisBoundsManual(true);
        graphViewResult.getViewport().setMaxY(10);
    }
}
