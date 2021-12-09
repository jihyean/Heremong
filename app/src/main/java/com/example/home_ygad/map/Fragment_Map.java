package com.example.home_ygad.map;

import android.net.sip.SipSession;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.home_ygad.Item;
import com.example.home_ygad.R;
import com.example.home_ygad.adapterphone2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Map;

public class Fragment_Map extends Fragment {

    private View view2;

    RecyclerView phoneRecycler3;
    ArrayList<Item> items2= new ArrayList<>(); //리사이클러뷰 배열
    adapterphone2 adapter2;//테스트용 수평리사이클러어댑터


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view2 = inflater.inflate(R.layout.fragment_map,container, false);


        LinearLayoutManager layoutManager2=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);

        adapter2 = new adapterphone2(getActivity().getApplicationContext(),items2); //리사이클 어댑터
        phoneRecycler3=view2.findViewById(R.id.my_recycler_Map);

        phoneRecycler3.setAdapter(adapter2);

        phoneRecycler3.setHasFixedSize(true); //해당 항목의 높이나 너비가 변경되지 않으며, 전부 동일하다. 이게 없으면 매번 다시 그려야함
        phoneRecycler3.setLayoutManager(layoutManager2);

      //  final String URL = "https://heremong.dothome.co.kr/Search.php";
        Map<String, String> map;

        String SW = "qwe";




        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // String SB = et_search.getText().toString();
                Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();


                //지금 답변이 없는건가..? 기본적으로 배열에 들어가있는데..?? 일단 배열이 비어있다고 답변이 뜸. 왜지?
                // s Unexpected response code 302  에러 302는 리디렉션의 http 와 https 차이로 생기는 오류,,이미지 파일 URL에 문제가 있다
                // 반환값중 이미지 URL은 https로 시작한다. 이게 에러의 원인인듯. 이거 해결하면 되려나...

                //      이 스트링 리퀘스트의 답변이 스트링으로 온다. 이름은 response 다.
                //      response 는 데이터 타입이 string 이라 jsonObject로 바꿔줘야 한다.
                //      바꿀때 무조건 try/catch 문으로 싸야 한다. 무조건이다.
                //

                //      그럼 jsonarray를 만들어준다음에 홈화면에 있던 코드 쓰면될듯?? 아닌가
                //      근데 그럼 어... 어떤 기준으로 오브젝트를 자르지? 지금 뭉탱이로있는 스트링인데


                try {
                    JSONArray searchresponceJSON = new JSONArray(response);
                    Toast.makeText(getActivity().getApplicationContext(), searchresponceJSON.toString(), Toast.LENGTH_SHORT).show();

                    items2.clear();
                    adapter2.notifyDataSetChanged();

//                    try {
//
//                        for(int i=0;i<searchresponceJSON.length();i++){
//                            // String jsonObject= response.String(i);
//
//                            int Place_id= Integer.parseInt(searchresponceJSON.getString(0)); //no가 문자열이라서 바꿔야함.
//                            String P_name=searchresponceJSON.getString(1);
//                            String P_address=searchresponceJSON.getString(2);
//                            String P_image=searchresponceJSON.getString(3);
//                            // 저 name을 알아야 잘라서 넣어주기가 편한데, string을 json으로 변환시키는게 지금 불가함,지금 index를 제대로 인식하고있는건가?
//
//
//                            //이미지 경로의 경우 서버 IP가 제외된 주소이므로(uploads/xxxx.jpg) 바로 사용 불가.
//                            //P_image = "http://heremong.dothome.co.kr/"+P_image;
//                            //String P_image = "http://localhost/univ/displayFile?fileName=6d4408e3-9f4a-4ce8-a224-8b101921e92c.jpg";
//
//
//
//                            items2.add(0,new Item(Place_id, P_name, P_address, P_image)); //이 아이템이 phonehelper 같은 아이템임. 여기서 add
//                            adapter2.notifyItemInserted(0); //      얘는 삽입된 item(여기선 고유명사 item 임)이 있다는걸 알려주는것
//
//                        }
//                    } catch (JSONException e) {e.printStackTrace();}
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        };

//        SearchRequest searchRequest = new SearchRequest(SW,responseListener);
//        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
//        requestQueue.add(searchRequest);
//


        return view2;
    }


}