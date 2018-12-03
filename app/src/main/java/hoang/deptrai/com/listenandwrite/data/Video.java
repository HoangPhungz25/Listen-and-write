package hoang.deptrai.com.listenandwrite.data;


import java.io.Serializable;

public class Video implements Serializable{
    private String name;
    private int level;
    private String id_video;
    private String author;
    private int track_start_array[];
    private int track_end_array[];
    public Video(){

    }

    public Video(String name,int level,String author,String id_video, int track_start_array[], int track_end_array[]) {
        this.name = name;
        this.level = level;
        this.author = author;
        this.id_video = id_video;
        this.track_start_array = track_start_array;
        this.track_end_array = track_end_array;
    }

    public int[] getTrack_start_array() {
        return track_start_array;
    }

    public void setTrack_start_array(int[] track_start_array) {
        this.track_start_array = track_start_array;
    }

    public int[] getTrack_end_array() {
        return track_end_array;
    }

    public void setTrack_end_array(int[] track_end_array) {
        this.track_end_array = track_end_array;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getId_video() {
        return id_video;
    }

    public void setId_video(String link_video) {
        this.id_video = link_video;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
