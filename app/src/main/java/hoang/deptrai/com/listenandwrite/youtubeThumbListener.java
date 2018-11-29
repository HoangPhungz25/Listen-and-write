package hoang.deptrai.com.listenandwrite;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

    public class youtubeThumbListener implements YouTubeThumbnailView.OnInitializedListener{
        String id_video;
        public youtubeThumbListener(String id_video){
            this.id_video = id_video;
        }
        @Override
        public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

            youTubeThumbnailLoader.setVideo(id_video);
            youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        @Override
        public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
        }
    }
