package hoang.deptrai.com.listenandwrite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import hoang.deptrai.com.listenandwrite.data.KEY;
import hoang.deptrai.com.listenandwrite.data.Video;

public class StudyActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer player;
    ImageButton btPrevious, btNext, btCheck;
    Spinner spinnerSelectTrack;
    EditText etAnswer;
    TextView tvResult;

    private Video now_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        addControls();

        Intent intent = getIntent();
        now_video = (Video) intent.getSerializableExtra("VIDEO");
    }
//    private void createSpinner(){
//        String[] track_array_string = new String[now_video.getTrackArray().];
//        for(int i=0; i< now_video.getTrackArray().length; i++){
//            tr
//        }
//        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, )
//    }
    private void addControls() {
        youTubePlayerView = findViewById(R.id.youtubeView);
        //button
        btPrevious = findViewById(R.id.btPreviousTrack);
        btNext = findViewById(R.id.btNextTrack);
        spinnerSelectTrack = findViewById(R.id.spinnerSelectTrack);
        btCheck = findViewById(R.id.btCheck);
        etAnswer = findViewById(R.id.etAnswer);
        tvResult = findViewById(R.id.tvResult);
        //youtube
        youTubePlayerView.initialize(KEY.YOUTUBE_API_KEY,this);
        player = null;
        //intent

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        player = youTubePlayer;
        player.cueVideo(now_video.getId_video());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }
}
