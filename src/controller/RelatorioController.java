/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.NaoConformidade;
import model.Usuario;
import resources.Arquivo;
import resources.Relatorio;
import resources.Resources;

/**
 *
 * @author leona
 */
public class RelatorioController {
    private final Relatorio relatorio;
    private boolean arquivoOk;

    public RelatorioController() {
        this.relatorio = new Relatorio();
    }
    public boolean criarRelatorio(Usuario emitente){
        String caminhoDestino;
        Arquivo arq = new Arquivo();
        arq.escolherArquivoDestino("pdf", "Arquivos de documento", String.format("Relatório exportado em %s.pdf", Resources.getDataEHora()));
        caminhoDestino = arq.obterCaminhoSaida();
        arquivoOk = caminhoDestino != null;
        if(arquivoOk){
            relatorio.criarDocumento(caminhoDestino);
            relatorio.definirMargens(20, 20, 20, 20);
            relatorio.criarPaginaInicial(emitente);
        }
        return arquivoOk;
    }
    
    public void colocarNumeroDePaginas(){
        if(arquivoOk){
            relatorio.colocarNumeroDePaginas();
        }
    }
    
    public void listarNaoConformidade(NaoConformidade naoConformidade){
        if(arquivoOk){
            relatorio.listarNaoConformidade(naoConformidade);
        }
    }
    
    public void fecharDocumento(){
        if(arquivoOk){
            relatorio.fecharDocumento();
        }
    }
    
}
