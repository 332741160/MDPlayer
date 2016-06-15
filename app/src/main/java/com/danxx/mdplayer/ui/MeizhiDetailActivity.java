package com.danxx.mdplayer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.danxx.mdplayer.R;
import com.squareup.picasso.Picasso;

/**
 * 妹纸图片详情
 * danxingxi
 */
public class MeizhiDetailActivity extends AppCompatActivity {
    private PhotoView photoView;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi_detail);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        photoView = (PhotoView) findViewById(R.id.img);
        // 启用图片缩放功能
        photoView.enable();
        // 禁用图片缩放功能 (默认为禁用，会跟普通的ImageView一样，缩放功能需手动调用enable()启用)
//        photoView.disenable();
        // 获取图片信息
        Info info = photoView.getInfo();
        // 从普通的ImageView中获取Info
        //Info info = PhotoView.getImageViewInfo(ImageView);
        // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
        photoView.animaFrom(info);
        // 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
        photoView.animaTo(info, new Runnable() {
            @Override
            public void run() {
                //动画完成监听
            }
        });
        // 获取/设置 动画持续时间
        photoView.setAnimaDuring(200);
        int d = photoView.getAnimaDuring();
        // 获取/设置 最大缩放倍数
        photoView.setMaxScale( 4.0f );
        float maxScale = photoView.getMaxScale();
        // 设置动画的插入器
//        photoView.setInterpolator(Interpolator interpolator);
        if(!TextUtils.isEmpty(url)){
            Picasso.with(MeizhiDetailActivity.this).load("http://tnfs.tngou.net/image"+url).into(photoView);
        }
    }
}
