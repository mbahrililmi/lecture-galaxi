/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Lisda
 */
public class ttrs_penjualan_detail {
    Connection _Cnn;
    public String faktur, prd_id;
    public Double qty, diskon;
    public String _Akses = "";
    
    public void hapusdata(String faktur, String prd_id) {
        System.out.println("pesan kode" + faktur);
        try {
            _Cnn = DBO.getConnection();
            String _sql = "DELETE FROM `ttrs_penjualan_detail` WHERE `faktur` ='" + faktur + "' and `prd_id` ='" + prd_id + "'";
            System.out.println(_sql);
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            prs.executeUpdate();
            System.out.println(_sql);
            prs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simpandata() {
        try {
            String _sql = "";
            _Cnn = DBO.getConnection();
            _sql = "INSERT INTO ttrs_penjualan_detail"
                    + " VALUES "
                    + " ('" + faktur + "',"
                    + " '" + prd_id + "',"
                    + " '" + qty + "',"
                    + " '" + diskon + "')";
            System.out.println(_sql + "ttrs simpan");
            int status = DBO.executestatement(_sql);
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            if (status == 1) {
                prs.setString(1, this.faktur);
                prs.setString(2, this.prd_id);
                prs.setDouble(3, this.qty);
                prs.setDouble(4, this.diskon);
                prs.executeUpdate();
                System.out.println(_sql);
                _Cnn = DBO.getConnection();
                prs.close();
            }                    
        } catch (Exception err) {
            //System.out.println("Class.ttrs_penjualan_detail.simpandata error()" + err);  
        }
    }
    
    public void Cari (String _faktur, String _prd_id) {
        this.faktur = _faktur;
        this.prd_id = _prd_id;
        try {
            _Akses = "";
            _Cnn = DBO.getConnection();
            String SQL = "SELECT * "
                    + "FROM `ttrs_penjualan_detail` "
                    + "WHERE `faktur` = '" + faktur + "` AND prd_id =`" + prd_id + "'";
            Statement st = _Cnn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                _Akses = "-";
                this.faktur = rs.getString(1);
                this.prd_id = rs.getString(2);
                this.qty = rs.getDouble(3);
                this.diskon = rs.getDouble(4);
                System.out.println(SQL);
                _Cnn = DBO.getConnection();
            }
            st.close();
            System.out.println("_" + st.toString());
        } catch (Exception err) {
            System.out.println("_ERROR" + err.toString());
        }
    }
    
    public void updatedata() {
        boolean result = false;
        String _sql = "";
        try {
            _Cnn = DBO.getConnection();
            Statement st = _Cnn.createStatement();
            _sql = "UPDATE ttrs_penjualan_detail SET"
                    +"  `faktur` =  '" + String.valueOf(this.faktur) + ","
                    +"  `prd_id` =  '" + String.valueOf(this.prd_id) + ","
                    +"  `qty` =  '" + String.valueOf(this.qty) + ","
                    +"  `diskon` =  '" + String.valueOf(this.diskon) + ""
                    + " WHERE `faktur` = '" + String.valueOf(this.faktur) + "` AND prd_id=`" + String.valueOf(this.prd_id) + "'";
            
            PreparedStatement prs = _Cnn.prepareStatement(_sql);    
                prs.setString(1, this.faktur);
                prs.setString(2, this.prd_id);
                prs.setDouble(3, this.qty);
                prs.setDouble(4, this.diskon);
                prs.executeUpdate();
                System.out.println(_sql);
                _Cnn = DBO.getConnection();
                prs.close();
            
        } catch (Exception err) {
            System.out.println("ERROR " + err + " " + _Cnn + " " + _sql);           
        }
    }
    
    public void simpanORupdate() {
        try {
            String _sql;
            _Cnn = DBO.getConnection();
            if (_Akses.equals("")) {
                _sql = "INSER INTO ttrs_penjualan_detail"
                        + " VALUES"
                        + " ('" + String.valueOf(faktur) + "',"
                        + " '" + String.valueOf(prd_id) + "',"
                        + " '" + String.valueOf(qty) + "',"
                        + " '" + String.valueOf(diskon) + "')";
                System.out.println(_sql);
            } else {
                _sql = "UPDATE ttrs_penjualan_detail SET"
                        + "  `faktur` = '" + String.valueOf(faktur) + ","
                        + "  `prd_id` = '" + String.valueOf(prd_id) + ","
                        + "  `qty` = '" + String.valueOf(qty) + "'"
                        + " `diskon` = '" + String.valueOf(diskon) + ""
                        + " WHERE ``faktur` =  '"
                        + String.valueOf(this.faktur) + "'AND prd_id=`" + String.valueOf(this.prd_id) + "`";
                System.out.println(_sql);
            }
            int status = DBO.executestatement(_sql);
            if (status == 1) {
                PreparedStatement prs = _Cnn.prepareStatement(_sql);
                prs.setString(1, this.faktur);
                prs.setString(2, this.prd_id);
                prs.setDouble(3, this.qty);
                prs.setDouble(4, this.diskon);
                prs.executeUpdate();
                System.out.println(_sql);
                prs.close();
            }
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "simpan or udpate Penjualan Detail ERROR : " + err);
        }
    }
}
