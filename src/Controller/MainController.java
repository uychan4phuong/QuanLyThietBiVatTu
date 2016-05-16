/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TaiKhoan;
import View.DangNhapForm;
import View.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Nguyễn Văn Sơn
 * @version 1.0
 */
public class MainController {
    
    private static MainController INSTANCE = null;
    
    private ThietBiController thietBiController;
    private VatTuController vatTuController;

    private MainController() {
        
        //Khởi tạo Controller cho Thiết bị và Vật tư
        thietBiController = ThietBiController.getInstance();
        vatTuController = VatTuController.getInstance();
        
        taoLangNghe();
    }

    
    /**
     * Singleton pattern
     * @return thể hiện duy nhất của MainController
     */
    public static MainController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MainController();
        }
        return INSTANCE;
    }
    
    /**
     * Tạo lắng nghe cho các Component chung trong giao diện:
     * Đăng xuất, Hướng dẫn
     */
    public void taoLangNghe(){
        MainForm.getInstance().buttonDangXuatActionPerformed(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                MainForm.getInstance().setVisible(false);
                DangNhapForm.getInstance().setVisible(true);
                
                if (TaiKhoan.getInstance().getKieuNguoiDung() == 1) {
                    MainForm.getInstance().getTabbedPaneMain().insertTab("Thống kê sử dụng vật tư", null, MainForm.getInstance().getPanelThongKeVatTu(), null, 0);
                    MainForm.getInstance().getTabbedPaneMain().insertTab("Thống kê thiết bị đơn vị", null, MainForm.getInstance().getPanelThongKeThietBi(), null, 0);
                    MainForm.getInstance().getTabbedPaneMain().insertTab("Quản lý vật tư", null, MainForm.getInstance().getPanelQuanLyVatTu(), null, 0);
                    MainForm.getInstance().getTabbedPaneMain().insertTab("Quản lý thiết bị", null, MainForm.getInstance().getPanelQuanLyThietBi(), null, 0);
                } else {
                    MainForm.getInstance().getTabbedPaneMain().insertTab("Cập nhập tiêu thụ vật tư", null, MainForm.getInstance().getPanelCapNhatTieuThuVatTu(), null, 4);
                    MainForm.getInstance().getTabbedPaneMain().insertTab("Yêu cầu sử dụng vật tư", null, MainForm.getInstance().getPanelYeuCauVatTu(), null, 4);
                }
            }
        });
    }
}
