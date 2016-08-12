/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

/**
 *
 * @author Adam
 */
public class OtakuDBConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=OtakuDB;userName=sa;password=hi";
        //Connection con = DriverManager.getConnection(connectionURL);
            
        OtakuDBGUI otaku = new OtakuDBGUI();
        otaku.setVisible(true);
        System.out.println("Connected!");

        //Statement stmt = con.createStatement();
        //String sql;
        //sql = "Select * FROM ANIME";
        //sql = "INSERT INTO ANIME VALUES('Naruto', 'Hayato Date', 'Pierrot', 'Katsuyuki Sumisawa', '2002-10-03', '2007-02-08', 220, NULL)";
        
        //stmt.executeUpdate(sql);
    }
    
}
