package hoang.deptrai.com.listenandwrite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.List;

import hoang.deptrai.com.listenandwrite.R;
import hoang.deptrai.com.listenandwrite.algorithm.ObjectModel_To_DrawChart;
import hoang.deptrai.com.listenandwrite.algorithm.ToolString;
import hoang.deptrai.com.listenandwrite.data.Video;

public class AdapterChartItem extends ArrayAdapter<ObjectModel_To_DrawChart> {
    Activity context;
    int resource;
    @NonNull List<ObjectModel_To_DrawChart> objects;
    public AdapterChartItem(@NonNull Activity context, int resource, @NonNull List<ObjectModel_To_DrawChart> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(resource,null);

        TextView tvIndexOfTrack = item.findViewById(R.id.tvIndexOfTrack);
        TextView tvChartItemResult = item.findViewById(R.id.tvChartItemResult);
        ProgressBar progressBarChartItemResult = item.findViewById(R.id.progressBarChartItemResult);

        ObjectModel_To_DrawChart objectModel_to_drawChart = objects.get(position);

        ToolString toolString = new ToolString();
        tvIndexOfTrack.setText("Track: " + position+"");
        if(objectModel_to_drawChart!=null){
            tvChartItemResult.setText(toolString.colorize(
                    objectModel_to_drawChart.getSubLyric(),
                    objectModel_to_drawChart.getResult_index_array()
            ));
            progressBarChartItemResult.setProgress(objectModel_to_drawChart.getPercent_OfSimilarSubString());
        }else{
            tvChartItemResult.setText("Not Completed!");
            progressBarChartItemResult.setProgress(0);
        }
        return item;
    }
}
