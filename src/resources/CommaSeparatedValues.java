/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import controller.NaoConformidadeController;

/**
 *
 * @author leona
 */
public class CommaSeparatedValues {
    private final String [] colunas;
    Arquivo arquivo;

    public CommaSeparatedValues() {
        this.colunas = new String[]{"Código", "Descriçao", "Origem", "Abrangência", 
            "Ação de correção", "Data de acontecimento", "Data de registro", 
            "Reincidência", "Código do responsável", "Nome do responsável", 
            "CPF do responsável", "Código do setor", "Nome do setor", 
            "Código do responsável pelo setor", "Nome do responsável pelo setor", 
            "CPF do responsável pelo setor"
        };
    }
    
    private void exportarCsv(){
        arquivo = new Arquivo();
        arquivo.escolherArquivoDestino("csv", "Arquivo de planilha eletrônica", "");
        arquivo.abrirArquivoParaEscrita();
        for(int i=0; i<colunas.length; i++){
            if(i<colunas.length-1){//a última coluna não pode ter vírgula
                arquivo.escreverNoArquivoSaida(colunas[i]+";");
            }
            else{
                arquivo.escreverNoArquivoSaida(colunas[i]+"\n");
            }
        }
        new NaoConformidadeController().listarTodos((t) -> {
            arquivo.escreverNoArquivoSaida(t.toString()+"\n");
        });
        arquivo.fecharArquivo();
    }
    
    public static void main(String[] args) {
        new CommaSeparatedValues().exportarCsv();
    }
}
