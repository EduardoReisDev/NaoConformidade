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
public class ResponsavelController {
    private Component componentePai; 
    private Responsavel responsavel;

    public ResponsavelController() {
        
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
    
    public void abrirFormCadastro(){
        FormCadastrar formCadastro = new FormCadastrar((Frame) componentePai, true, this);
        formCadastro.setLocationRelativeTo(null);
        formCadastro.setVisible(true);//
    }
    
    public boolean cadastrar(Responsavel responsavel){
        if(responsavel != null){
            return new ResponsavelDao().criar(responsavel);
        }
        return false;
    }
    
    public boolean excluir(int id){
        if(confirmarExclusao(id)){
            if(new ResponsavelDao().excluir(id)){
                exibirSucessoExclusao();
                return true;
            }
            else{
                exibirErroExclusao();
                return false;
            }
        }
        return false;
    }
    
    /**
     *
     * @param id id do responsável a ser excluído
     * @return true se foi confirmada a exclusão, false se não
     */
    public boolean confirmarExclusao(int id){
        responsavel = listarPorId(id);
        return Mensagens.confirmar(componentePai,
                mensagemExcluirResponsavel(responsavel),
                "Excluir responsável",
                Resources.QUESTAO);
    }
    
    /**
     *Este método mostra uma mensagem de sucesso ao excluir
     */
    public void exibirSucessoExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemSucessoExcluirResponsavel(responsavel),
                "Sucesso ao excluir",
                Resources.SUCESSO);
    }
    
    /**
     *Este método mostra uma mensagem de erro ao excluir
     */
    public void exibirErroExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemErroExcluirResponsavel(responsavel), 
                "Erro ao excluir",
                Resources.ERRO);
    }
    
    
    public void abrirFormularioEditar(int id){
        FormEditar formEdita = new FormEditar((Frame) componentePai, true, this);
        formEdita.preencherCampos(new ResponsavelDao().listarPorId(id));
        formEdita.setLocationRelativeTo(null);
        formEdita.setVisible(true);
    }
    
    public boolean editar(Responsavel responsavel){
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
    
    public boolean validarCpf(String cpf){
        return Resources.validarCpf(cpf);
    }
    
    public boolean validarNome(String nome){
        return nome.length()>2;
    }
    
    public boolean verificarSeExisteCpf(String cpf){
        return new ResponsavelDao().listarPorCpf(cpf) != null;
    }
}