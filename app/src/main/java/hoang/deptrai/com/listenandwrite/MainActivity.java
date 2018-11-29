package hoang.deptrai.com.listenandwrite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import hoang.deptrai.com.listenandwrite.adapter.AdapterVideo;
import hoang.deptrai.com.listenandwrite.data.SimulateData;
import hoang.deptrai.com.listenandwrite.data.Video;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerLevel;
    ListView lvVideo;
    ArrayList<ArrayList<Video>> data;

    private int NOW_LEVEL = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NOW_LEVEL = position;
                updateListView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startStudyActivity(position);
            }

            private void startStudyActivity(int position) {
                Video video = data.get(NOW_LEVEL).get(position);
                Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                intent.putExtra("VIDEO",video);
                startActivity(intent);
            }
        });
    }
    private void updateListView(int level){
        ArrayList<Video> listVideo = data.get(level);
        AdapterVideo adapterVideo = new AdapterVideo(MainActivity.this, R.layout.item_video,listVideo);
        adapterVideo.notifyDataSetChanged();
        lvVideo.setAdapter(adapterVideo);
    }
    private void addControls() {
        //spinner
        spinnerLevel = findViewById(R.id.spinnerLevel);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                                R.array.level_array,
                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapter);

        //list view
        lvVideo = findViewById(R.id.lvVideo);
        //data
        SimulateData simulateData = new SimulateData();
        data = simulateData.getManyListVideo();
    }

}
