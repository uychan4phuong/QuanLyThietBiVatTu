/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MainForm;
import View.VatTu.CapNhatTieuThuVatTuForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Xử lý liên quan đến Vật tư
 * @author Nguyễn Văn Sơn
 * @version 1.0
 */
public class VatTuController {
    
    private static VatTuController INSTANCE = null;
    private CapNhatTieuThuVatTuForm capNhatTieuThuVatTuForm;
    
    private VatTuController() {
        
        taoLangNgheYeuCauSuDungVatTuForm();
        taoLangNgheCapNhatTieuThuVatTuForm();
    }
    
    /**
     * Singleton pattern
     * @return thể hiện duy nhất của MainController
     */
    public static VatTuController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VatTuController();
        }
        return INSTANCE;
    }
    
    /**
     * Tạo lắng nghe cho các Component trong giao diện Yêu cầu Sử dụng vật tư thuộc {@code MainForm}
     */
    private void taoLangNgheYeuCauSuDungVatTuForm(){
        MainForm.getInstance().buttonCapNhatTieuThuVatTuActionPerformed(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (capNhatTieuThuVatTuForm == null) {
                    capNhatTieuThuVatTuForm = new CapNhatTieuThuVatTuForm(MainForm.getInstance(), true);
                    
                    
                }
                capNhatTieuThuVatTuForm.setVisible(true);
            }
        });
    }
    
    /**
     * Tạo lắng nghe cho các Component trong giao diện Cập nhật tiêu thụ vật tư thuộc {@code MainForm}
     */
    private void taoLangNgheCapNhatTieuThuVatTuForm(){
        
    }
    
    //===== YÊU CẦU SỬ DỤNG VẬT TƯ: của Sơn =====
    
    //===== CẬP NHÂT TIÊU THỤ VẬT TƯ: của Sơn =====
}
