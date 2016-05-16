/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MainForm;
import View.ThietBi.CapNhatThongTinThietBiForm;
import View.ThietBi.ThemThietBiForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Xử lý liên quan đến thiết bị
 *
 * @author Nguyễn Văn Sơn
 * @version 1.0
 */
public class ThietBiController {
    //===== phần chung =====
    
    private static ThietBiController INSTANCE = null;
    private static ThemThietBiForm themThietBiForm;
    private static CapNhatThongTinThietBiForm capNhatThongTinThietBiForm;

    private ThietBiController() {
        taoLangNgheQuanLyForm();
        taoLangNgheThongKeForm();
    }

    /**
     * Singleton pattern
     *
     * @return thể hiện duy nhất của MainController
     */
    public static ThietBiController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThietBiController();
        }
        return INSTANCE;
    }

    /**
     * Tạo lắng nghe cho các Component trong Giao diện Quản Lý Thiết Bị thuộc {@code MainForm}
     */
    private void taoLangNgheQuanLyForm() {
        MainForm.getInstance().buttonThemThietBiActionPerformed(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (themThietBiForm == null) {
                    themThietBiForm = new ThemThietBiForm(MainForm.getInstance(), true);
                    
                    taoLangNgheThemThietBiForm();
                }
                themThietBiForm.setVisible(true);
            }
        });
        MainForm.getInstance().buttonCapNhatThietBiActionPerformed(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (capNhatThongTinThietBiForm == null) {
                    capNhatThongTinThietBiForm = new CapNhatThongTinThietBiForm(MainForm.getInstance(), true);
                    
                    taoLangNgheCapNhatThongTinThietBiForm();
                }
                capNhatThongTinThietBiForm.setVisible(true);
            }
        });
    }

    /**
     * Tạo lắng nghe cho các Component trong Thống kê Thiết Bị thuộc {@code MainForm}
     */
    private void taoLangNgheThongKeForm() {

    }

    //===== THÊM THIẾT BỊ: của ai =====
    
    /**
     * Tạo lắng nghe cho các Component trong Giao Diện Thêm Thiết Bị: {@code ThemThietBiForm}
     */
    private void taoLangNgheThemThietBiForm(){
        themThietBiForm.buttonXacNhanActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Xử lý thông tin
                
                //Nếu đúng thì xác nhận
                themThietBiForm.setVisible(false);
            }
        });
    }

    //===== CẬP NHẬT THÔNG TIN THIẾT BỊ của ai =====
    
    /**
     * Tạo lắng nghe cho các Component trong Giao Diện Cập nhập thông tin Thiết Bị: {@code CapNhatThongTinThietBiForm}
     */
    private void taoLangNgheCapNhatThongTinThietBiForm(){
        capNhatThongTinThietBiForm.buttonXacNhanActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Xử lý thông tin
                
                //Nếu đúng thì xác nhận
                capNhatThongTinThietBiForm.setVisible(false);
            }
        });
    }
}
