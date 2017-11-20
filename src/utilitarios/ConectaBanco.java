/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author FELIPE DE OLIVEIRA VOGEL PENNA
 */
public class ConectaBanco {
   
   public Statement stm;
   public ResultSet rs;
   private String driver = "com.mysql.jdbc.Driver";
   private String caminho = "jdbc:mysql://localhost:3306/sisbank";
   private String usuario = "root";
   private String senha = "";

   public Connection conn;
   
   
    public void conecta() { 

         try {
             conn =  DriverManager.getConnection(caminho,usuario,senha);

             } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"ERRO DE CONEXÃO COM O BANCO", "SICONAT-COPS", JOptionPane.ERROR_MESSAGE);
         }
    }
  
    public void desconecta() { 

         try {
             conn.close();          
             } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"ERRO DE CONEXÃO COM O BANCO", "SICONAT-COPS", JOptionPane.ERROR_MESSAGE);
         }
    }

    public void executaSQL(String sql) { 

         try {
             stm = conn.createStatement();
             rs = stm.executeQuery(sql);
             } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"ERRO DE CONEXÃO COM O BANCO", "SICONAT-COPS", JOptionPane.ERROR_MESSAGE);
         }
    }
}
