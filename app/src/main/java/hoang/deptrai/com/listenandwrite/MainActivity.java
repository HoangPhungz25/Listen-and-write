package hoang.deptrai.com.listenandwrite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import hoang.deptrai.com.listenandwrite.adapter.AdapterVideo;
import hoang.deptrai.com.listenandwrite.data.Database;
import hoang.deptrai.com.listenandwrite.data.SimulateData;
import hoang.deptrai.com.listenandwrite.data.SimulateData_to_SQLiteDatabase;
import hoang.deptrai.com.listenandwrite.data.Video;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerLevel;
    ListView lvVideo;
    ArrayList<ArrayList<Video>> data;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private int NOW_LEVEL = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.nav_drawer_button);

        addControls();
        addEvents();
    }

    private void addEvents() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_level:
                        Toast.makeText(MainActivity.this, "LEVEL", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about_us:
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.fragment_container,new FragmentLogin()).commit();
                        break;
                    case R.id.nav_login:
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.fragment_container,new FragmentLogin()).commit();
                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawer(navigationView);

                //UI change

                return true;
            }
        });
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
                Log.d("database",video.getName());
                intent.putExtra("VIDEO",video);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        //spinner
        spinnerLevel = findViewById(R.id.spinnerLevel);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                                R.array.level_array,
                                R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapter);

        //list view
        lvVideo = findViewById(R.id.lvVideo);

        //side bar navigation
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);


        //data
        /* No longer required, this is the old way to create data
            SimulateData simulateData = new SimulateData();
            data = simulateData.getManyListVideo();
         */
        SimulateData_to_SQLiteDatabase simulateData_to_sqLiteDatabase = new SimulateData_to_SQLiteDatabase(MainActivity.this);
        Log.d("database","created simulate database");
        Database database = new Database(simulateData_to_sqLiteDatabase.databaseQuery);
        data = database.getDataFromVideoTable();
        Log.d("database","got database to ArayList<ArrayList<>>");
    }
    private void updateListView(int level){
        ArrayList<Video> listVideo = data.get(level);
        AdapterVideo adapterVideo = new AdapterVideo(MainActivity.this, R.layout.item_video,listVideo);
        lvVideo.setAdapter(adapterVideo);
        adapterVideo.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
