package hoang.deptrai.com.listenandwrite.data;

import android.content.Context;

import hoang.deptrai.com.listenandwrite.algorithm.ToolString;

public class SimulateData_to_SQLiteDatabase {
    int version = 1;
    public Database_Query databaseQuery;
    private final String table_name = "Video";
    ToolString toolString;
    public SimulateData_to_SQLiteDatabase(Context context){
        toolString = new ToolString();
        databaseQuery = new Database_Query(context,"LAW_databaseQuery.sql",null, version);
        databaseQuery.QueryData("CREATE TABLE IF NOT EXISTS Video(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(100)," +
                "level INTEGER not null," +
                "author VARCHAR(100)," +
                "id_video VARCHAR(50) not null," +
                "track_start_array VARCHAR(200) not null," +
                "track_end_array VARCHAR(200) not null," +
                "lyrics VARCHAR(550) not null)");
        /* inserted
        //lv1
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Take my hand",0,"Unknow","-wTLvPuFK7I",
                Some_TrackStartAndEndArray.trackStart_UntilYou,
                Some_TrackStartAndEndArray.trackEnd_UntilYou,
                Some_Lyrics.lyric_UntilYou));
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Perfect",0,"Ed Sheeran","2Vv-BfVoq4g",
                Some_TrackStartAndEndArray.trackStart_UntilYou,
                Some_TrackStartAndEndArray.trackEnd_UntilYou,
                Some_Lyrics.lyric_UntilYou));
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Until you",0,"Shayne Ward","YfDqONbzYPc",
                    Some_TrackStartAndEndArray.trackStart_UntilYou,
                    Some_TrackStartAndEndArray.trackEnd_UntilYou,
                    Some_Lyrics.lyric_UntilYou));
        //lv2
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Photograph",1,"Ed Sheeran","nSDgHBxUbVQ",
                Some_TrackStartAndEndArray.trackStart_UntilYou,
                Some_TrackStartAndEndArray.trackEnd_UntilYou,
                Some_Lyrics.lyric_UntilYou));
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Why not me",1,"Enrique Iglesias","GyrH6xiFiT0",
                Some_TrackStartAndEndArray.trackStart_UntilYou,
                Some_TrackStartAndEndArray.trackEnd_UntilYou,
                Some_Lyrics.lyric_UntilYou));
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Let her go",1,"Passenger","RBumgq5yVrA",
                Some_TrackStartAndEndArray.trackStart_UntilYou,
                Some_TrackStartAndEndArray.trackEnd_UntilYou,
                Some_Lyrics.lyric_UntilYou));
        //lv3
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Counting star",2,"OneRepublic","hT_nvWreIhg",
                Some_TrackStartAndEndArray.trackStart_UntilYou,
                Some_TrackStartAndEndArray.trackEnd_UntilYou,
                Some_Lyrics.lyric_UntilYou));
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Stress out",2,"twenty one pilots","pXRviuL6vMY",
                Some_TrackStartAndEndArray.trackStart_UntilYou,
                Some_TrackStartAndEndArray.trackEnd_UntilYou,
                Some_Lyrics.lyric_UntilYou));
        INSERT_Video_to_databaseQuery(databaseQuery, new Video("Stereo hearts",2,"Adam Levine","T3E9Wjbq44E",
                Some_TrackStartAndEndArray.trackStart_UntilYou,
                Some_TrackStartAndEndArray.trackEnd_UntilYou,
                Some_Lyrics.lyric_UntilYou));
                */
    }
    private void INSERT_Video_to_databaseQuery(Database_Query databaseQuery, Video video){
        databaseQuery.QueryData("INSERT INTO "+table_name+
                " VALUES (" +
                "null" +
                ",'"+video.getName()+
                "',"+video.getLevel()+
                ",'"+video.getAuthor()+
                "','"+video.getId_video()+
                "','"+video.getTrack_start_array()+
                "','"+video.getTrack_end_array()+
                "','"+toolString.standardize_lyrics(video.getLyrics())+"')");
    }
}
