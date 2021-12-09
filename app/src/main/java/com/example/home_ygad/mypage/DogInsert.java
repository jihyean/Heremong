package com.example.home_ygad.mypage;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.home_ygad.R;

import org.json.JSONException;
import org.json.JSONObject;

//import static android.os.Build.ID;

public class DogInsert extends AppCompatActivity {

    ArrayAdapter<CharSequence> spinner, spinner2;

    String Sex;  //장소 카테고리
    String Kg;  //몸무게       (map 하기)
    String Grade;       // grade 하네스 이동장 여부 등   (map 하기)
    TextView sizeresult;
    Button btn_canclei;
    Button btn_regi;
    RadioGroup sex;
    RadioButton sex1, sex2;
    CheckBox ferodogi;
    String Sav = "0";          //맹견여부 (map 하기)
    EditText et_name, et_age, et_weighti;
    String ID, namei, agei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doginsert);

        ID = "id";

        //성별 선택
        sex = findViewById(R.id.sex);
        sex1 = findViewById(R.id.sex1);
        sex2 = findViewById(R.id.sex2);

        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.sex1){

                    //임시로 카테고리 변수 설정
                    Sex = "0";

                }else if(checkedId == R.id.sex2){

                    Sex = "1";

                }
            }
        });

        //맹견 여부 체크
        ferodogi = findViewById(R.id.ferodogi);
        ferodogi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                Sav = "0";
                if (ferodogi.isChecked()){
                    Sav = "1";
                }else if(ferodogi.isChecked()==false) {
                    Sav = "0";
                }
            }
        });


        //seekbar 코드
        sizeresult = findViewById(R.id.sizeresult);

//        SeekBar seekbar = findViewById(R.id.sizeseek);
//        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                //조작 중
//                sizeresult.setText(String.format("몸무게 %d KG", seekBar.getProgress()));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                //처음 터치했을 때
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                //터치가 끝났을 때
//                sizeresult.setText(String.format("몸무게 %d KG", seekBar.getProgress()));
//
//                //몸무게 변수 저장
//                Kg = sizeresult.toString();
//                Toast.makeText(DogInsert.this, Kg.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });


        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_weighti = findViewById(R.id.et_weighti);

        //등록 완료 버튼
        btn_regi = findViewById(R.id.btn_regi);
        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이름 입력
                namei = et_name.getText().toString();
                //나이 입력
                agei = et_age.getText().toString();

                Kg = et_weighti.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(DogInsert.this, "php", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(DogInsert.this,"반려견 등록에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                    onBackPressed();

                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(DogInsert.this,"반려견 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                DogRequest dogRequest = new DogRequest( ID, namei, Kg, Sav, Sex, agei, responseListener);
                RequestQueue dogqueue = Volley.newRequestQueue(DogInsert.this);
                dogqueue.add(dogRequest);

//                Intent myIntenti = new Intent(DogInsert.this, Mypage.class);
//
//                Toast.makeText(DogInsert.this, ID, Toast.LENGTH_SHORT).show();
//                Toast.makeText(DogInsert.this, namei, Toast.LENGTH_SHORT).show();//
//                Toast.makeText(DogInsert.this, Kg, Toast.LENGTH_SHORT).show();
//                Toast.makeText(DogInsert.this, Sav, Toast.LENGTH_SHORT).show();
//                Toast.makeText(DogInsert.this, Sex, Toast.LENGTH_SHORT).show();
//                Toast.makeText(DogInsert.this, agei, Toast.LENGTH_SHORT).show();//
//
//                myIntenti.putExtra("ID", ID);
//                myIntenti.putExtra("namei", namei);
//                myIntenti.putExtra("Kg", Kg);
//                myIntenti.putExtra("Sav", Sav);
//                myIntenti.putExtra("Sex", Sex);
//                myIntenti.putExtra("agei", agei);
//
//                startActivity(myIntenti);

//                onBackPressed();
            }
        });

        //필터 초기화 버튼
        btn_canclei = findViewById(R.id.btn_canclei);

        btn_canclei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

