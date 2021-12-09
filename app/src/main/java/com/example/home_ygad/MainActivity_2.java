package com.example.home_ygad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity_2 extends AppCompatActivity {

    RecyclerView recyclerView,phoneRecycler3;

    ArrayList<Item> items= new ArrayList<>();
    ArrayList<Item> items2= new ArrayList<>(); //혹시몰라 아이템(DB연동데이터)용 데이터 넣을 배열 새로만듦

    ItemAdapter adapter;
    adapterphone2 adapter2;//테스트용 수평리사이클러어댑터
    LinearLayoutManager layoutManager2=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
    //이건 레이아웃 매니저인데 this가  json 안에선 안돼서 여기서 선언해둠


    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BlankFragment1 blankFragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
//
//        recyclerView=findViewById(R.id.recycler);
        phoneRecycler3=findViewById(R.id.my_recycler_3);

        adapter= new ItemAdapter(this, items); //       데이터 넣을 배열을 미리 만듦,현재 비어있다
        recyclerView.setAdapter(adapter); //        이 문장에서 런타임오류가 나면 어댑터에 문제가있다는뜻

        adapter2 = new adapterphone2(this,items2); //테스트용 리사이클 어댑터
        //       이 adapter2는 매개변수가 3개. 컨택스트,배열,클릭리스너임. 3번째 매개변수는 아직 안넣음
        phoneRecycler3.setAdapter(adapter2);


        LinearLayoutManager layoutManager1=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager1);

        phoneRecycler3.setHasFixedSize(true); //해당 항목의 높이나 너비가 변경되지 않으며, 전부 동일하다. 이게 없으면 매번 다시 그려야함
        phoneRecycler3.setLayoutManager(layoutManager2);

        fragmentManager = getSupportFragmentManager();


        blankFragment1 = new BlankFragment1();
//
//
//        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.custom_result, fra).commitAllowingStateLoss();


    }

    public void onCustomToggleClick(View view) {
        boolean on =((ToggleButton)view).isChecked();

        if (on) {
            Toast.makeText(this, "토글 체크", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "토글 ", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickLoad(View view) {

        //서버의 loadDBtoJson.php파일에 접속하여 (DB데이터들)결과 받기
        //Volley+ 라이브러리 사용

        //서버주소
        String serverUrl="http://heremong.dothome.co.kr/PlaceList3.php";
        //      PlaceList3.php 를 사용, Place_id, P_name, P_address, P_image,P_image2 from Place

             //결과를 JsonArray 받을 것이므로..
             //StringRequest가 아니라..
             //JsonArrayRequest를 이용할 것임
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
                 //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                // Toast.makeText(MainActivity_2.this, response.toString(), Toast.LENGTH_SHORT).show();


                //      파라미터로 응답받은 결과 JsonArray를 분석

                items.clear(); //       말그대로 청소하는거임 버튼누르때마다 복사되면 안되니까 있는듯
                adapter.notifyDataSetChanged(); //      등록된 관찰자에게 데이터셋이 바뀜을 알린 다는데 어따쓰는거지?

                items2.clear();
                adapter2.notifyDataSetChanged();//      일단 따라서 넣어보자


                try {

                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        int Place_id= Integer.parseInt(jsonObject.getString("Place_id")); //no가 문자열이라서 바꿔야함.
                        String P_name=jsonObject.getString("P_name");
                        String P_address=jsonObject.getString("P_address");
                        String P_image=jsonObject.getString("P_image");
                        //         어...그러니까 이 clikload 안의 onResponce 함수에서 json으로 어케한 정보들을 여기서 사용가능한 string들로 바꿔준다?
                        //         근데 이미지는 어케 넣어지게 되는거야

                        //이미지 경로의 경우 서버 IP가 제외된 주소이므로(uploads/xxxx.jpg) 바로 사용 불가.
                        //P_image = "http://heremong.dothome.co.kr/"+P_image;
                        //String P_image = "http://localhost/univ/displayFile?fileName=6d4408e3-9f4a-4ce8-a224-8b101921e92c.jpg";

                        items.add(0,new Item(Place_id, P_name, P_address, P_image)); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
                        adapter.notifyItemInserted(0);


                        items2.add(0,new Item(Place_id, P_name, P_address, P_image)); //이 아이템이 phonehelper 같은 아이템임. 여기서 add
                        adapter2.notifyItemInserted(0); //      얘는 삽입된 item(여기선 고유명사 item 임)이 있다는걸 알려주는것

//                        adapter2 = new adapterphone2(items2,this)
//                        phoneRecycler3.setAdapter(adapter2);//테스트용 수평리사이클러어댑터
                    }
                } catch (JSONException e) {e.printStackTrace();}

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_2.this, "ERROR", Toast.LENGTH_SHORT).show(); //     에러시 토스트 메세지
            }
        });

        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);


    }
}