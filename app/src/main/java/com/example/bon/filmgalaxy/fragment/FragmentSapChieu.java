package com.example.bon.filmgalaxy.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bon.filmgalaxy.R;
import com.example.bon.filmgalaxy.adapter.CustomAdapterPhim;
import com.example.bon.filmgalaxy.app.LinkInternet;
import com.example.bon.filmgalaxy.model.TypePhim;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bon on 3/17/2017.
 */

public class FragmentSapChieu extends Fragment {
    private CustomAdapterPhim customAdapterPhim;
    private ArrayList<TypePhim> arrayListSapChieu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sapchieu,container,false);
        RecyclerView recyclerView_SapChieu = (RecyclerView) view.findViewById(R.id.recyclerView_SapChieu);
        arrayListSapChieu = new ArrayList<>();
        customAdapterPhim = new CustomAdapterPhim(arrayListSapChieu,getActivity());
        PhimSapChieu(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_SapChieu.setLayoutManager(layoutManager);
        recyclerView_SapChieu.setItemAnimator(new DefaultItemAnimator());
        recyclerView_SapChieu.setAdapter(customAdapterPhim);
//        recyclerView_SapChieu.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
////                Intent intent = new Intent(getActivity(), NoiDungChiTiet.class);
////                intent.putExtra("NoiDungPhim",arrayListSapChieu.get(position));
////                startActivity(intent);
//                Toast.makeText(getActivity(), "Sap Chieu", Toast.LENGTH_SHORT).show();
//            }
//        }));
        return view;
    }

    public void PhimSapChieu(Activity activity){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LinkInternet.Fragment_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),response + "", Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < response.length();i++){
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            TypePhim typePhim = new TypePhim();
                            typePhim.setTenPhim(jsonObject.getString("tenphim"));
                            typePhim.setTheLoai(jsonObject.getString("theloaiphim"));
                            typePhim.setHinhAnh(jsonObject.getString("hinhanh"));
                            typePhim.setUrlVideo(jsonObject.getString("urlvideo"));
                            typePhim.setDoDai(jsonObject.getString("dodai"));
                            typePhim.setCumRap(jsonObject.getString("cumrap"));
                            typePhim.setDienVien(jsonObject.getString("dienvien"));
                            typePhim.setTrangThai(jsonObject.getString("trangthai"));
                            typePhim.setChiTietNoiDung(jsonObject.getString("mota"));
                            arrayListSapChieu.add(typePhim);
                            customAdapterPhim.notifyDataSetChanged();
                            Log.d("Toast",typePhim.getTenPhim());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG","Error" + error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("idTrangThai",String.valueOf(1));
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }
}
