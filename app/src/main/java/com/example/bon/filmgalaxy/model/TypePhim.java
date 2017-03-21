package com.example.bon.filmgalaxy.model;

import java.io.Serializable;

/**
 * Created by Bon on 3/18/2017.
 */

public class TypePhim implements Serializable {
    String TenPhim,HinhAnh,NgayChieu,TheLoai,DoDai,DienVien,DaoDien,CumRap,ChiTietNoiDung,UrlVideo,TrangThai;
    public TypePhim(){
    }

    public TypePhim(String tenPhim, String hinhAnh, String ngayChieu, String theLoai, String doDai, String dienVien, String daoDien, String cumRap, String chiTietNoiDung, String urlVideo, String trangThai) {
        TenPhim = tenPhim;
        HinhAnh = hinhAnh;
        NgayChieu = ngayChieu;
        TheLoai = theLoai;
        DoDai = doDai;
        DienVien = dienVien;
        DaoDien = daoDien;
        CumRap = cumRap;
        ChiTietNoiDung = chiTietNoiDung;
        UrlVideo = urlVideo;
        TrangThai = trangThai;
    }

    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String tenPhim) {
        TenPhim = tenPhim;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getNgayChieu() {
        return NgayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        NgayChieu = ngayChieu;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getDoDai() {
        return DoDai;
    }

    public void setDoDai(String doDai) {
        DoDai = doDai;
    }

    public String getDienVien() {
        return DienVien;
    }

    public void setDienVien(String dienVien) {
        DienVien = dienVien;
    }

    public String getDaoDien() {
        return DaoDien;
    }

    public void setDaoDien(String daoDien) {
        DaoDien = daoDien;
    }

    public String getCumRap() {
        return CumRap;
    }

    public void setCumRap(String cumRap) {
        CumRap = cumRap;
    }

    public String getChiTietNoiDung() {
        return ChiTietNoiDung;
    }

    public void setChiTietNoiDung(String chiTietNoiDung) {
        ChiTietNoiDung = chiTietNoiDung;
    }

    public String getUrlVideo() {
        return UrlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        UrlVideo = urlVideo;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
