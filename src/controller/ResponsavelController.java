/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import resources.Resources;
import dao.ResponsavelDao;
import java.awt.Component;
import java.awt.Frame;
import java.util.function.Consumer;
import model.Responsavel;
import view.Mensagens;
import view.responsaveis.FormCadastrarResponsavel;
import view.responsaveis.FormEditarResponsavel;

/**
 *
 * @author leona
 */
public class ResponsavelController {
    private Component componentePai; 
    private Responsavel responsavel;
    private final ResponsavelDao responsavelDao;
    private FormCadastrarResponsavel formCadastro;
    private FormEditarResponsavel formEditar;
    
    public ResponsavelController() {
        this.responsavelDao = new ResponsavelDao();
    }
    
    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }

  
    public void listarTodos(Consumer <?super Responsavel> responsavel){
        responsavelDao.listarTodos(responsavel::accept);
    }
    
    public void listarPorNome(Consumer <?super Responsavel> responsavel, String nome){
        responsavelDao.listarPorNome(responsavel::accept, nome);
    }
    public Responsavel listarPorId(int id){
        return responsavelDao.listarPorId(id);
    }
    
    public int getLastId(){
        return responsavelDao.getLastId();
    }
    
    public void abrirFormCadastro(){
        formCadastro = new FormCadastrarResponsavel((Frame) componentePai, true, this);
        formCadastro.setLocationRelativeTo(componentePai);
        formCadastro.setVisible(true);//
    }
    
    public boolean cadastrar(Responsavel responsavel){
        if(responsavel != null){
            return responsavelDao.criar(responsavel);
        }
        return false;
    }
    
    public boolean excluir(int id){
        if(confirmarExclusao(id)){
            if(responsavelDao.excluir(id)){
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
        formEditar = new FormEditarResponsavel((Frame) componentePai, true, this);
        formEditar.preencherCampos(new ResponsavelDao().listarPorId(id));
        formEditar.setLocationRelativeTo(componentePai);
        formEditar.setVisible(true);
    }
    
    public boolean editar(Responsavel responsavel){
        if(responsavel != null){
            return responsavelDao.editar(responsavel);
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
        return responsavelDao.listarPorCpf(cpf) != null;
    }
}