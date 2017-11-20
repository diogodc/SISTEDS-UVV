/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Felipe
 */
public class PoupancaEmBanco {
    private Integer id;
    private float jurosMensal;
    private String agencia;
    private Integer numPoup;
    private float saldo;
    private String nomeCliente;
    private float taxaAdm;
    private String nomeBanco;

    public PoupancaEmBanco(Integer id, float jurosMensal, String agencia, Integer numPoup, float saldo, String nomeCliente, float taxaAdm, String nomeBanco) {
        this.id = id;
        this.jurosMensal = jurosMensal;
        this.agencia = agencia;
        this.numPoup = numPoup;
        this.saldo = saldo;
        this.nomeCliente = nomeCliente;
        this.taxaAdm = taxaAdm;
        this.nomeBanco = nomeBanco;
    }
    
    public PoupancaEmBanco(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getJurosMensal() {
        return jurosMensal;
    }

    public void setJurosMensal(float jurosMensal) {
        this.jurosMensal = jurosMensal;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Integer getNumPoup() {
        return numPoup;
    }

    public void setNumPoup(Integer numPoup) {
        this.numPoup = numPoup;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public float getTaxaAdm() {
        return taxaAdm;
    }

    public void setTaxaAdm(float taxaAdm) {
        this.taxaAdm = taxaAdm;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
    
    
}
