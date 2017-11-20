
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.Timer;
import modelo.PoupancaEmBanco;
import utilitarios.ConectaBanco;
import utilitarios.DataHora;


public class PoupancaDAO {
    ConectaBanco conexao = new ConectaBanco();
    DataHora dth = new DataHora();
    
    public void salvarPEB(PoupancaEmBanco peb){
      conexao.conecta();  
      try{
            String sql = "INSERT INTO tb_poupanca(jurosMensal,agencia,numPoup,saldo,nomeCliente,taxaAdm,nomeBanco) VALUES (?,?,?,?,?,?,?);";
            
            PreparedStatement comando = conexao.conn.prepareStatement(sql);
            comando.setFloat (1, peb.getJurosMensal());
            comando.setString (2, peb.getAgencia());
            comando.setInt (3, peb.getNumPoup());
            comando.setFloat (4, peb.getSaldo());
            comando.setString (5, peb.getNomeCliente());
            comando.setFloat(6, peb.getTaxaAdm());
            comando.setString (7, peb.getNomeBanco());

            comando.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
      }
    }
    
    public void historicoPEB(int opt, float valor, PoupancaEmBanco peb){
      conexao.conecta();  
      try{
            String sql = "INSERT INTO tb_historico(data,hora,operacao,valor,saldo) VALUES (?,?,?,?,?);";
            
            int day, month, year;
            int second, minute, hour;
            GregorianCalendar date = new GregorianCalendar();
            day = date.get(Calendar.DAY_OF_MONTH);
            month = date.get(Calendar.MONTH);
            year = date.get(Calendar.YEAR);
            
            second = date.get(Calendar.SECOND);
            minute = date.get(Calendar.MINUTE);
            hour = date.get(Calendar.HOUR);
            
            String data = year+"-"+month+"-"+day;
            String hora = hour+":"+minute+":"+second;
            
            
            PreparedStatement comando = conexao.conn.prepareStatement(sql);
            comando.setString(1, data);
            comando.setString(2, hora);
            comando.setInt(3, opt);
            comando.setFloat(4, valor);
            comando.setFloat(5, peb.getSaldo());

            comando.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
      }
    }
    
    public void editarPEB(PoupancaEmBanco peb){
      conexao.conecta();
      try{      
            String sql = "UPDATE tb_poupanca SET jurosMensal = ?, agencia = ?, numPoup = ?, saldo = ?, nomeCliente = ?, taxaAdm = ?, nomeBanco = ? "
                    + "WHERE id = ? ";
            PreparedStatement comando = conexao.conn.prepareStatement(sql);
            comando.setFloat(1, peb.getJurosMensal());
            comando.setString (2, peb.getAgencia());
            comando.setFloat(3, peb.getNumPoup());
            comando.setFloat (4, peb.getSaldo());
            comando.setString (5, peb.getNomeCliente());
            comando.setFloat (6, peb.getTaxaAdm());
            comando.setString (7, peb.getNomeBanco());
            comando.setInt (8, peb.getId());

            comando.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
      }
    }  
    
    public void excluirPEB(PoupancaEmBanco peb){
      conexao.conecta();
      try{
            String sql = "DELETE FROM tb_poupanca WHERE id = ?";
            PreparedStatement comando = conexao.conn.prepareStatement(sql);
            comando.setInt (1, peb.getId());
            comando.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
      }
    }
    
    public PoupancaEmBanco pebX(int numPoup){
        conexao.conecta();
        try{
            String sql = "SELECT * FROM tb_poupanca WHERE numPoup = ?";
            PreparedStatement comando = conexao.conn.prepareStatement(sql);
            comando.setInt(1, numPoup);
            ResultSet rs = comando.executeQuery();
            
            if(rs!= null && rs.next()){
            PoupancaEmBanco peb = new PoupancaEmBanco();
            peb.setId(rs.getInt("id"));
            peb.setJurosMensal(rs.getFloat("jurosMensal"));
            peb.setAgencia(rs.getString("agencia"));
            peb.setNumPoup(rs.getInt("numPoup"));
            peb.setSaldo(rs.getFloat("saldo"));
            peb.setNomeCliente(rs.getString("nomeCliente"));
            peb.setTaxaAdm(rs.getFloat("taxaAdm"));
            peb.setNomeBanco(rs.getString("nomeBanco"));
            
            return peb;}
  
        }catch(Exception e){
            e.printStackTrace();
      }
        return null;
    }
    
    public ArrayList carregarPEB(){
        //Recuperando dados
        conexao.conecta();
        ArrayList dados = new ArrayList();
        String sql = "SELECT * FROM tb_poupanca";

        conexao.executaSQL(sql);
            try{
                conexao.rs.first();
                do{ 
                    dados.add(new Object[]{
                        conexao.rs.getInt("id"),
                        conexao.rs.getString("nomeCliente"),
                        conexao.rs.getString("nomeBanco"),
                        conexao.rs.getString("agencia"),
                        conexao.rs.getInt("numPoup"),
                        conexao.rs.getFloat("saldo"),
                        conexao.rs.getFloat("jurosMensal"),
                        conexao.rs.getFloat("taxaAdm")});                
                }while(conexao.rs.next()); 
                return dados; 
            }catch (SQLException ex){
            }
        return dados;    
    }
    
    
    public ArrayList carregar_histPEB(){
        //Recuperando dados
        conexao.conecta();
        ArrayList dados = new ArrayList();
        String sql = "SELECT * FROM tb_historico";

        conexao.executaSQL(sql);
            try{
                conexao.rs.first();
                do{ 
                    dados.add(new Object[]{
                        conexao.rs.getInt("id"),
                        dth.formatoDMY(conexao.rs.getDate("data")),
                        conexao.rs.getString("hora"),
                        tipo(conexao.rs.getString("operacao")),
                        conexao.rs.getFloat("valor"),
                        conexao.rs.getFloat("saldo")});                
                }while(conexao.rs.next()); 
                return dados; 
            }catch (SQLException ex){
            }
        return dados;    
    }
    
    public String tipo(String opt){
        if(opt.equals("1")){return "1-DEPÓSITO";}
        else if(opt.equals("2")){return "2-SAQUE";}
        else if(opt.equals("3")){return "3-APLICA JUROS";}
        return "";
    }
    
   
}
