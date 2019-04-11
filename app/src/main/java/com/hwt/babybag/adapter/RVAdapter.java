package com.hwt.babybag.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwt.babybag.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VideoViewHolder>{
    private Context context;
    private List<String> list;

    public RVAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mission_item,viewGroup,false);
//        View view = LayoutInflater.from(context).inflate(R.layout.baby_recommend_list_item,viewGroup,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, final int i) {
        videoViewHolder.userName.setText(list.get(i));
//        videoViewHolder.video_introduce.setText(list.get(i));
//        videoViewHolder.video_title.setText(list.get(i)+i);
//        videoViewHolder.baby_ll_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"i--->"+i,Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    class VideoViewHolder extends RecyclerView.ViewHolder{
//        private ImageView videoCover;
//        private TextView video_title,video_introduce;
//        private LinearLayout baby_ll_item;
//
//        public VideoViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.videoCover = itemView.findViewById(R.id.video_cover);
//            this.video_title = itemView.findViewById(R.id.video_title);
//            this.video_introduce = itemView.findViewById(R.id.video_introduce);
//            this.baby_ll_item = itemView.findViewById(R.id.baby_ll_item);
//        }
//    }


    class VideoViewHolder extends RecyclerView.ViewHolder{
        private ImageView userHeader,praise;
        private TextView userName,addtime,missionContent;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            userHeader = itemView.findViewById(R.id.user_header);
            userName = itemView.findViewById(R.id.user_name);
            addtime = itemView.findViewById(R.id.add_time);
            missionContent = itemView.findViewById(R.id.mission_content);

        }
    }
}
