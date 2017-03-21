package com.example.bon.filmgalaxy.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bon.filmgalaxy.NoiDungChiTiet;
import com.example.bon.filmgalaxy.R;
import com.example.bon.filmgalaxy.ScreenYoutube;
import com.example.bon.filmgalaxy.model.TypePhim;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bon on 3/18/2017.
 */

public class CustomAdapterPhim extends RecyclerView.Adapter<CustomAdapterPhim.ViewHolder> {
    private List<TypePhim>  listPhim;
    private Context context;

    public CustomAdapterPhim(List<TypePhim> listPhim, Context context) {
        this.listPhim = listPhim;
        this.context = context;
    }

    @Override
    public CustomAdapterPhim.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom_recyclerview,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomAdapterPhim.ViewHolder holder, final int position) {
       if (position %2 == 0){
           holder.cardView.setCardBackgroundColor(Color.WHITE);
       }else {
           holder.cardView.setCardBackgroundColor(Color.argb(190,183,183,183));
       }
        final TypePhim typePhim = listPhim.get(position);
        holder.txtTenPhim.setText(typePhim.getTenPhim());
        holder.txtTheLoai.setText(typePhim.getTheLoai());
        Picasso.with(context).load(typePhim.getHinhAnh()).into(holder.imageViewHinh);
        holder.imageViewHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScreenYoutube.class);
                intent.putExtra("IdYoutube",listPhim.get(position).getUrlVideo());
                if (listPhim.get(position).getUrlVideo() != null){
                    Log.d("LISTPHIM","Co Du Lieu");
                }else {
                    Log.d("LISTPHIM","Khong Co Giu Lieu");
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhim.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenPhim,txtTheLoai;
        ImageView imageViewHinh;
        CardView cardView;
        ViewHolder(View itemView) {
            super(itemView);
            txtTenPhim = (TextView)itemView.findViewById(R.id.txtTenPhim);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            txtTheLoai = (TextView)itemView.findViewById(R.id.txtTheLoai);
            imageViewHinh = (ImageView)itemView.findViewById(R.id.HinhAnh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent intent = new Intent(context, NoiDungChiTiet.class);
                    intent.putExtra("NoiDungPhim",listPhim.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
