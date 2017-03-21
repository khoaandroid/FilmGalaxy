package com.example.bon.filmgalaxy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bon.filmgalaxy.model.TypePhim;
import com.squareup.picasso.Picasso;

public class NoiDungChiTiet extends AppCompatActivity {
    TextView txtTenPhim,txtKhoiChieu,txtTheLoai,txtDoDai,txtDienVien,txtDaoDien,txtCumRap,txtMoTa;
    ImageView imagHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_chi_tiet);
        AnhXa();
        Intent intentGet = getIntent();
        final TypePhim typePhim = (TypePhim) intentGet.getSerializableExtra("NoiDungPhim");
        if (typePhim.getTrangThai().equals("1")){
            txtTenPhim.setText(typePhim.getTenPhim());
            txtCumRap.setText("Cụm Rạp: "+typePhim.getCumRap());
            txtKhoiChieu.setText( "Khởi Chiếu: " +typePhim.getNgayChieu());
            txtTheLoai.setText( "Thể Loại: "+typePhim.getTheLoai());
            txtDoDai.setText("Độ Dài: "+typePhim.getDoDai());
            txtDienVien.setText("Diễn Viên: "+typePhim.getDienVien());
            txtDaoDien.setText("Dao Diễn: "+typePhim.getDaoDien());
            txtMoTa.setText(typePhim.getChiTietNoiDung());
            Picasso.with(this).load(typePhim.getHinhAnh()).into(imagHinh);
        }else{
            txtTenPhim.setText(typePhim.getTenPhim());
            txtCumRap.setText("Cụm Rạp: "+typePhim.getCumRap());
            txtKhoiChieu.setText( "Khởi Chiếu: " +typePhim.getNgayChieu());
            txtTheLoai.setText( "Thể Loại: "+typePhim.getTheLoai());
            txtDoDai.setText("Độ Dài: "+typePhim.getDoDai());
            txtDienVien.setText("Diễn Viên: "+typePhim.getDienVien());
            txtDaoDien.setText("Dao Diễn: "+typePhim.getDaoDien());
            txtMoTa.setText(typePhim.getChiTietNoiDung());
            Picasso.with(this).load(typePhim.getHinhAnh()).into(imagHinh);
        }
        imagHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoiDungChiTiet.this,ScreenYoutube.class);
                intent.putExtra("IdYoutube",typePhim.getUrlVideo());
                startActivity(intent);
            }
        });


    }

    private void  AnhXa(){
        txtTenPhim = (TextView)findViewById(R.id.txtTenPhim);
        txtKhoiChieu = (TextView)findViewById(R.id.txtNgayChieu);
        txtTheLoai = (TextView)findViewById(R.id.txtTheLoai);
        txtDoDai = (TextView)findViewById(R.id.txtDoDai);
        txtDienVien = (TextView)findViewById(R.id.txtDienVien);
        txtDaoDien = (TextView)findViewById(R.id.txtDaoDien);
        txtCumRap = (TextView)findViewById(R.id.txtCumRap);
        txtMoTa  = (TextView)findViewById(R.id.txtMoTa);
        imagHinh = (ImageView)findViewById(R.id.imageHinhAnh);
    }
}
