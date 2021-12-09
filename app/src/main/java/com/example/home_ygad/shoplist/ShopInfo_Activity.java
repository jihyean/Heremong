package com.example.home_ygad.shoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_ygad.R;

public class ShopInfo_Activity extends AppCompatActivity {
    private Intent intent;
    ImageButton c_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        c_back = (ImageButton) findViewById(R.id.Btn_Back_press1);

        TextView tvName = (TextView)findViewById(R.id.tv_name2);
        TextView tvMsg = (TextView)findViewById(R.id.tv_msg2);
        ImageView iv2 = (ImageView) findViewById(R.id.iv2);

        Bitmap image;


        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("tvName"));
        tvMsg.setText(intent.getStringExtra("tvMsg"));
        byte[] arr = getIntent().getByteArrayExtra("tvImage");
        image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        iv2.setImageBitmap(image);

//        tvName.setText(intent.getStringExtra("tvName"));
//        tvName = intent.getStringExtra("tvName");
        c_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}