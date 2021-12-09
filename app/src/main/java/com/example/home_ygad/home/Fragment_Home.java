package com.example.home_ygad.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.home_ygad.Item;
import com.example.home_ygad.shoplist.Park_activity;
import com.example.home_ygad.R;
import com.example.home_ygad.search.Restorant_Activity;
import com.example.home_ygad.search.SearchActivity;
import com.example.home_ygad.adapterphone2;
import com.example.home_ygad.custom.CustomizedActivity;
import com.example.home_ygad.shoplist.Cafe_Activity;
import com.like.LikeButton;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Home extends Fragment  {

    private View view1;//프래그먼트라서 있는 View,
    //      프래그먼트는 this 대신 getActivity().getApplicationContext() 를 쓴다

    RecyclerView phoneRecycler3;
    ArrayList<Item> items2= new ArrayList<>(); //혹시몰라 아이템(DB연동데이터)용 데이터 넣을 배열 새로만듦
    LikeButton likeButton;
    EditText editText1;
    Button btn_custom;

    adapterphone2 adapter2;// 수평리사이클러어댑터

    Handler hander1 = new Handler();

    private ImageButton btn_cafe,btn_retorant,btn_park;
   

    SliderView sliderView;
    int[] images = {R.drawable.one,
            R.drawable.two_1,
            R.drawable.three_1,
            R.drawable.four_1,
            R.drawable.five_1,
            };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) { //인터페이스가 처음 그려질 때 호출된다
        // Inflate the layout for this fragment = 인플레이터로 뷰를 그려주는 역할. OnCreate 대신 얘를 쓴다고 볼 수 잇다
        view1 = inflater.inflate(R.layout.fragment_home1, container, false);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        //이건 레이아웃 매니저인데 this가  json 안에선 안돼서 여기서 선언해둠
//        likeButton=view1.findViewById(R.id.like_button);



        phoneRecycler3=view1.findViewById(R.id.my_recycler_Home);
        adapter2 = new adapterphone2(getActivity().getApplicationContext(),items2); //리사이클 어댑터
        //       이 adapter2는 매개변수가 3개. 컨택스트,배열,클릭리스너임. 3번째 매개변수는 아직 안넣음
        phoneRecycler3.setAdapter(adapter2); //     얘가 에러나면 어댑터에 문제있음.프래그먼트라 수정사항이 있어야할수있음
        phoneRecycler3.setHasFixedSize(true); //해당 항목의 높이나 너비가 변경되지 않으며, 전부 동일하다. 이게 없으면 매번 다시 그려야함
        phoneRecycler3.setLayoutManager(layoutManager2);


        btn_cafe = (ImageButton) view1.findViewById(R.id.Btn_CAfe); //액티비티 이동 버튼
        btn_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Cafe_Activity.class);
                startActivity(intent); //액티비티 이동, 여긴 fragment라서 좀 다를수있음

            }
        });

        btn_park = (ImageButton) view1.findViewById(R.id.Btn_Park); //액티비티 이동 버튼
        btn_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Park_activity.class);
                startActivity(intent);

            }
        });

        btn_retorant = (ImageButton) view1.findViewById(R.id.Btn_retorant); //액티비티 이동 버튼
        btn_retorant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Restorant_Activity.class);
                startActivity(intent);

            }
        });

        btn_custom = (Button)view1.findViewById(R.id.Btn_ToCustomise);
        btn_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CustomizedActivity.class);
                startActivity(intent);
            }
        });

        editText1 =view1.findViewById(R.id.edit_search);

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });



        new Thread(new Runnable() {
            boolean isRun = true;

            @Override
            public void run() {
                while ((isRun)){


                    hander1.post(new Runnable() {
                        @Override
                        public void run() {
                            //서버의 loadDBtoJson.php파일에 접속하여 (DB데이터들)결과 받기
                            //Volley+ 라이브러리 사용

                            //서버주소
                            String serverUrl="http://heremong.dothome.co.kr/PlaceList2.php";
                            //         PlaceList3.php 를 사용, Place_id, P_name, P_address, P_image,P_image2 from Place

                            //결과를 JsonArray 받을 것이므로..
                            //StringRequest가 아니라..
                            //JsonArrayRequest를 이용할 것임
                            JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>()
                            {
                                //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
                                @Override
                                public void onResponse(JSONArray response) {
                                    // Toast.makeText(MainActivity_2.this, response.toString(), Toast.LENGTH_SHORT).show();


                                    //      파라미터로 응답받은 결과 JsonArray를 분석

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



                                            items2.add(0,new Item(Place_id, P_name, P_address, P_image)); //이 아이템이 phonehelper 같은 아이템임. 여기서 add
                                            adapter2.notifyItemInserted(0); //      얘는 삽입된 item(여기선 고유명사 item 임)이 있다는걸 알려주는것

                                        }
                                    } catch (JSONException e) {e.printStackTrace();}

                                }


                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                  //  Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show(); //     에러시 토스트 메세지
                                }
                            });

                            //실제 요청 작업을 수행해주는 요청큐 객체 생성
                            RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());

                            //요청큐에 요청 객체 생성
                            requestQueue.add(jsonArrayRequest);


                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){ }

                }

            }
        }).start();






        sliderView = (SliderView) view1.findViewById(R.id.image_slider); //view 객체에 현재 뷰를 담고 호출
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        return view1;//프라그먼트라서 view1 같은걸 씀
    }


//
//    @Override
//    public void liked( likeButton) {
//        Toast.makeText(getActivity().getApplicationContext(),"좋아요", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void unLiked(LikeButton likeButton) {
//
//    }
}