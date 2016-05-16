/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Chứa thông tin tài khoản đăng nhập
 * @author Nguyễn Văn Sơn
 */
public class TaiKhoan {
    
    private static TaiKhoan INSTANCE = null;
    private String tenTaiKhoan;
    private String matKhau;
    private int kieuNguoiDung;
    
    private TaiKhoan(){
        
    }
    
    public static TaiKhoan getInstance(){
        if(INSTANCE == null){
            INSTANCE = new TaiKhoan();
        }
        return INSTANCE;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getKieuNguoiDung() {
        return kieuNguoiDung;
    }

    public void setKieuNguoiDung(int kieuNguoiDung) {
        this.kieuNguoiDung = kieuNguoiDung;
    }
    
}
