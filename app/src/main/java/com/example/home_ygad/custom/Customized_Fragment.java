package com.example.home_ygad.custom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.home_ygad.Item;
import com.example.home_ygad.R;
import com.example.home_ygad.adapterphone1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Customized_Fragment extends Fragment {


    private View view1;

    RecyclerView recyclerView1;
    ArrayList<Item> items = new ArrayList<>(); //혹시몰라 아이템(DB연동데이터)용 데이터 넣을 배열 새로만듦



    adapterphone1 adapter;
    //String Dog_id = GetIntent.getStringExtra("Dog_id");
    String SB = " ";
    String Dog_id;
    Button btn_custom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_customized_, container, false);
        recyclerView1 = view1.findViewById(R.id.recyclerview_CustomResult);
        adapter = new adapterphone1(getActivity().getApplicationContext(), items); //리사이클 어댑터
        //       이 adapter2는 매개변수가 3개. 컨택스트,배열,클릭리스너임. 3번째 매개변수는 아직 안넣음
        recyclerView1.setAdapter(adapter); //     얘가 에러나면 어댑터에 문제있음.프래그먼트라 수정사항이 있어야할수있음
        recyclerView1.setHasFixedSize(true); //해당 항목의 높이나 너비가 변경되지 않으며, 전부 동일하다. 이게 없으면 매번 다시 그려야함
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager2);
//
//        Bundle extra = getArguments();
//
//            Dog_id = extra.getString("Dog_id");
//
//            Toast.makeText(getActivity().getApplicationContext(),Dog_id, Toast.LENGTH_SHORT).show();
//

        btn_custom = view1.findViewById(R.id.btn_custom);
        btn_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String SB = "";
                String Dog_id = "1";

                Bundle extra = getArguments();
                if (extra != null){
                    Dog_id = extra.getString("Dog_id");

                    Toast.makeText(getActivity().getApplicationContext(),Dog_id, Toast.LENGTH_SHORT).show();
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        //불러들이는지 확인하기 위해 해당내용을 토스트메세지로 출력하도록 함(개발편의를 위한 것)
                        System.out.println("답변옴");
                        items.clear(); //일단 깨끗하게 처리함
                        adapter.notifyDataSetChanged();
                        try {
                            JSONArray customarray = new JSONArray(response);
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = customarray.getJSONObject(i);

                                int Place_id = Integer.parseInt(jsonObject.getString("Place_id")); //no가 문자열이라서 바꿔야함.
                                String P_name = jsonObject.getString("P_name");
                                String P_address = jsonObject.getString("P_address");
                                String P_image = jsonObject.getString("P_image");
                                //         어...그러니까 이 clikload 안의 onResponce 함수에서 json으로 어케한 정보들을 여기서 사용가능한 string들로 바꿔준다?
                                //         근데 이미지는 어케 넣어지게 되는거야

                                //이미지 경로의 경우 서버 IP가 제외된 주소이므로(uploads/xxxx.jpg) 바로 사용 불가.
                                //P_image = "http://heremong.dothome.co.kr/"+P_image;
                                //String P_image = "http://localhost/univ/displayFile?fileName=6d4408e3-9f4a-4ce8-a224-8b101921e92c.jpg";

                                items.add(0, new Item(Place_id, P_name, P_address, P_image)); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
                                adapter.notifyItemInserted(0);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                CustomResultRequest customResultRequest = new CustomResultRequest(SB, Dog_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(customResultRequest);


            }
        });


        return view1;
    }
}
