package hoang.deptrai.com.listenandwrite.data;

import java.util.ArrayList;

import hoang.deptrai.com.listenandwrite.R;

public class SimulateData {
    private final int NUMBER_OF_LEVEL = 9;
    private ArrayList<ArrayList<Video>> data;

    public SimulateData(){
    }
    public ArrayList<ArrayList<Video>> getManyListVideo() {

        data = new ArrayList<>();
        for(int i=0; i<NUMBER_OF_LEVEL; i++){
            data.add(new ArrayList<Video>());
        }

        int exam_track_start_array[] = new int[5];
        int exam_track_end_array[] = new int[5];
        exam_track_start_array[0] = 1000; exam_track_end_array[0] = 5000;
        exam_track_start_array[1] = 6000; exam_track_end_array[1] = 12000;
        exam_track_start_array[2] = 16000; exam_track_end_array[2] = 18000;
        exam_track_start_array[3] = 50000; exam_track_end_array[3] = 60000;
        exam_track_start_array[4] = 80000; exam_track_end_array[4] = 83000;

        ArrayList<Video> listVideo = data.get(0);
//        listVideo.add(new Video("Take my hand",0,"Unknow","-wTLvPuFK7I",exam_track_start_array,exam_track_end_array));
//        listVideo.add(new Video("Perfect",0,"Ed Sheeran","2Vv-BfVoq4g",exam_track_start_array,exam_track_end_array));
//        listVideo.add(new Video("A Thousand year",0,"Christina Perri","rtOvBOTyX00",exam_track_start_array,exam_track_end_array));
//        listVideo.add(new Video("Until you",0,"Shayne Ward","YfDqONbzYPc",exam_track_start_array,exam_track_end_array));
//
//        listVideo = data.get(1);
//        listVideo.add(new Video("Photograph",1,"Ed Sheeran","nSDgHBxUbVQ",exam_track_start_array,exam_track_end_array));
//        listVideo.add(new Video("Why not me",1,"Enrique Iglesias","GyrH6xiFiT0",exam_track_start_array,exam_track_end_array));
//        listVideo.add(new Video("Let her go",1,"Passenger","RBumgq5yVrA",exam_track_start_array,exam_track_end_array));
//
//        listVideo = data.get(2);
//        listVideo.add(new Video("Counting star",2,"OneRepublic","hT_nvWreIhg",exam_track_start_array,exam_track_end_array));
//        listVideo.add(new Video("Stress out",2,"twenty one pilots","pXRviuL6vMY",exam_track_start_array,exam_track_end_array));
//        listVideo.add(new Video("Stereo hearts",2,"Adam Levine","T3E9Wjbq44E",exam_track_start_array,exam_track_end_array));
//
//        ArrayList<Video> listVideo = data.get(0);
//        listVideo.add(new Video("Take my hand",0,"Unknow","-wTLvPuFK7I"));
//        listVideo.add(new Video("Perfect",0,"Ed Sheeran","2Vv-BfVoq4g"));
//        listVideo.add(new Video("A Thousand year",0,"Christina Perri","rtOvBOTyX00"));
//        listVideo.add(new Video("Until you",0,"Shayne Ward","YfDqONbzYPc"));
//
//        listVideo = data.get(1);
//        listVideo.add(new Video("Photograph",1,"Ed Sheeran","nSDgHBxUbVQ"));
//        listVideo.add(new Video("Why not me",1,"Enrique Iglesias","GyrH6xiFiT0"));
//        listVideo.add(new Video("Let her go",1,"Passenger","RBumgq5yVrA"));
//
//        listVideo = data.get(2);
//        listVideo.add(new Video("Counting star",2,"OneRepublic","hT_nvWreIhg"));
//        listVideo.add(new Video("Stress out",2,"twenty one pilots","pXRviuL6vMY"));
//        listVideo.add(new Video("Stereo hearts",2,"Adam Levine","T3E9Wjbq44E"));


        return data;
    }
}
