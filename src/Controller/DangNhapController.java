/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseQuery;
import Model.TaiKhoan;
import View.DangNhapForm;
import View.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Xử lý Đăng nhập
 *
 * @author Nguyễn Văn Sơn
 * @version 1.0
 */
public class DangNhapController {

    private static DangNhapController INSTANCE = null;
    
    private DangNhapForm dangNhapForm;

    private DangNhapController() {
        //Khởi tạo Form đăng nhập và hiển thị
        dangNhapForm = DangNhapForm.getInstance();
        dangNhapForm.setVisible(true);
        
        //Tạo lắng nghe cho button Đăng nhập
        dangNhapForm.buttonDangNhapActionPerformed(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Lấy thông tin đăng nhập nhập vào
                String ten = dangNhapForm.getTextTenDangNhap().getText();
                char[] tmp = dangNhapForm.getTextMatKhau().getPassword();
                String matkhau = MD5.getMD5( new String(tmp).getBytes());
                
                
                if ( DatabaseQuery.getInstance().queryTaiKhoan(ten, matkhau)) {
                    //Khởi tạo Điều khiển và giao diện Màn hình chính
                    MainController.getInstance();

                    MainForm.getInstance().setVisible(true);
                    //Phân quyền hiển thị cho người dùng
                    if (TaiKhoan.getInstance().getKieuNguoiDung() == 0) {
                        MainForm.getInstance().getTabbedPaneMain().removeTabAt(4);
                        MainForm.getInstance().getTabbedPaneMain().removeTabAt(4);
                    } else {
                        MainForm.getInstance().getTabbedPaneMain().removeTabAt(0);
                        MainForm.getInstance().getTabbedPaneMain().removeTabAt(0);
                        MainForm.getInstance().getTabbedPaneMain().removeTabAt(0);
                        MainForm.getInstance().getTabbedPaneMain().removeTabAt(0);
                    }
                    
                    //Hiển thị giao diện chính và ẩn giao diện đăng nhập
                    dangNhapForm.setVisible(false);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại");
                }
            }
        });
    }
    
    /**
     * Singleton pattern
     * @return hể hiện duy nhất của DangNhapController
     */
    public static DangNhapController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DangNhapController();
        }
        return INSTANCE;
    }
    
    public static void main(String[] args) {
        DangNhapController.getInstance();
    }
}