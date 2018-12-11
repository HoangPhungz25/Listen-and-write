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

import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import hoang.deptrai.com.listenandwrite.R;
import hoang.deptrai.com.listenandwrite.algorithm.ToolString;
import hoang.deptrai.com.listenandwrite.data.KEY;
import hoang.deptrai.com.listenandwrite.data.Video;
import hoang.deptrai.com.listenandwrite.youtubeThumbListener;

public class AdapterVideo extends ArrayAdapter<Video> {
    Activity context;
    int resource;
    @NonNull List<Video> objects;
    ToolString toolString = new ToolString();
    public AdapterVideo(@NonNull Activity context, int resource, @NonNull List<Video> objects) {
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

        YouTubeThumbnailView youtubeThumb = item.findViewById(R.id.youtubeThumb);
        TextView tvName = item.findViewById(R.id.tvName);
        TextView tvAuthor = item.findViewById(R.id.tvAuthor);
        TextView tvTimePlayed = item.findViewById(R.id.tvTimePlayed);
        ProgressBar progressBar = item.findViewById(R.id.progressBarTotalPercent);

        Video video = objects.get(position);

        youtubeThumb.initialize(KEY.YOUTUBE_API_KEY, new youtubeThumbListener(video.getId_video()));
        tvName.setText(video.getName().toString());
        tvAuthor.setText(video.getAuthor().toString());
        tvTimePlayed.setText(video.getTime_played_this_video()+"");
        progressBar.setProgress(video.getAvg_percent_of_all_time());

        return item;
    }
}
