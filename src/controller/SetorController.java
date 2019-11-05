/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import resources.Resources;
import dao.ResponsavelDao;
import dao.SetorDao;
import java.awt.Component;
import java.awt.Frame;
import java.util.function.Consumer;
import model.Responsavel;
import model.Setor;
import view.Mensagens;
import view.setor.FormCadastrarSetor;
import view.setor.FormEditarSetor;

/**
 *
 * @author leona
 */

public class SetorController {
    private Component componentePai;
    private Setor setor;
    private FormCadastrarSetor formCadastro;
    private FormEditarSetor formEditar;
    private final SetorDao setorDao;
    private final ResponsavelDao responsavelDao;
    
    public SetorController() {
        this.setorDao = new SetorDao();
        this.responsavelDao = new ResponsavelDao();
    }
    

    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }
    
    public int getLastId(){
        return setorDao.getLastId();
    }
    
    /**
    *Este método é responsável por acessar a tela FormCadastrar de Setores
    */
    
    public void cadastrar(){
        formCadastro = new FormCadastrarSetor((Frame) componentePai, true, this);
        formCadastro.setLocationRelativeTo(componentePai);
        formCadastro.setVisible(true);
    }
    
    /**
    *Este método faz a listagem a partir do id do setor
    * @param id id do usuário a ser consultado;
    * @return 
    */
    public Setor listarPorId(int id){
        return setorDao.listarPorId(id);
    }
    
    /**
    *Este método é responsável por listar todos os setores existentes no banco de dados
    * @param resultado resultado da listagem
    */
    public void listarTodos(Consumer<? super Setor> resultado){
        setorDao.listarTodos(resultado::accept);
    }
    
    public void listarPorNome(Consumer<? super Setor> resultado, String nome){
        setorDao.listarPorNome(resultado::accept, nome);
    }
    
    /**
    *Este método é responsável por listar todos os setores existentes no banco de dados
    * @param resultado resultado da listagem
    */
    public void listarResponsaveis(Consumer<? super Responsavel> resultado){
        responsavelDao.listarTodos(resultado::accept);
    }
    
    /**
    *Este método é responsável por excluir o setor do banco de dados
    * @param id id do setor a ser excluído
    * @return true se foi excluído com sucesso ou false, se não
    */
    public boolean excluir(int id){
        if(confirmarExclusao(id)){
            if(setorDao.excluir(id)){
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
     * @param id id do setor a ser excluído
     * @return true se foi confirmada a exclusão, false se não
     */
    
    public boolean confirmarExclusao(int id){
        setor = listarPorId(id);
        return Mensagens.confirmar(componentePai,
                mensagemExcluirSetor(setor),
                "Excluir Setor",
                Resources.QUESTAO);
    }
    
     /**
     *Este método mostra uma mensagem de sucesso ao excluir
     */
    
    public void exibirSucessoExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemSucessoExcluirSetor(setor),
                "Sucesso ao excluir",
                Resources.SUCESSO);
    }
    
     /**
     *Este método mostra uma mensagem de erro ao excluir
     */
    public void exibirErroExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemErroExcluirSetor(setor), 
                "Erro ao excluir",
                Resources.ERRO);
    }
    
    public String mensagemExcluirSetor(Setor setor){
        return "Tem certeza que deseja excluir o setor " + setor.getNome() + "?";
    }
    
    public String mensagemSucessoExcluirSetor(Setor setor){
        return "Sucesso ao excluir o setor " + setor.getNome() + "!";
    }
    
    public String mensagemErroExcluirSetor(Setor setor){
        return "Erro ao excluir o setor " + setor.getNome() + "!";
    }
    
    public void abrirFormEditar(int id) {
        formEditar = new FormEditarSetor((Frame) componentePai, true, this);
        formEditar.setLocationRelativeTo(componentePai);
        formEditar.preencherCampos(new SetorDao().listarPorId(id));
        formEditar.setVisible(true);
    }
    
    public boolean editar(Setor setor){
        if(setor!=null){
           return setorDao.editar(setor);//salva no banco de dados
        } 
        return false;
    }
    
  public boolean adicionar(Setor setor){
        if(setor!=null){//se não retornar nulo, insere no banco
            return setorDao.criar(setor);//salva no banco de dados
        }
        return false;
    }
}