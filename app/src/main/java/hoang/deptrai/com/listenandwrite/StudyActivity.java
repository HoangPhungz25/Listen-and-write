package hoang.deptrai.com.listenandwrite;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import hoang.deptrai.com.listenandwrite.algorithm.ObjectModel_To_DrawChart;
import hoang.deptrai.com.listenandwrite.algorithm.ToolString;
import hoang.deptrai.com.listenandwrite.data.KEY;
import hoang.deptrai.com.listenandwrite.data.Video;

public class StudyActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer player;
    ImageButton btPrevious, btNext, btCheck;
    Spinner spinnerSelectTrack;
    EditText etAnswer;
    TextView tvResult;

    Handler handlerParent,handler;
    Compare2StringArray compare2StringArray;
    ToolString toolString;

    private Video now_video;
    private int index_selected_track=0;
    private int[] track_start_array, track_end_array;
    private String[] subLyric_array;
    private ObjectModel_To_DrawChart[] objectModel_To_DrawChart_Array;
    private int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        //set push up content when keyboard is showing
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        Intent intent = getIntent();
        now_video = (Video) intent.getSerializableExtra("VIDEO");

        toolString = new ToolString();
        track_start_array = toolString.split_string_to_int_array(now_video.getTrack_start_array());
        track_end_array = toolString.split_string_to_int_array(now_video.getTrack_end_array());
        subLyric_array = toolString.split_lyrics(now_video.getLyrics());

//        Log.d("data_intent",player.toString());
//        Log.d("data_intent",now_video.getTrack_end_array());
//        Log.d("data_intent",now_video.getTrack_start_array());
//        Log.d("data_intent",now_video.getLyrics());

        Log.d("data_intent","Start: addControls()");
        addControls();
    }

    private void addEvents() {
        Log.d("data_intent","Start: addEvents()");

        spinnerSelectTrack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("data_intent","Start: item onSelected()");
                index_selected_track = position;
                updateEditextAndTextView();
                repeatPlay(track_start_array[position],track_end_array[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("handler","Start Check");
                objectModel_To_DrawChart_Array[index_selected_track] = compare2StringArray.compare2StringArray(
                                                                            etAnswer.getText().toString(),
                                                                            subLyric_array[index_selected_track]);
                totalScore += objectModel_To_DrawChart_Array[index_selected_track].getPercent_OfSimilarSubString();
                tvResult.setText(toolString.colorize(
                                                subLyric_array[index_selected_track],
                                                objectModel_To_DrawChart_Array[index_selected_track].getResult_index_array()
                                                ));
                Toast.makeText(StudyActivity.this, "Checked : ", Toast.LENGTH_LONG).show();
                Toast.makeText(StudyActivity.this,
                        objectModel_To_DrawChart_Array[index_selected_track].getPercent_OfSimilarSubString()+"",
                        Toast.LENGTH_SHORT).show();
                Log.d("handler","End Check");

                if(index_selected_track==5) drawChart();
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if there is at least one more track, move to the next track
                if(spinnerSelectTrack.getSelectedItemPosition()<track_start_array.length-1){
                    spinnerSelectTrack.setSelection(spinnerSelectTrack.getSelectedItemPosition()+1);
                    updateEditextAndTextView();
                }
            }
        });
        btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if there is at least an track before, move to previous track
                if(spinnerSelectTrack.getSelectedItemPosition()>0){
                    spinnerSelectTrack.setSelection(spinnerSelectTrack.getSelectedItemPosition()-1);
                    updateEditextAndTextView();
                }
            }
        });
    }

    private void drawChart() {
        Intent intent = new Intent(StudyActivity.this, ChartResultActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("_BUNDLE_CHART_DATA",objectModel_To_DrawChart_Array);
        intent.putExtra("CHART_DATA", objectModel_To_DrawChart_Array);
        Log.d("intent_chart","putExtra");
        startActivity(intent);
        Log.d("intent_chart","start Activity");
    }

    private void updateEditextAndTextView(){
        etAnswer.setText("");
        tvResult.setText("");
    }
    private void repeatPlay(final int start, final int end){
        Log.d("data_intent","Start: repeatPlay()");
        //remove all runnable is being in queue, thanks for stack over flow <3
        handler.removeCallbacksAndMessages(null);
        handlerParent.removeCallbacksAndMessages(null);
        Log.d("data_intent","Start: seek()");
//        Log.d("data_intent",player.toString());
        player.seekToMillis(start);
        player.play();
        Log.d("data_intent","Start: postDelay()");

        handlerParent.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
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
                    }
                },1000);
            }
        },1000);
    }

    private void createSpinner(){
        String[] number_of_track_array_string = new String[track_start_array.length];
        for(int i=0; i< number_of_track_array_string.length; i++){
            number_of_track_array_string[i] = i+"";
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item ,number_of_track_array_string);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectTrack.setAdapter(arrayAdapter);
    }
    private void addControls() {
        objectModel_To_DrawChart_Array = new ObjectModel_To_DrawChart[track_end_array.length];

        //handler
        handlerParent = new Handler();
        handler = new Handler();
        //Comparator
        compare2StringArray = new Compare2StringArray();
        //toolString to split string

        //button
        btPrevious = findViewById(R.id.btPreviousTrack);
        btNext = findViewById(R.id.btNextTrack);
        btCheck = findViewById(R.id.btCheck);
        etAnswer = findViewById(R.id.etAnswer);
        tvResult = findViewById(R.id.tvResult);

        //spinner
        spinnerSelectTrack = findViewById(R.id.spinnerSelectTrack);
        createSpinner();

        youTubePlayerView = findViewById(R.id.youtubeView);
        player = null;

        Log.d("data_intent","Start: initialize Youtube");
        youTubePlayerView.initialize(KEY.YOUTUBE_API_KEY,this);
        Log.d("data_intent","Start: initialize Youtube done");

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        if(!b){
            Log.d("data_intent","Start");
            player = youTubePlayer;
        Log.d("data_intent","Started");
        player.cueVideo(now_video.getId_video());
        Log.d("data_intent","Cue");
        //i have to call addEvent() here because if i put addControl(); then addEvents() right after it, onItemListener() may call
        // repeatPlay(), and repeatPlay() call player.seekTo() before onInitializationSuccess() is call,at that time
        // player instance of  YoutubePlayer has not been created yet, so it may cause null pointer exception, seekTo() on null reference.
        // To prevent that, i put addEvents() in the last of onInitializationSuccess(), so that the player.seekTo() will always be
        // call after the player has been initialized
        addEvents();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        Log.d("data_intent","Initialization failure");
        youTubePlayerView.initialize(KEY.YOUTUBE_API_KEY,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handlerParent.removeCallbacksAndMessages(null);
    }
}
//task: compare2string should return index_similar_substring_array not spannableString_array.
//do that, then caculate score base on that returned int[]
//pass it to chart activity, draw chart and show list answer