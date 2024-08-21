package com.example.compose;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.DownloadBlock;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SingleDownload extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 100;

    private View mainView;
    private TextView progressTextView;
    private TextView titleTextView;
    private TextView etaTextView;
    private TextView downloadSpeedTextView;
    private Request request;
    private Fetch fetch;
    public static final String[] sampleUrls = new String[]{
            "http://speedtest.ftp.otenet.gr/files/test100Mb.db",
            "https://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_720p_stereo.avi",
            "http://media.mongodb.org/zips.json",
            "http://www.exampletonyotest/some/unknown/123/Errorlink.txt",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Android_logo_2019.svg/687px-Android_logo_2019.svg.png",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(this)
                .setDownloadConcurrentLimit(3)
                .build();

        fetch = Fetch.Impl.getInstance(fetchConfiguration);

        String url = "http:www.example.com/test.txt";
        String file = "/downloads/test.txt";

        final Request request = new Request(url, file);
        request.setPriority(Priority.HIGH);
        request.setNetworkType(NetworkType.ALL);
        request.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG");

        fetch.enqueue(request, updatedRequest -> {
            //Request was successfully enqueued for download.
        }, error -> {
            //An error occurred enqueuing the request.
        });

    }


    FetchListener fetchListener = new FetchListener() {
        @Override
        public void onQueued(@NonNull Download download, boolean b) {

        }

        @Override
        public void onWaitingNetwork(@NonNull Download download) {

        }

        @Override
        public void onStarted(@NonNull Download download, @NonNull List<? extends DownloadBlock> list, int i) {

        }

        @Override
        public void onError(@NonNull Download download, @NonNull Error error, @Nullable Throwable throwable) {

        }

        @Override
        public void onDownloadBlockUpdated(@NonNull Download download, @NonNull DownloadBlock downloadBlock, int i) {

        }

        @Override
        public void onAdded(@NonNull Download download) {

        }


        @Override
        public void onCompleted(@NotNull Download download) {

        }

        @Override
        public void onProgress(@NotNull Download download, long etaInMilliSeconds, long downloadedBytesPerSecond) {
            if (request.getId() == download.getId()) {
                updateDownload(download, etaInMilliSeconds);
            }
            int progress = download.getProgress();
        }

        private void updateDownload(Download download, long etaInMilliSeconds) {
        }

        @Override
        public void onPaused(@NotNull Download download) {

        }

        @Override
        public void onResumed(@NotNull Download download) {

        }

        @Override
        public void onCancelled(@NotNull Download download) {

        }

        @Override
        public void onRemoved(@NotNull Download download) {

        }

        @Override
        public void onDeleted(@NotNull Download download) {

        }
    };


//Remove listener when done.


}