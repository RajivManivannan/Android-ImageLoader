package com.reeuse.imageloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.main_glide_btn)).setOnClickListener(this);
        (findViewById(R.id.main_picasso_btn)).setOnClickListener(this);
        (findViewById(R.id.main_fresco_btn)).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_glide_btn:
                startActivity(new Intent(MainActivity.this, GlideActivity.class));
                break;
            case R.id.main_picasso_btn:
                startActivity(new Intent(MainActivity.this, PicassoActivity.class));
                break;
            case R.id.main_fresco_btn:
                startActivity(new Intent(MainActivity.this, FrescoActivity.class));
                break;
        }
    }
}
