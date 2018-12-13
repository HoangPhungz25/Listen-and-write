package hoang.deptrai.com.listenandwrite;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;

import hoang.deptrai.com.listenandwrite.adapter.AdapterEachTimePlayedVideo;
import hoang.deptrai.com.listenandwrite.algorithm.ToolString;
import hoang.deptrai.com.listenandwrite.data.Video;

public class FragmentChartForVideo extends android.app.Fragment {
    GraphView graphView;
    ListView lvTimePlayedVideo;
    AdapterEachTimePlayedVideo adapterEachTimePlayedVideo;
    ArrayList<String> listPercentEachTimePlayed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_for_video,container,false);

        Video video = new Video();
        if(getArguments()!=null && getArguments().containsKey("VIDEO")) video = (Video) getArguments().getSerializable("VIDEO");
        ToolString toolString = new ToolString();
        listPercentEachTimePlayed = new ArrayList<String>(Arrays.asList(toolString.split_string_to_string_array(video.getPercent_of_eachTimePlayed())));

        graphView = view.findViewById(R.id.graphChartForVideo);
        lvTimePlayedVideo = view.findViewById(R.id.lvVideoPlayedEachTime);
        adapterEachTimePlayedVideo = new AdapterEachTimePlayedVideo(getActivity(),
                R.layout.item_chart_result_for_a_video,
                listPercentEachTimePlayed);
        lvTimePlayedVideo.setAdapter(adapterEachTimePlayedVideo);

        graphView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMaxX(listPercentEachTimePlayed.size());
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMaxY(100);

        DataPoint[] dataPoints = new DataPoint[listPercentEachTimePlayed.size()];
        for(int i=0; i<listPercentEachTimePlayed.size(); i++){
            dataPoints[i] = new DataPoint(i,Integer.parseInt(listPercentEachTimePlayed.get(i)));
        }
        LineGraphSeries<DataPoint> lineGraphSeries = new LineGraphSeries<>(dataPoints);
        lineGraphSeries.setColor(Color.rgb(255,233,0));
        graphView.addSeries(lineGraphSeries);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
