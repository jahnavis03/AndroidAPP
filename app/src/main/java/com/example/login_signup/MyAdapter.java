package com.example.login_signup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getFaceImage1()).into(holder.recImage);
        holder.recPet.setText(dataList.get(position).getPetName());
        holder.recTag.setText(dataList.get(position).getTagNo());
        holder.recBreed.setText(dataList.get(position).getBreed());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image1", dataList.get(holder.getAdapterPosition()).getFaceImage1());
                intent.putExtra("Image2", dataList.get(holder.getAdapterPosition()).getFaceImage2());
                intent.putExtra("Image3", dataList.get(holder.getAdapterPosition()).getFaceImage3());
                intent.putExtra("Image4", dataList.get(holder.getAdapterPosition()).getMuzzleImage1());
                intent.putExtra("Image5", dataList.get(holder.getAdapterPosition()).getMuzzleImage2());
                intent.putExtra("Image6", dataList.get(holder.getAdapterPosition()).getMuzzleImage3());
                intent.putExtra("Breed", dataList.get(holder.getAdapterPosition()).getBreed());
                intent.putExtra("Pet", dataList.get(holder.getAdapterPosition()).getPetName());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Tag", dataList.get(holder.getAdapterPosition()).getTagNo());
                intent.putExtra("Age", dataList.get(holder.getAdapterPosition()).getAge());
                intent.putExtra("Medical", dataList.get(holder.getAdapterPosition()).getMedicalHist());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
//    public void searchDataList(ArrayList<DataClass> searchList){
//        dataList=searchList;
//        notifyDataSetChanged();
//    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recPet, recBreed, recTag;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recBreed = itemView.findViewById(R.id.recBreed);
        recTag = itemView.findViewById(R.id.recTag);
        recPet = itemView.findViewById(R.id.recPet);
    }
}
