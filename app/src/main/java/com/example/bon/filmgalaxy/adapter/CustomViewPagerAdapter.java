package com.example.bon.filmgalaxy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;
import com.example.bon.filmgalaxy.R;
import com.example.bon.filmgalaxy.app.AppController;
import com.example.bon.filmgalaxy.model.TypeImagePager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bon on 3/13/2017.
 */

public class CustomViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<TypeImagePager> arrayHinh;
    ImageLoader imageLoader = AppController.getmInstance().getImageLoader();

    public CustomViewPagerAdapter(Context mContext, List<TypeImagePager> arrayHinh) {
        this.mContext = mContext;
        this.arrayHinh = arrayHinh;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayHinh.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = inflater.inflate(R.layout.custom_layout_pager,container,false);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView);

        TypeImagePager m = arrayHinh.get(position);
        Picasso.with(mContext).load(m.getHinhAnh()).into(imageView);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
