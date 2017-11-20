/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PoupancaDAO;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.PoupancaEmBanco;
import utilitarios.Tabela;
import visao.Historico;
import visao.Sisbank;

/**
 *
 * @author Felipe
 */
public class PoupancaControle {
    PoupancaEmBanco peb = new PoupancaEmBanco();
    PoupancaDAO pDAO = new PoupancaDAO();
    
    public void atualizaSaldo(PoupancaEmBanco peb){
        peb.setSaldo(peb.getSaldo()+(peb.getSaldo()*(peb.getJurosMensal()/100)));
        pDAO.editarPEB(peb);
    }
    
    public void atualizaSaldo(float taxaJuros, PoupancaEmBanco peb){
        peb.setSaldo(peb.getSaldo()+(peb.getSaldo()*(taxaJuros/100)));
        pDAO.editarPEB(peb);
        
    }
    
    public void atualizaSaldo(int numPoup, float taxaJuros){
        peb = pDAO.pebX(numPoup);
        peb.setSaldo(peb.getSaldo()+(peb.getSaldo()*(taxaJuros/100)));
        pDAO.editarPEB(peb);
    }
    
    public void operacao(int opt, float valor, PoupancaEmBanco peb){
        //deposito
        if(opt==1){
            peb.setSaldo(peb.getSaldo()+valor);
            pDAO.editarPEB(peb);
        }
        //saque
        else if(opt==2){
            peb.setSaldo(peb.getSaldo()-valor-(valor*(peb.getTaxaAdm()/100)));
            pDAO.editarPEB(peb);
        }
        //aplica juros
        else{
            peb.setSaldo(peb.getSaldo()+(peb.getSaldo()*(valor/100)));
            pDAO.editarPEB(peb);
        }
        
        pDAO.historicoPEB(opt, valor, peb);
    }
    
    public void salvar(PoupancaEmBanco peb){
        pDAO.salvarPEB(peb);
    }
    
    public void editar(PoupancaEmBanco peb){
        pDAO.editarPEB(peb);
    }
    
    public void excluir(PoupancaEmBanco peb){
        pDAO.excluirPEB(peb);
    }
    
    public void pesquisar(){
        TableRowSorter pesquisar = null;
        pesquisar = new TableRowSorter<TableModel>(Sisbank.jTable.getModel());
        Sisbank.jTable.setRowSorter(pesquisar);
        String text = Sisbank.jTextField_Pesq.getText();
        if (text.length() == 0) {
            pesquisar.setRowFilter(null);
        } else {
            pesquisar.setRowFilter(RowFilter.regexFilter("(?i)"+text));
        }
    }  
    
    public void pesquisar_hist(){
        TableRowSorter pesquisar = null;
        pesquisar = new TableRowSorter<TableModel>(Historico.jTable.getModel());
        Historico.jTable.setRowSorter(pesquisar);
        String text = Historico.jTextField_Pesq.getText();
        if (text.length() == 0) {
            pesquisar.setRowFilter(null);
        } else {
            pesquisar.setRowFilter(RowFilter.regexFilter("(?i)"+text));
        }
    }   
    
    
    public void listarPEB(){
        ArrayList dados = pDAO.carregarPEB();
        String [] Colunas = new String[]{"ID","CLIENTE","BANCO","AGÊNCIA","Nº. POUPANÇA","SALDO","JUROS MENSAL","TAXA ADM."};

        //Criando e preenchendo tabela    
        Tabela modelo = new Tabela(dados, Colunas);
        Sisbank.jTable.setModel(modelo);
        //ID
        Sisbank.jTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);  
        Sisbank.jTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        Sisbank.jTable.getColumnModel().getColumn(0).setMaxWidth(0);  
        Sisbank.jTable.getColumnModel().getColumn(0).setMinWidth(0);
        //CLIENTE
        Sisbank.jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        //BANCO
        Sisbank.jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        //AGÊNCIA
        Sisbank.jTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        //Nº. POUPANÇA
        Sisbank.jTable.getColumnModel().getColumn(4).setPreferredWidth(35);
        //SALDO
        Sisbank.jTable.getColumnModel().getColumn(5).setPreferredWidth(35);
        //JUROS MENSAL
        Sisbank.jTable.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);  
        Sisbank.jTable.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        Sisbank.jTable.getColumnModel().getColumn(6).setMaxWidth(0);  
        Sisbank.jTable.getColumnModel().getColumn(6).setMinWidth(0);
        //TAXA ADM.
        Sisbank.jTable.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);  
        Sisbank.jTable.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        Sisbank.jTable.getColumnModel().getColumn(7).setMaxWidth(0);  
        Sisbank.jTable.getColumnModel().getColumn(7).setMinWidth(0);
        
        Sisbank.jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Sisbank.jTable.setAutoCreateRowSorter(true); 
    }
    
    public void list_histPEB(){
        ArrayList dados = pDAO.carregar_histPEB();
        String [] Colunas = new String[]{"ID","DATA","HORA","OPERAÇÃO","VALOR","SALDO"};

        //Criando e preenchendo tabela    
        Tabela modelo = new Tabela(dados, Colunas);
        Historico.jTable.setModel(modelo);
        //ID
        Historico.jTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);  
        Historico.jTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        Historico.jTable.getColumnModel().getColumn(0).setMaxWidth(0);  
        Historico.jTable.getColumnModel().getColumn(0).setMinWidth(0);
        //DATA
        Historico.jTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        //HORA
        Historico.jTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        //OPERAÇÃO
        Historico.jTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        //VALOR
        Historico.jTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        //SALDO
        Historico.jTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        
        Historico.jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Historico.jTable.setAutoCreateRowSorter(true); 
    }
}
