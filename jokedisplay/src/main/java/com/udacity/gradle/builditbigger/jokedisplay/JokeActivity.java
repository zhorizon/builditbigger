package com.udacity.gradle.builditbigger.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            joke = bundle.getString("key_joke");
        }

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(joke);
    }
}
