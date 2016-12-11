package com.harpreet.moviereviews;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpVideoDialogFragment extends DialogFragment {

    VideoView videoView;

    public HelpVideoDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragmenthelpvideo, container, false);

        videoView = (VideoView) rootView.findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getActivity().getPackageName()+"/"+R.raw.movie_reviews_preview);
        videoView.setMediaController(new MediaController(getActivity()));
        videoView.requestFocus();
        videoView.start();
        return rootView;
    }


}
