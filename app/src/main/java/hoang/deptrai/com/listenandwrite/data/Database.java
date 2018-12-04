package hoang.deptrai.com.listenandwrite.data;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

//    task: create a database class that has functions:
//      *   get data from SQLite database then return arraylist<arraylist<string>>


public class Database {
        private final String table_name = "Video";
        private final int NUMBER_OF_LEVEL = 5;

        Database_Query databaseQuery;

        public Database( Database_Query database_query){
           this.databaseQuery = database_query;
        }

        public ArrayList<ArrayList<Video>> getDataFromVideoTable(){
            Cursor cursor = databaseQuery.SELECT_Data("SELECT * FROM "+table_name);

            ArrayList<ArrayList<Video>> data;
            data = new ArrayList<>();
            for(int i =0; i<NUMBER_OF_LEVEL; i++) data.add(new ArrayList<Video>()); //create 5 level

            while(cursor.moveToNext()){
                Video video = new Video(
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7));
                data.get(video.getLevel()).add(video); //add a new video from SQLite to its level
            }
            return data;
        }


}
