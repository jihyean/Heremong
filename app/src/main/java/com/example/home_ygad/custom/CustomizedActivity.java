package com.example.home_ygad.custom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.home_ygad.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomizedActivity extends AppCompatActivity {

    //해야할 일 - 1. DogProfile php에서 id 뭐로해야 뜨는지 봐야함
    //         - 2. 전용 어댑터로 하단 프라그먼트가 바뀌도록 해야하는데 지금 생각해놓은건 누를때마다 프라그먼트를 새로 호출하고, 프라그먼트에서도 리퀘스트를 넣는...
    //               근데 리퀘스트를 넣을때 map으로 쏴줘야 하는것이 있으면 리퀘스트 클래스를 따로 만드는것이 좋음. id로 구분한다면 리퀘스트로 해야함
    //
    //   3. 딴거긴 한데 필터 검색 검색결과도 확인하면서 약간 수정이 필요함 ex) 필터설정없이 검색하기, 뭐뭐하기
    //   4. 찜 만들기. like unlike가 바로바로 인식이 되는건지 확인해야함 ㄹㅇ 주말내로 시작해야함
    //   5. 세부페이지에 가게 갈래 넣기. 이미지2 넣기

    RecyclerView recyclerView;

    ArrayList<dogProfile_Item> items = new ArrayList<>();

    CustomizeAdapter adapter;

    EditText search_bar;

    Button btn_customintent;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
     Customized_Fragment customized_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized);

        String ID ="id";

        recyclerView = findViewById(R.id.recycler_dogprofile);
        adapter = new CustomizeAdapter(this, items);
        recyclerView.setAdapter(adapter);
        search_bar = findViewById(R.id.search_bar2);

        //리사이클러뷰의 레이아웃 매니저 설정Caller.run(ZygoteInit.java:866)
        //        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:756)
        //D/EGL_emulation: eglMakeCurrent: 0x9d0bb0a0: ver 2 0 (tinfo 0x9b3b0530)
        //D/EGL_emulation: eglMakeCurrent: 0x9d0bb0a0: ver 2 0 (tinfo 0x9b3b0530)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        fragmentManager = getSupportFragmentManager();
//        Intent intent = getIntent();
//        intent.getStringExtra("Dog_id");
//        String Dog_id = intent.getStringExtra("Dog_id");
//
//        customized_fragment = new Customized_Fragment();
//        String Dog_id = "1";
//                Bundle bundle = new Bundle();
//        bundle.putString("Dog_id", Dog_id);
//        customized_fragment.setArguments(bundle);
//
//
//        Toast.makeText(CustomizedActivity.this, "열림", Toast.LENGTH_SHORT).show();



//        btn_customintent=findViewById(R.id.btn_customintent);
//
//        btn_customintent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = getIntent();
//                intent.getStringExtra("Dog_id");
//                String Dog_id = intent.getStringExtra("Dog_id");
//
//                Toast.makeText(CustomizedActivity.this, Dog_id, Toast.LENGTH_SHORT).show();
//
//                customized_fragment = new Customized_Fragment();
//
//
//
//                Bundle bundle = new Bundle();
//                bundle.putString("Dog_id", Dog_id);
//                customized_fragment.setArguments(bundle);
//
//            }
//        });

        customized_fragment = new Customized_Fragment();


        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.custom_result, customized_fragment).commitAllowingStateLoss();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(CustomizedActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                //불러들이는지 확인하기 위해 해당내용을 토스트메세지로 출력하도록 함(개발편의를 위한 것)
                System.out.println("답변옴");
                items.clear(); //일단 깨끗하게 처리함
                adapter.notifyDataSetChanged();
                try {
                    JSONArray dogarray = new JSONArray(response);
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject = dogarray.getJSONObject(i);

                        String Dog_id = jsonObject.getString("Dog_id");

                        String D_name = jsonObject.getString("D_name");

                        String D_sex =jsonObject.getString("D_sex");
                        String D_sav = jsonObject.getString("D_sav");
                        String D_kg = jsonObject.getString("D_kg"); //no가 문자열이라서 바꿔야함.



                        items.add(0, new dogProfile_Item(Dog_id,D_name, D_sex, D_sav, D_kg)); //
                        adapter.notifyItemInserted(0); //      얘는 삽입된 item(여기선 고유명사 item 임)이 있다는걸 알려주는것

//
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        // 서버로 Volley를 이용해서 요청을 함.
        CustomizedRequest customizedRequest = new CustomizedRequest(ID,responseListener );
        RequestQueue queue = Volley.newRequestQueue(CustomizedActivity.this);
        queue.add(customizedRequest);
    }
}




