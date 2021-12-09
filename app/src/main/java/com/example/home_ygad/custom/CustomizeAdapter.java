package com.example.home_ygad.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_ygad.R;

import java.util.ArrayList;


// 맞춤의 강아지 프로필 클릭시 하단 프라그먼트 결과를 위한 어댑터


public class CustomizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> { //여기는 어댑터에 이름붙인듯한데
    //                              jsonarrayrequest 버리고 스트링리퀘스트로 하되, 받아오는걸 jsonobject로
    Context context;
    ArrayList<dogProfile_Item> dogProfile_items; //아이템을 넣은 배열. 강아지 프로필용 ITMe
    Customized_Fragment fragment_customized;


    //  final private ListItemClickListener mOnClickListener;



    public CustomizeAdapter(Context context, ArrayList<dogProfile_Item> dogProfile_items) {
        this.dogProfile_items = dogProfile_items;
        this.context = context;
        //  mOnClickListener = listener;
    }

    //수평 리사이클러뷰 어댑터
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        // View itemview = inflater.inflate(R.layout.phonerecyclercard_2, parent, false); //여기 수정함 infrater를

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dogprofile_itemview, parent, false);

        VH holder = new VH(view);
        return holder;

    }

//그러니까 만약... 유저 id는 저장되어있고...그걸 호출하는게...

    @Override //여기  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;

        dogProfile_Item dogProfile_item = dogProfile_items.get(position);
        vh.D_name.setText(dogProfile_item.getD_name());
        vh.D_kg.setText(dogProfile_item.getD_kg()+"Kg");
//        vh.D_sav.setText(dogProfile_item.getD_sav());
       // vh.D_sex.setText(dogProfile_item.getD_sex());
        vh.Dog_id.setText(dogProfile_item.getDog_id());

        String i =dogProfile_item.getD_sav();

        if (i.equals("0")){
            vh.D_sav.setText("맹견x");
        } else{
            vh.D_sav.setText("맹견");
        }
        String e = dogProfile_item.getD_sex();

        if (e.equals("0")){
            vh.D_sex.setText("남자");
        } else {
            vh.D_sex.setText("여자");
        }

        //세부페이지를 위해 추가한부분
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Dog_id = dogProfile_item.getDog_id();
//
//                fragment_customized = new Customized_Fragment();
//
//                Bundle bundle = new Bundle();
//                bundle.putString("Dog_id",Dog_id);
//                fragment_customized.setArguments(bundle);
//
//                Intent intent;
//                intent = new Intent(context, CustomizedActivity.class);
//                intent.putExtra("Dog_id", Dog_id);
//                context.startActivity(intent);
//
//                Toast.makeText(context, Dog_id.toString(), Toast.LENGTH_SHORT).show();


            }
        });
        //역기까지

    }




//    @Override
//    public int getItemCount() { return dogProfile_items.size(); } //폰로케이션 = 배열의 크기를 반환
//이곳도 수정됨. 이코드가 이전버전

    @Override
    public int getItemCount(){
        return (dogProfile_items != null ? dogProfile_items.size() : 0);
    }
////이코드가 세부페이지용 버전
//
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
////이코드가 추가됨됨


    class VH extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView D_name;
        TextView D_sex;
        TextView D_kg;
        TextView D_sav;
        TextView Dog_id;

        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this);

            D_name=itemView.findViewById(R.id.dv_name);
            D_sex=itemView.findViewById(R.id.dv_sex);
            D_kg=itemView.findViewById(R.id.dv_kg);
            D_sav=itemView.findViewById(R.id.dv_sav);
            Dog_id=itemView.findViewById(R.id.dv_dogid);



//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int currentPos = getAdapterPosition();//클릭 포지션 가져오기
//                    dogProfile_Item itemData = dogProfile_items.get(currentPos);
////
////                    Toast.makeText(context,itemData.getPlace_id() + "\n",Toast.LENGTH_SHORT).show();
//
//                }
//            });

        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            //   mOnClickListener.onphoneListClick(clickedPosition);
        }
    }



}
