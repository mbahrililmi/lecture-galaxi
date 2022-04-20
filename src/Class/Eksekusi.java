/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author Lisda
 */
public class Eksekusi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBO tesKoneksi = new DBO();
        tesKoneksi.makeConnect();
        ttrs_penjualan_detail tpd = new ttrs_penjualan_detail();
        //tpd.hapusdata("F001", "PD002");
    }
    
}
