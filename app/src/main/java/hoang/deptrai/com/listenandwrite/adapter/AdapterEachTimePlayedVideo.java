package hoang.deptrai.com.listenandwrite.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hoang.deptrai.com.listenandwrite.R;

public class AdapterEachTimePlayedVideo extends ArrayAdapter<Integer> {
    Activity context;
    int resource;
    @NonNull
    List<String> objects;
    public AdapterEachTimePlayedVideo(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(resource,null);

        TextView tvTrackNumber = view.findViewById(R.id.tvTimeNumberForVideo);
        TextView tvPercentEachTimePlayed = view.findViewById(R.id.tvPercentForVideo);
        ProgressBar progressBarEachTimePlayed = view.findViewById(R.id.progressBarForVideo);

        int percent = Integer.parseInt(objects.get(position));

        tvTrackNumber.setText("Time: "+position);
        tvPercentEachTimePlayed.setText(percent+"%");
        progressBarEachTimePlayed.setProgress(percent);

        return view;
    }
}
