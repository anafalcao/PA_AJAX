package dto;

import java.io.Serializable;

public class Parametros implements Serializable{
    private String serial;
    private String medidor;
    private String temperatura;
    private String umidade;
    private String datahora;
    private Integer serialno_medidores;
    private String nome;
    private String tabela;

    public String getMedidor() {
        return this.medidor;
    }

    public void setMedidor(String medidor) {
        this.medidor = medidor;
    }

    public String getTemperatura() {
        return this.temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
    
    public String getUmidade() {
        return this.umidade;
    }

    public void setUmidade(String umidade) {
        this.umidade = umidade;
    }
    
    public String getDataHora() {
        return this.datahora;
    }

    public void setDataHora(String datahora) {
        this.datahora = datahora;
    }
    
    
     public String getSerial() {
        return this.serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    
    public Integer getSerialno_medidores() {
        return this.serialno_medidores;
    }

    public void setSerialno_medidores(Integer serialno_medidores) {
        this.serialno_medidores = serialno_medidores;
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTabela() {
        return this.tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

}
