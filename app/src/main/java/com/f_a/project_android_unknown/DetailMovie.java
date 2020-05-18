package com.f_a.project_android_unknown;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMovie extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_PRICE = "extra_price";
    public static final String EXTRA_DETAIL = "extra_detail";
    public static final String EXTRA_TIME = "extra_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);



        //--------|Panggil widget|--------
        ImageView imgPhoto = findViewById(R.id.img_item);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvPrice = findViewById(R.id.tv_price);
        TextView tvDetail = findViewById(R.id.tv_detail);
        TextView tvTime = findViewById(R.id.tv_item_time);

//--------| GET DATA |--------
        int photo = getIntent().getIntExtra(EXTRA_PHOTO,0);
        String name = getIntent().getStringExtra(EXTRA_NAME);
        String price = getIntent().getStringExtra(EXTRA_PRICE);
        String detail = getIntent().getStringExtra(EXTRA_DETAIL);
        String time = getIntent().getStringExtra(EXTRA_TIME);

//--------|PASANG|--------
        Glide.with(this).load(photo).into(imgPhoto);
        tvName.setText(name);
        tvPrice.setText(price);
        tvDetail.setText(detail);
        tvTime.setText(time);

        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle(name); // set the top title

    }
}
