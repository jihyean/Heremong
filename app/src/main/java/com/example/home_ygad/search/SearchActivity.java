package com.example.home_ygad.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Item> items = new ArrayList<>();

    adapterphone1 adapter;

    EditText search_bar;

    ImageButton filterbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        recyclerView = findViewById(R.id.recycler2);
        adapter = new adapterphone1(this, items);
        recyclerView.setAdapter(adapter);
        search_bar = findViewById(R.id.search_bar1);
        filterbutton = findViewById(R.id.filterbutton);

        //리사이클러뷰의 레이아웃 매니저 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //필터 설정으로 넘어가기
        filterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search_Filter.class);
                startActivity(intent);
            }
        });
    }

    public void clickLoad(View view) {

        //서버의 loadDBtoJson.php파일에 접속하여 (DB데이터들)결과 받기
        //Volley+ 라이브러리 사용

        //서버주소
      //  String serverUrl = "http://heremong.dothome.co.kr/Search.php";
        String SB = search_bar.getText().toString();
        String ID = "id";
        Toast.makeText(SearchActivity.this, "버튼클릭됨", Toast.LENGTH_SHORT).show();


        Intent GetIntent = getIntent();

        String Dkg = GetIntent.getStringExtra("Dkg");
        String Dsav = GetIntent.getStringExtra("Dsav");
        String Grade = GetIntent.getStringExtra("Grade");
        String Cate = GetIntent.getStringExtra("Cate");
        String Paddress = GetIntent.getStringExtra("Paddress");
        // String Dkg = "12";
        //String Grade = "1";
        //String Dsav = "0";
        //String Cate = "카페";

//        Toast.makeText(MainActivity.this, Grade, Toast.LENGTH_SHORT).show();
//        Toast.makeText(MainActivity.this, D_kg, Toast.LENGTH_SHORT).show();



        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SearchActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                //불러들이는지 확인하기 위해 해당내용을 토스트메세지로 출력하도록 함(개발편의를 위한 것)
                System.out.println("답변옴");
                items.clear(); //일단 깨끗하게 처리함
                adapter.notifyDataSetChanged();
                try {
                    JSONArray searcharray = new JSONArray(response);
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject = searcharray.getJSONObject(i);

                        int Place_id= Integer.parseInt(jsonObject.getString("Place_id")); //no가 문자열이라서 바꿔야함.
                        String P_name=jsonObject.getString("P_name");
                        String P_address=jsonObject.getString("P_address");
                        String P_image=jsonObject.getString("P_image");

                        //이미지 경로의 경우 서버 IP가 제외된 주소이므로(uploads/xxxx.jpg) 바로 사용 불가.
                        //P_image = "http://heremong.dothome.co.kr/"+P_image;
                        //String P_image = "http://localhost/univ/displayFile?fileName=6d4408e3-9f4a-4ce8-a224-8b101921e92c.jpg";

                        items.add(0,new Item(Place_id, P_name, P_address, P_image)); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
                        adapter.notifyItemInserted(0);
                    }
                } catch (JSONException e) {e.printStackTrace();}

            }
        };
        // 서버로 Volley를 이용해서 요청을 함.
        SearchRequest_filter searchRequest_filter = new SearchRequest_filter(ID, SB, Dkg, Grade, Dsav, Cate, Paddress, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        queue.add(searchRequest_filter);

    }
}