/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ResponsavelDao;
import java.awt.Component;
import java.awt.Frame;
import java.util.function.Consumer;
import model.Responsavel;
import view.Mensagens;
import view.responsaveis.FormCadastrar;
import view.responsaveis.FormEditar;

/**
 *
 * @author leona
 */
public class ResposavelController {
    private Component componentePai; 
    private Responsavel responsavel;
    private Resources rsc;

    public ResposavelController() {
        this.rsc = new Resources();
    }
    
    
    public void listarTodos(Consumer <?super Responsavel> responsavel){
        new ResponsavelDao().listarTodos(responsavel::accept);
    }
    public void listarPorNome(Consumer <?super Responsavel> responsavel, String nome){
        new ResponsavelDao().listarPorNome(responsavel::accept, nome);
    }
    public Responsavel listarPorId(int id){
        return new ResponsavelDao().listarPorId(id);
    }
    
    public int getLastId(){
        return new ResponsavelDao().getLastId();
    }
    
    private Responsavel abrirFormCadastro(){
        FormCadastrar formCadastro = new FormCadastrar((Frame) componentePai, true);
        formCadastro.setLocationRelativeTo(null);
        formCadastro.txtCodigo.setText(String.format("%010d", getLastId()+1));
        formCadastro.setVisible(true);//
        if(formCadastro.isValido()){
            return new Responsavel(
                    0, 
                    formCadastro.txtCpf.getText(), 
                    formCadastro.txtNome.getText()
            );
        }
        return null;
    }
    
    public boolean cadastrar(){
        responsavel = abrirFormCadastro();
        if(responsavel != null){
            return new ResponsavelDao().criar(responsavel);
        }
        return false;
    }
    
    public boolean excluir(int id){
        if(confirmarExclusao(id)){
            if(new ResponsavelDao().excluir(id)){
                mensagemSucessoExcluirResponsavel(responsavel);
                return true;
            }
            else{
                mensagemErroExcluirResponsavel(responsavel);
                return false;
            }
        }
        return false;
    }

    public boolean confirmarExclusao(int id){
        responsavel = listarPorId(id);
        return Mensagens.confirmar(componentePai,
                mensagemExcluirResponsavel(responsavel),
                rsc.TITULO_EXCLUIR_USUARIO,
                rsc.QUESTAO);
    }
    
    /**
     *Este método mostra uma mensagem de sucesso ao excluir
     */
    public void exibirSucessoExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemSucessoExcluirResponsavel(responsavel),
                rsc.TITULO_SUCESSO_EXCLUSAO_USUARIO,
                rsc.SUCESSO);
    }
    
    /**
     *Este método mostra uma mensagem de erro ao excluir
     */
    public void exibirErroExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemErroExcluirResponsavel(responsavel), 
                rsc.TITULO_ERRO_EXCLUSAO_USUARIO,
                rsc.ERRO);
    }
    
    
    private Responsavel abrirFormularioEditar(Responsavel responsavel){
        FormEditar formEdita = new FormEditar((Frame) componentePai, true);
        formEdita.preencherCampos(responsavel);
        formEdita.setLocationRelativeTo(null);
        formEdita.setVisible(true);
        if(formEdita.validarCampos()){
            return new Responsavel(
                    responsavel.getId(),
                    responsavel.getCpf(),
                    formEdita.txtNome.getText()
            );
        }
        return null;
    }
    
    public boolean editar(int id) {
        responsavel = new ResponsavelDao().listarPorId(id);
        responsavel = abrirFormularioEditar(responsavel);
        if(responsavel != null){
            return new ResponsavelDao().editar(responsavel);
        }
        return false;
    }

    private String mensagemExcluirResponsavel(Responsavel responsavel) {
        return "Tem certeza que deseja excluir o responsável " + responsavel.getNome() + "?";
    }

    private String mensagemSucessoExcluirResponsavel(Responsavel responsavel) {
        return "Sucesso ao excluir o responsavel " + responsavel.getNome() + "!";
    }

    private String mensagemErroExcluirResponsavel(Responsavel responsavel) {
        return "Erro ao excluir o responsável " + responsavel.getNome() + "!";
    }
    
}