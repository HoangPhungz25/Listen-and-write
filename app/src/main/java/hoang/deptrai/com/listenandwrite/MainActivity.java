package hoang.deptrai.com.listenandwrite;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;

import hoang.deptrai.com.listenandwrite.adapter.AdapterVideo;
import hoang.deptrai.com.listenandwrite.data.Database;
import hoang.deptrai.com.listenandwrite.data.Database_Query;
import hoang.deptrai.com.listenandwrite.data.SimulateData_to_SQLiteDatabase;
import hoang.deptrai.com.listenandwrite.data.Video;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerLevel;
    ListView lvVideo;
    ArrayList<ArrayList<Video>> data;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    public static Database database;

    private int NOW_LEVEL = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        actionBar.setHomeAsUpIndicator(R.drawable.nav_drawer_button);

        addControls();
        addEvents();
    }

    private void addEvents() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(navigationView);

                switch (item.getItemId()){
                    case R.id.nav_level:
                        for (Fragment fragment:getSupportFragmentManager().getFragments()) {
                            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        }
                        android.app.Fragment fragment = getFragmentManager().findFragmentByTag("fragment_chart_for_video");
                        Log.d("fragment","get by tag: "+fragment);
                        if(fragment!=null)  getFragmentManager().beginTransaction().remove(fragment).commit();
                        spinnerLevel.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_about_us:
                        getSupportFragmentManager().
                                beginTransaction().
                                add(R.id.fragment_container,new FragmentAboutUs()).addToBackStack("about_us").commit();
                        spinnerLevel.setVisibility(View.GONE);
                        break;
                    case R.id.nav_login:
                        getSupportFragmentManager().
                                beginTransaction().
                                add(R.id.fragment_container,new FragmentLogin()).addToBackStack("login").commit();
                        spinnerLevel.setVisibility(View.GONE);
                        break;
                }
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
                Log.d("click","clicked");
                if(data!=null)startStudyActivity(position);
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
        new SimulateData_to_SQLiteDatabase(this);
        database = new Database(new Database_Query(this,"LAW_databaseQuery.sql",null, 1));
        data = database.getDataFromVideoTable();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("back","pressed");
        getFragmentManager().popBackStack();
        Log.d("back","popped");
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = database.getDataFromVideoTable();
        updateListView(NOW_LEVEL);
    }
}
//add update database when onResume*********************************
//fix password edit text to password style********************