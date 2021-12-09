package com.example.home_ygad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.home_ygad.shoplist.ShopInfo_Activity;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

//수직 리사이클러뷰를 위한 어댑터. 가게 상세페이지가 가능
//android:networkSecurityConfig="@xml/network_security_config"

public class adapterphone1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnLikeListener { //여기는 어댑터에 이름붙인듯한데
    //                              jsonarrayrequest 버리고 스트링리퀘스트로 하되, 받아오는걸 jsonobject로
    Context context;
    ArrayList<Item> phoneLaocations; //아이템을 넣은 배열
    LikeButton likeButton;

    //  final private ListItemClickListener mOnClickListener;



    public adapterphone1(Context context, ArrayList<Item> phoneLaocations) {
        this.phoneLaocations = phoneLaocations;
        this.context = context;
        //  mOnClickListener = listener;
    }

    //수평 리사이클러뷰 어댑터
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        // View itemview = inflater.inflate(R.layout.phonerecyclercard_2, parent, false); //여기 수정함 infrater를

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        VH holder = new VH(view);
        return holder;

    }

//그러니까 만약... 유저 id는 저장되어있고...그걸 호출하는게...

    @Override //여기  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;

        Item phonehelper = phoneLaocations.get(position);
        vh.tvName.setText(phonehelper.getP_name());
        vh.tvMsg.setText(phonehelper.getP_address());

        Glide.with(context).load(phonehelper.getP_image()).into(vh.iv);
        //      글라이드 쓰는법, load에 url이 들어가면 됨. 그러면 로딩해줌



        //세부페이지를 위해 추가한부분
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvName = vh.tvName.getText().toString();
                String tvMsg = vh.tvMsg.getText().toString();
                ImageView imageView = vh.iv;

                Intent intent;
                intent = new Intent(context, ShopInfo_Activity.class);
                intent.putExtra("tvName", tvName);
                intent.putExtra("tvMsg", tvMsg);

                //우선. 받은 이미지를 비트맵 형식을 바꾼다
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                //비트맵 형식으로 바꾼 이미지 뷰를 비트 어레이로 변경하여 putextra로 보낸다
                //이후 받은곳에서 다시 bitmap으로 변환하여 이미지뷰에 사용한ㄷ.
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("tvImage",byteArray);

                context.startActivity(intent);
            }
        });
        //역기까지

    }


//    @Override
//    public int getItemCount() { return phoneLaocations.size(); } //폰로케이션 = 배열의 크기를 반환
//이곳도 수정됨. 이코드가 이전버전

    @Override
    public int getItemCount(){
        return (phoneLaocations != null ? phoneLaocations.size() : 0);
    }
//이코드가 세부페이지용 버전


    @Override
    public long getItemId(int position) {
        return position;
    }
//이코드가 추가됨됨

    @Override
    public void liked(LikeButton likeButton) {

    }

    @Override
    public void unLiked(LikeButton likeButton) {

    }



    class VH extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvName;
        TextView tvMsg;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvName=itemView.findViewById(R.id.tv_name);
            tvMsg=itemView.findViewById(R.id.tv_msg);
            iv=itemView.findViewById(R.id.iv);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPos = getAdapterPosition();//클릭 포지션 가져오기
                    Item itemData = phoneLaocations.get(currentPos);

                    Toast.makeText(context,itemData.getPlace_id() + "\n",Toast.LENGTH_SHORT).show();

                }
            });

        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            //   mOnClickListener.onphoneListClick(clickedPosition);
        }
    }



}
