package com.example.home_ygad.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_ygad.R;

public class Search_Filter extends AppCompatActivity {
    ArrayAdapter<CharSequence> spinner, spinner2;
    String choice_do="";
    String choice_se="";
    String adress_do; //주소정하기으 "도"
    String adress_se; //주소정하기으 "시"
    String Cate;  //장소 카테고리
    String Dkg;  //몸무게       (map 하기)
    String Grade;       // grade 하네스 이동장 여부 등   (map 하기)
    TextView sizeresult;
    Button delete;
    Button filtercomplete;
    RadioGroup place;
    RadioGroup grade_group;
    RadioButton place1, place2, place3, place4;
    RadioButton grade0, grade1, grade2, grade3;
    CheckBox ferodog;
    String Dsav = "0";          //맹견여부 (map 하기)
    EditText et_weight;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        Spinner aspinner = findViewById(R.id.Aspinner);
        Spinner aspinner2 = findViewById(R.id.Aspinner2);

        spinner = ArrayAdapter.createFromResource(this, R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);

        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        aspinner.setAdapter(spinner);


        String ID = "id";
        String SB = "qwe";


        aspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if(spinner.getItem(i).equals("서울")) {
                    choice_do = "서울";
                    spinner2 = ArrayAdapter.createFromResource(Search_Filter.this, R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    aspinner2.setAdapter(spinner2);

                    //변수에 "도" 저장
                    adress_do = aspinner.getSelectedItem().toString();

                    aspinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                            choice_se = spinner2.getItem(i).toString();

                            //변수에 "시/군/구" 저장
                            adress_se = aspinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (spinner.getItem(i).equals("경기")) {
                    choice_do = "경기";
                    spinner2 = ArrayAdapter.createFromResource(Search_Filter.this, R.array.spinner_do_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    aspinner2.setAdapter(spinner2);

                    //변수에 "도" 저장
                    adress_do = aspinner.getSelectedItem().toString();

                    aspinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                            choice_se = spinner2.getItem(i).toString();

                            //변수에 "시/군/구" 저장
                            adress_se = aspinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //카테고리 선택
        place = findViewById(R.id.place);
        place1 = findViewById(R.id.place1);
        place2 = findViewById(R.id.place2);
        place3 = findViewById(R.id.place3);
        place4 = findViewById(R.id.place4);

        place.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.place1){

                    //임시로 카테고리 변수 설정
                    Cate = "카페";

                }else if(checkedId == R.id.place2){

                    Cate = "식당";

                }else if(checkedId == R.id.place3){

                    Cate = "쇼핑";

                }else if(checkedId == R.id.place4){

                    Cate = "유틸리티";

                }
            }
        });

        //맹견 여부 체크
        ferodog = findViewById(R.id.ferodog);
        ferodog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                Dsav = "0";
                if (ferodog.isChecked()){
                    Dsav = "1";
                }else if(ferodog.isChecked()==false) {
                    Dsav = "0";
                }
            }
        });


        //seekbar 코드
//        sizeresult = findViewById(R.id.sizeresult);
//
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
//                Dkg = sizeresult.toString();
//                Toast.makeText(Search_Filter.this, Dkg.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

        //등급설정
        grade_group = findViewById(R.id.grade);
        grade0 = findViewById(R.id.grade0);
        grade1 = findViewById(R.id.grade1);
        grade2 = findViewById(R.id.grade2);
        grade3 = findViewById(R.id.grade3);

        grade_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (grade0.isChecked()){
                    Grade = "0";
                    Toast.makeText(Search_Filter.this, Grade, Toast.LENGTH_SHORT).show();
                }else if(grade1.isChecked()){
                    Grade = "1";
                }else if(grade2.isChecked()){
                    Grade="2";
                }else if(grade3.isChecked()){
                    Grade="3";
                }
            }
        });

        //필터 설정 완료 버튼
        filtercomplete = findViewById(R.id.filtercomplete);

        filtercomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et_weight = findViewById(R.id.et_weight);
                Dkg = et_weight.getText().toString();


//                tvMsg = aspinner.getSelectedItem().toString();
//                Intent intent;
//                intent = new Intent(context, MainActivity.class);
//                intent.putExtra("P_adress", tvMsg);
                Intent myIntent = new Intent(Search_Filter.this, SearchActivity.class);

//                Toast.makeText(Search_Filter.this, ID, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Search_Filter.this, Dkg, Toast.LENGTH_SHORT).show(); //
//                Toast.makeText(Search_Filter.this, Dsav, Toast.LENGTH_SHORT).show(); //
//                Toast.makeText(Search_Filter.this, Grade, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Search_Filter.this, Cate, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Search_Filter.this, adress_se, Toast.LENGTH_SHORT).show();
                myIntent.putExtra("Dkg", Dkg);
                myIntent.putExtra("Dsav", Dsav);
                myIntent.putExtra("Grade", Grade);
                myIntent.putExtra("Cate", Cate);
                myIntent.putExtra("SB", SB);
                myIntent.putExtra("ID",ID);
                myIntent.putExtra("Paddress",adress_se);
                startActivity(myIntent);

                onBackPressed();
            }
        });

        //필터 초기화 버튼
        delete = findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adress_do = "서울";
                adress_se = "서울";
                Cate = "카페";
                Dsav = "0";
                Dkg = "1";
                Grade = "3";
                Toast.makeText(Search_Filter.this, "필터가 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}