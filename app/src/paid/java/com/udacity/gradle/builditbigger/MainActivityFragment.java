package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.jokedisplay.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.Callback {
    private ProgressBar spinner;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        spinner = (ProgressBar) root.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        return root;
    }

    public void tellJoke(View view){
        new EndpointsAsyncTask(this).execute(new Pair<Context, String>(getActivity(), "test"));
    }

    @Override
    public void onPreExecute() {
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFetchJokeFinished(String joke) {
        spinner.setVisibility(View.GONE);

        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra("key_joke", joke);
        startActivity(intent);
    }

}
