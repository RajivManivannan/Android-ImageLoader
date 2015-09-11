package com.reeuse.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;

public class GlideActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button loadBtn;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);


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
        //Reference link - https://github.com/bumptech/glide/wiki/Configuration
         //url = "https://raw.githubusercontent.com/RajivManivannan/ImageLoader/master/lollipop_1536x1158_318KB.jpg";
         url = "https://raw.githubusercontent.com/RajivManivannan/ImageLoader/master/lollipop_1536x1158_318KB_webp.webp";
        //url = "https://raw.githubusercontent.com/RajivManivannan/ImageLoader/master/android_bot_500x470_140KB_gif.gif";
        /*For custom config */
        GlideBuilder glideBuilder = new GlideBuilder(this);
        // To set the space for Disk cache.
        glideBuilder.setDiskCache(
                new InternalCacheDiskCacheFactory(this, DISK_CACHE_SIZE));
         int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        glideBuilder.setMemoryCache(new LruResourceCache(maxMemory / 8));
        // To set the directory location for cache
      /*  glideBuilder.setDiskCache(
                new InternalCacheDiskCacheFactory(this, "Glide", maxMemory/8));*/

        Glide.with(this).load(url)
                //.asGif()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.placeholder_color)
                .error(R.drawable.error_image)
                .centerCrop()
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
