/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author SON-HA
 */
public class DatabaseQuery {
    
    private static DatabaseQuery INSTANCE = null;

    private Connection conn;
    private Statement stmt;

    private DatabaseQuery() {
        try {
            conn = getHSQLConnection();
            stmt = (Statement) conn.createStatement();
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Kết nối database thất bại");
            System.exit(0);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("đã kết nối database");
    }
        
    /**
     * Singleton pattern
     * @return hể hiện duy nhất của DatabaseQuery
     */
    public static DatabaseQuery getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DatabaseQuery();
        }
        return INSTANCE;
    }

    /**
     * Tạo kết nối đến cơ sở dữ liệu
     * @return đường kết nối tới cơ sở dữ liệu
     * @throws Exception
     */
    public Connection getHSQLConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/group15?useUnicode=yes&characterEncoding=UTF-8";
        return DriverManager.getConnection(url, "root", "");
    }
        
    public boolean queryTaiKhoan(String ten, String matkhau){
        try {
            String querySensor = "SELECT * FROM tai_khoan;";
            ResultSet rs = stmt.executeQuery(querySensor);
            if (!rs.first()) {
                javax.swing.JOptionPane.showMessageDialog(null, "\"Không chưa dữ liệu trong TABLE tai_khoan trong Database!");
            } else {
                do {
                    String ten_tai_khoan = rs.getString("ten_dang_nhap");
                    String mat_khau = rs.getString("mat_khau");
                    int kieu_nguoi_dung = Integer.parseInt( rs.getString("kieu_nguoi_dung"));
                    if (ten_tai_khoan.equals( ten) && mat_khau.equals( matkhau)) {
                        System.out.println("Có tài khoản");
                        TaiKhoan.getInstance().setTenTaiKhoan(ten_tai_khoan);
                        TaiKhoan.getInstance().setMatKhau(matkhau);
                        TaiKhoan.getInstance().setKieuNguoiDung(kieu_nguoi_dung);
                        return true;
                    }
                } while (rs.next());
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi queryTaiKhoan()");
        }
        return false;
    }
    

//    public void addSensor(String sensorID, float longtitude, float latitude) {
//        System.out.println("... đang add Sensor");
//        String queryStr = "INSERT INTO tbl_sensor VALUES(?, ?, ?);";
//        try (PreparedStatement addStmt = conn.prepareStatement(queryStr)) {
//            addStmt.setString(1, sensorID);
//            addStmt.setFloat(2, longtitude);
//            addStmt.setFloat(3, latitude);
//            addStmt.addBatch();
//            addStmt.executeBatch();
//        } catch (SQLException ex) {
//            System.out.println("Lỗi addSensor(): " + ex.getMessage());
//        }
//    }
    
//    public ArrayList<Sensor> querySensor() throws SQLException{
//        ArrayList<Sensor> temp = new ArrayList<Sensor>();
//        String querySensor = "SELECT * FROM tbl_sensor;";
//        ResultSet rs = stmt.executeQuery(querySensor);
//        if (!rs.first()) {
//            System.out.println("Have no record TABLE tbl_sensor!");
//        } else {
//            do {
//                Sensor tmpOne = new Sensor();
//                String sensorID = rs.getString("sensorID");
//                String sensorName = rs.getString("sensorName");
//                float longtitude = rs.getFloat("longtitude");
//                float latitude = rs.getFloat("latitude");
//                tmpOne.setSensorID(sensorID);
//                tmpOne.setSensorName(sensorName);
//                tmpOne.setLongitude(longtitude);
//                tmpOne.setLatitude(latitude);
//                temp.add(tmpOne);
//            } while (rs.next());
//        }
//        return temp;
//    }
    
//    public void addData( String sensorID, String time, float temperature, float humidity) {
//        System.out.println("... đang add Data");
//        String queryStr = "INSERT INTO tbl_data(sensorID, time, temperature, humidity) VALUES( ?, ?, ?, ?);";
//        try (PreparedStatement addStmt = conn.prepareStatement(queryStr)) {
//            addStmt.setString(1, sensorID);
//            addStmt.setString(2, time);
//            addStmt.setFloat(3, temperature);
//            addStmt.setFloat(4, humidity);
//            addStmt.addBatch();
//            addStmt.executeBatch();
//        } catch (SQLException ex) {
//            System.out.println("Lỗi addSensor(): " + ex.getMessage());
//        }
//    }
//    
//    public Vector queryData(String sensorID) throws SQLException{
//        Vector temp = new Vector();
//        String queryData = "SELECT time, temperature, humidity FROM tbl_data WHERE sensorID="+ sensorID + ";";
//        ResultSet rs  = stmt.executeQuery(queryData);
//        if (!rs.first()) {
//            System.out.println("Have no record TABLE tbl_data!");
//        } else {
//            do {
//                Vector tempOne = new Vector();
//                String time = rs.getString("time");
//                float temperature = rs.getFloat("temperature");
//                float humidity = rs.getFloat("humidity");
//                tempOne.add(time);
//                tempOne.add(temperature);
//                tempOne.add(humidity);
//                temp.add(tempOne);
//            } while (rs.next());
//            
//        }
//        return temp;
//    }
//    
//    public ArrayList<ArrayList<Float>> queryWeatherStatistic(String sensorID, String year, String month){
//        ArrayList<Float> listTemperature = new ArrayList<>();
//        ArrayList<Float> listHumidity = new ArrayList<>();
//        ArrayList<ArrayList<Float>> list = new ArrayList<>();
//        list.add(listTemperature);
//        list.add(listHumidity);
//        
//        String queryData = "SELECT time, temperature, humidity FROM tbl_data WHERE sensorID=" + sensorID + ";";
//        ResultSet rs;
//        try { 
//            rs = stmt.executeQuery(queryData);
//            if (!rs.first()) {
//                    System.out.println("Have no record TABLE tbl_data!");
//            } else {
//                do {
//                    String time = rs.getString("time");
//                    String[] tmp = time.split("-");
//                    if (tmp[1].equals(month) && tmp[2].equals(year)) {
//                        list.get(0).add(rs.getFloat("temperature"));
//                        list.get(1).add(rs.getFloat("humidity"));
//                    }
//                } while (rs.next());
//            }
//        } catch (SQLException ex) {
//            System.out.println("Lỗi queryWeatherStatistic()" + ex.getMessage());
//        }
////        for (int j = 0; j < list.get(0).size(); j++) {
////            System.out.print( list.get(0).get(j) + ", " + list.get(1).get(j));
////        }
//        return list;
//    }
//    
//    public ArrayList<ArrayList<String>> queryYearMonthList(String sensorID){
//        ArrayList<ArrayList<String>> yearList = new ArrayList<>();
//        int indexYear = 0;
//        try {
//            String queryData = "SELECT time FROM tbl_data WHERE sensorID="+ sensorID + ";";
//            ResultSet rs = stmt.executeQuery(queryData);
//            if (!rs.first()) {
//                System.out.println("Have no record TABLE tbl_data!");
//            } else {
//                do {
//                    String time = rs.getString("time");
//                    String[] tmp = time.split("-");
//                    boolean markYear = false;
//                    if(yearList != null){
//                        for (int i = 0; i < yearList.size(); i++) {
//                            //kiểm tra đã tồn tại list năm chưa
//                            if (tmp[2].equals(yearList.get(i).get(0))) {
//                                markYear = true;
//                                boolean markMonth = false;
//                                //Đã tồn tại list năm này thì kiểm tra đã tồn tại tháng chưa
//                                for (int j = 1; j < yearList.get(i).size(); j++) {
//                                    if (tmp[1].equals(yearList.get(i).get(j))) {
//                                        markMonth = true;
//                                    }
//                                }
//                                if (!markMonth) {
//                                    yearList.get(i).add(tmp[1]);
//                                }
//                            }
//                        }
//                    }
//                    //chưa tồn tại list năm thì thêm vào cả năm và tháng
//                    if (!markYear) {
//                        //thêm năm mới
//                        ArrayList<String> list = new ArrayList<>();
//                        yearList.add(list);
//                        yearList.get(indexYear).add(tmp[2]);
//                        //thêm tháng mới
//                        yearList.get(indexYear++).add(tmp[1]);
//                    }
//                    
//                } while (rs.next());
//                
//            }
//        } catch (SQLException ex) {
//            System.out.println("Lỗi queryYearList():" + ex.getMessage());
//        }
//        return yearList;
//        for (int i = 0; i < yearList.size(); i++) {
//            for (int j = 0; j < yearList.get(i).size(); j++) {
//                System.out.print(" " + yearList.get(i).get(j));
//            }
//            System.out.println("");
        
//        String[] list = new String[yearList.size()];
//        list = yearList.toArray(list);
//        return list ;
//    }

//    public static void main(String[] args) {
//        DatabaseQuery db = new DatabaseQuery();
//        db.addData("3333", "fgsdfg time", 17, 17);
//    }
}
