package com.ruiyi.askandanswer.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/*
    Glide工具类
 */
public class GlideUtils {

    public static void loadImage(Context context,int res,ImageView imageView){
        Glide.with(context).load(res).into(imageView);
    }

    public static void loadImage(Context context, Object url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
}
