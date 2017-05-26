package com.cxp.mrr.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cxp.mrr.other.GlideCircleTransform;
import com.cxp.mrr.other.GlideRoundTransform;

import java.io.File;

/**
 * 文 件 名: ImageUtils
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 6:08
 * 描    述: 图片工具类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class ImageUtils {


    /**
     * 显示图片 默认有缓存
     *
     * @param context
     * @param url
     * @param img
     */
    public static void showImg(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .thumbnail(0.1f)
                .into(img);
    }

    /**
     * 显示图片 默认有缓存
     *
     * @param context
     * @param file
     * @param img
     */
    public static void showImg(Context context, File file, ImageView img) {
        Glide.with(context)
                .load(file)
                .centerCrop()
                .thumbnail(0.1f)
                .into(img);
    }

    /**
     * 显示圆形图片
     *
     * @param context
     * @param url
     * @param img
     */
    public static void showImgCircle(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .thumbnail(0.1f)
                .bitmapTransform(new GlideCircleTransform(context))
                .into(img);
    }

    /**
     * 显示圆形图片
     *
     * @param context
     * @param file
     * @param img
     */
    public static void showImgCircle(Context context, File file, ImageView img) {
        Glide.with(context)
                .load(file)
                .centerCrop()
                .thumbnail(0.1f)
                .bitmapTransform(new GlideCircleTransform(context))
                .into(img);
    }

    /**
     * 显示圆角图片
     *
     * @param context
     * @param url
     * @param img
     */
    public static void showImgRound(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .thumbnail(0.1f)
                .bitmapTransform(new GlideRoundTransform(context))
                .into(img);
    }

    /**
     * 显示圆角图片
     *
     * @param context
     * @param file
     * @param img
     */
    public static void showImgRound(Context context, File file, ImageView img) {
        Glide.with(context)
                .load(file)
                .centerCrop()
                .thumbnail(0.1f)
                .bitmapTransform(new GlideRoundTransform(context))
                .into(img);
    }
}
