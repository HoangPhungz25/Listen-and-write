package hoang.deptrai.com.listenandwrite;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.List;

import hoang.deptrai.com.listenandwrite.algorithm.Compare2StringArray;
import hoang.deptrai.com.listenandwrite.data.KEY;
import hoang.deptrai.com.listenandwrite.data.Video;

public class StudyActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer player = null;
    ImageButton btPrevious, btNext, btCheck;
    Spinner spinnerSelectTrack;
    EditText etAnswer;
    TextView tvResult;

    Handler handlerParent,handler;
    Compare2StringArray compare2StringArray;

    private Video now_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        Intent intent = getIntent();
        now_video = (Video) intent.getSerializableExtra("VIDEO");

        addControls();
        addEvents();

    }

    private void addEvents() {
        spinnerSelectTrack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StudyActivity.this, now_video.getTrack_start_array()[position]+"", Toast.LENGTH_SHORT).show();
                repeatPlay(now_video.getTrack_start_array()[position],now_video.getTrack_end_array()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("handler","Start Check");
                tvResult.setText(compare2StringArray.
                                    compare2StringArray(etAnswer.getText().
                                            toString(),
                                            "Hello babes my love"));
                Toast.makeText(StudyActivity.this, "Checked", Toast.LENGTH_LONG).show();
                Log.d("handler","End Check");
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if there is at least one more track, move to the next track
                if(spinnerSelectTrack.getSelectedItemPosition()<now_video.getTrack_start_array().length-1)
                spinnerSelectTrack.setSelection(spinnerSelectTrack.getSelectedItemPosition()+1);
            }
        });
        btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if there is at least an track before, move to previous track
                if(spinnerSelectTrack.getSelectedItemPosition()>0)
                    spinnerSelectTrack.setSelection(spinnerSelectTrack.getSelectedItemPosition()-1);
            }
        });
    }

    private void repeatPlay(final int start, final int end){
        //remove all runnable is being in queue, thanks for stack over flow <3
        handlerParent.removeCallbacksAndMessages(null);
        handler.removeCallbacksAndMessages(null);

        player.seekToMillis(start);
        player.play();

        handlerParent.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        if(!IS_RUNNING){
//                            handler.removeCallbacks(this);
//                        }else{
                            if(player.getCurrentTimeMillis()<end){
                                handler.postDelayed(this, 1000);
                                Log.d("handler",player.getCurrentTimeMillis()+"-"+start+":"+end);
                            }
                            else{
                                player.seekToMillis(start);
                                handler.removeCallbacks(this);
                                Log.d("handler","Repeat");

                                handlerParent.postDelayed(this, 1000);
                            }
//                        }
                    }
                },1000);
            }
        },1000);


    }

        private void createSpinner(){
        String[] number_of_track_array_string = new String[now_video.getTrack_start_array().length];
        for(int i=0; i< number_of_track_array_string.length; i++){
            number_of_track_array_string[i] = i+"";
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,number_of_track_array_string);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectTrack.setAdapter(arrayAdapter);
    }
    private void addControls() {
        youTubePlayerView = findViewById(R.id.youtubeView);
        player = null;
        youTubePlayerView.initialize(KEY.YOUTUBE_API_KEY,this);

        //handler
        handlerParent = new Handler();
        handler = new Handler();
        //Comparator
        compare2StringArray = new Compare2StringArray();

        //button
        btPrevious = findViewById(R.id.btPreviousTrack);
        btNext = findViewById(R.id.btNextTrack);
        btCheck = findViewById(R.id.btCheck);
        etAnswer = findViewById(R.id.etAnswer);
        tvResult = findViewById(R.id.tvResult);

        //spinner
        spinnerSelectTrack = findViewById(R.id.spinnerSelectTrack);
        createSpinner();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handlerParent.removeCallbacksAndMessages(null);
    }
}
