/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;
//merupakan logic dari koneksi ke database mysql

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lisda
 */
public class DBO {
    public static String almtDir = System.getProperty("user.dir") + "/src/report/";
    public static Connection vkoneksi = null; //+static
    private boolean blnIsConnected = false;
    private String jServer = "localhost:3306"; //setting sesuai setting install xampp server dan port
    private String jDatabase = "store_akhtar"; //database yang dituju 
    private String jUser = "root"; //isi sesuai dg kriteria user akses data di xampp
    private String jPassword = ""; //sesuaikan dg penggunaan password jika ada
    
    public void setServer(String value) {
        jServer = value;
    }
    
    public void setPassword(String value) {
        jPassword = value;
    }
    
    public void setDatabase(String value) {
        jDatabase = value;
    }
    
    public String getServer() {
        return jServer;
    }
    
    public String gerUser() {
        return jUser;
    }
    
    public String getPassword() {
        return jPassword;
    }
    
    public String getDatabase() {
        return jDatabase;
    }
    
    public static Connection getConnection() {
        return vkoneksi;
    }
    
    public boolean isConnected() {
        return blnIsConnected;
    }
    
//lakukan cek validasi koneksi terlebih dahulu utk mengurangu kegagallan akses database di xampp
    private boolean isValidConf() {
        boolean result = false;
        try {
            if (jServer.equals("")
                    || jDatabase.equals("")
                    || jUser.equals("")) {//|| jUser.equals("")
                //|| jPassword.equals("")) { //**hilangkan komentar '//' jika akses menggunakan password
                result = false;
            } else {
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public boolean makeConnect() {
        String urlValue = "";
        blnIsConnected = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            urlValue = "jdbc:mysql://" + jServer + "/"
                    
                    + jDatabase + "?user=" + jUser
                    + "&password=" + jPassword;
            if (isValidConf()) {
                vkoneksi =
DriverManager.getConnection(urlValue);
                blnIsConnected = true;
                System.out.println("koneksi sesuai kondisi, ditemukan");
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal" + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc.Driver tidak ditemukan");
        }
        if (blnIsConnected == false) {
            System.out.println("Koneksi Gagal");
        }
        return blnIsConnected;
    }
    
    public void konek() {
        vkoneksi = null;
        makeConnect(); }
public static int executestatement (String Q){
int status = 0;
try {
Statement st = getConnection().createStatement();
status = st.executeUpdate(Q);
} catch (SQLException ex) {
Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
}
return status;
}
public static ResultSet executeQuery(String SQL) {
ResultSet rs = null;
try {
Statement st = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
rs = st.executeQuery(SQL);
} catch (SQLException ex) {
Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
}
return rs;
}
}