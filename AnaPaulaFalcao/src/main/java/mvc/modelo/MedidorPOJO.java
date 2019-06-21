/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

/**
 *
 * @author User
 */
public class MedidorPOJO {
    int serial;
    String nome;
    String tabela;
    
    public MedidorPOJO(int serial, String nome, String tabela) {
        this.serial = serial;
        this.nome = nome;
        this.tabela = tabela;
    }
}
