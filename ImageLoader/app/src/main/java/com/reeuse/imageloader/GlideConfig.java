package com.reeuse.imageloader;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by User on 9/9/2015.
 */
public class GlideConfig implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // set the options to the builder as PREFER_ARGB_8888 instead of PREFER_RGB_565 for better image quality
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}