package com.reeuse.imageloader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

public class PicassoActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        imageView = (ImageView) findViewById(R.id.image_view);
        loadBtn = (Button) findViewById(R.id.load_btn);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
    }

    private void loadImage() {
        String url;
        //Reference link - http://square.github.io/picasso/
        //url = "https://raw.githubusercontent.com/RajivManivannan/ImageLoader/master/lollipop_1536x1158_318KB.jpg";
        url = "https://raw.githubusercontent.com/RajivManivannan/ImageLoader/master/lollipop_1536x1158_318KB_webp.webp";
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int width = 100, height = 100;
        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        picassoBuilder.indicatorsEnabled(true);
        picassoBuilder.memoryCache(new LruCache(maxMemory / 8));
        picassoBuilder.build();
        Picasso.with(this)
                .load(url)
                .placeholder(R.color.placeholder_color)
                .error(R.drawable.error_image)
                        //.resize(width,height)
                .config(Bitmap.Config.ARGB_8888)
                .fit()
                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
