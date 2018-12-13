package hoang.deptrai.com.listenandwrite;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;

import hoang.deptrai.com.listenandwrite.adapter.AdapterChartItem;
import hoang.deptrai.com.listenandwrite.algorithm.ObjectModel_To_DrawChart;

public class ChartResultActivity extends AppCompatActivity {
    ListView lvResult;
    AdapterChartItem adapterChartItem;
    ArrayList<ObjectModel_To_DrawChart> list_objectModel_To_DrawChart;
    GraphView graphViewResult;
    TextView tvTotalScore;
    ObjectModel_To_DrawChart[] objectModel_to_drawCharts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_result);

        Intent intent = getIntent();
        objectModel_to_drawCharts = (ObjectModel_To_DrawChart[]) intent.getSerializableExtra("CHART_DATA");

        addControls();

        DataPoint[] dataPoints = new DataPoint[objectModel_to_drawCharts.length];
        int total = 0;
        for(int i=0; i<objectModel_to_drawCharts.length; i++){
            if(objectModel_to_drawCharts[i]!=null){
                dataPoints[i] = new DataPoint(i,(objectModel_to_drawCharts[i].getPercent_OfSimilarSubString()/10));
                total+= objectModel_to_drawCharts[i].getPercent_OfSimilarSubString();
            }else{
                dataPoints[i] = new DataPoint(i,0);
            }
        }
        total/=objectModel_to_drawCharts.length;
        tvTotalScore.setText("Total Score: "+total+"%");
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        series.setColor(Color.rgb(255,233,0));
        graphViewResult.addSeries(series);

        addEvents();


    }

    private void addEvents() {
    }

    private void addControls() {
        lvResult = findViewById(R.id.lvResult);
        list_objectModel_To_DrawChart = new ArrayList<>(Arrays.asList(objectModel_to_drawCharts));
        adapterChartItem = new AdapterChartItem(this,R.layout.item_chart_result_for_a_track,list_objectModel_To_DrawChart);
        lvResult.setAdapter(adapterChartItem);

        graphViewResult = findViewById(R.id.graphViewResult);
        graphViewResult.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        graphViewResult.getViewport().setXAxisBoundsManual(true);
        graphViewResult.getViewport().setMaxX(objectModel_to_drawCharts.length);
        graphViewResult.getViewport().setYAxisBoundsManual(true);
        graphViewResult.getViewport().setMaxY(10);

        tvTotalScore = findViewById(R.id.tvTotalScore);
    }
}
