package hoang.deptrai.com.listenandwrite.data;

import java.util.ArrayList;

import hoang.deptrai.com.listenandwrite.R;

public class SimulateData {
    private final int NUMBER_OF_LEVEL = 9;
    private ArrayList<ArrayList<Video>> data;
    private String link1 = "BZsXcc_tC-o";
    private String link2 = "mYFaghHyMKc";
    private String link3 = "-wTLvPuFK7I";

    public SimulateData(){
    }
    public ArrayList<ArrayList<Video>> getManyListVideo() {

        data = new ArrayList<>();
        for(int i=0; i<NUMBER_OF_LEVEL; i++){
            data.add(new ArrayList<Video>());
        }

        ArrayList<Video> listVideo = data.get(0);
        listVideo.add(new Video("Take my hand",0,"Unknow",link3));
        listVideo.add(new Video("Perfect",0,"Ed Sheeran","2Vv-BfVoq4g"));
        listVideo.add(new Video("A Thousand year",0,"Christina Perri","rtOvBOTyX00"));
        listVideo.add(new Video("Until you",0,"Shayne Ward","YfDqONbzYPc"));

        listVideo = data.get(1);
        listVideo.add(new Video("Photograph",1,"Ed Sheeran","nSDgHBxUbVQ"));
        listVideo.add(new Video("Why not me",1,"Enrique Iglesias","GyrH6xiFiT0"));
        listVideo.add(new Video("Let her go",1,"Passenger","RBumgq5yVrA"));

        listVideo = data.get(2);
        listVideo.add(new Video("Counting star",2,"OneRepublic","hT_nvWreIhg"));
        listVideo.add(new Video("Stress out",2,"twenty one pilots","pXRviuL6vMY"));
        listVideo.add(new Video("Stereo hearts",2,"Adam Levine","T3E9Wjbq44E"));


        return data;
    }
}
