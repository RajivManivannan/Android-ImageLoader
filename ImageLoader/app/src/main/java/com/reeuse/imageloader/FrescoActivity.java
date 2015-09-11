package com.reeuse.imageloader;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.HashSet;
import java.util.Set;

public class FrescoActivity extends AppCompatActivity {

    private SimpleDraweeView imageView;
    private Uri uri;
    private Button loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** For Fresco Advance customization config */
        Set<RequestListener> listeners = new HashSet<>();
        listeners.add(new RequestLoggingListener());
        // Refer doc for more detail: http://frescolib.org/docs/configure-image-pipeline.html
        /** For handle disk cache config */
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setRequestListeners(listeners)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
        // initialize  Fresco
        // Fresco.initialize(this);
        setContentView(R.layout.activity_fresco);

        imageView = (SimpleDraweeView) findViewById(R.id.image_view); // Fresco's SimpleDraweeView
        loadBtn = (Button) findViewById(R.id.load_btn);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });

    }

    private void loadImage() {
        //uri = Uri.parse("https://raw.githubusercontent.com/RajivManivannan/ImageLoader/master/lollipop_1536x1158_318KB.jpg");
        //uri = Uri.parse("https://raw.githubusercontent.com/RajivManivannan/ImageLoader/master/lollipop_1536x1158_318KB_webp.webp"); // WebP image support from android 2.3
        uri = Uri.parse("https://raw.githubusercontent.com/RajivManivannan/ImageLoader/master/android_bot_500x470_140KB_gif.gif");// Gif image

        //We can set the below config in xml as well as programmatically
        /*
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setPlaceholderImage(ResourcesCompat.getDrawable(getResources(), R.color.placeholder_color, null))

                .build();
        imageView.setHierarchy(hierarchy);*/

        /* Just to load image */
        // imageView.setImageURI(uri);
        int width = 100, height = 100;
        /* can set the Request related config*/
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                // .setResizeOptions(new ResizeOptions(width, height)) // To resize the image.
                .setProgressiveRenderingEnabled(false)// It will load image progressively from N/W.
                .setRequestPriority(Priority.HIGH) // priority.
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH) // Disk cache
                .setAutoRotateEnabled(true)
                .build();

        /* Can set controller like taptoretry, play animation etc.*/
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(true)// enable for Gif file
                .setTapToRetryEnabled(true)// To show retry image.
                .build();
        imageView.setController(controller);
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
