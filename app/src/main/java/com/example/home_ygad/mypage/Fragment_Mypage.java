package com.example.home_ygad.mypage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_ygad.R;
import com.example.home_ygad.mypage.DogInsert;


public class Fragment_Mypage extends Fragment {

    private View view2;
    RecyclerView phoneRecycler;
    RecyclerView.Adapter adapter;

    Button btn;
    TextView textView1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view2 = inflater.inflate(R.layout.fragment_mypage,container, false);
//
//        textView1 = view2.findViewById(R.id.user_name);

        btn = view2.findViewById(R.id.btn_dogprofile);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), DogInsert.class);
                startActivity(intent); //액티비티 이동, 여긴 fragment라서
            Toast.makeText(getActivity().getApplicationContext(), "클릭 테스트", Toast.LENGTH_SHORT).show();

            }
        });

        return view2;
//
//
//        public void clickDog(View view) {
//
//            Intent intenti = new Intent(getApplicationContext(), DogInsert.class);
//            startActivity(intenti);
//
//            Toast.makeText(Mypage.this, "클릭 테스트", Toast.LENGTH_SHORT).show();

            //CustomDialog dialog = new CustomDialog(Mypage.this);
//        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//        params.copyFrom(dialog.getWindow().getAttributes());
//        params.width = (int) (getApplicationContext().getResources().getDisplayMetrics().widthPixels * 0.7f);
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //dialog.show();

            //CustomDialog.getInstance(this).showCustomDialog();

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setTitle("강아지 등록");
//
//        builder.setPositiveButton("등록", new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int id)
//            {
//                Toast.makeText(getApplicationContext(), "OK Click", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int id)
//            {
//                Toast.makeText(getApplicationContext(), "Cancel Click", Toast.LENGTH_SHORT).show();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//
//        alertDialog.show();

    }


}
