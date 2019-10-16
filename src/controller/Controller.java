/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.NaoConformidadeDao;
import model.NaoConformidade;
import dao.SetorDao;
import model.Setor;
import dao.UsuarioDao;
import java.awt.Component;
import java.awt.Frame;
import model.Usuario;
import java.util.Date;
import javax.swing.JFrame;
import view.Principal;
import view.gerenciar.FormUsuario;
import view.naoconformidade.CadastroNaoConformidade;
import view.naoconformidade.EditarNaoConformidade;
import view.naoconformidade.FormNaoConformidade;
import view.responsaveis.CadastrarResponsavel;
import view.responsaveis.EditarResponsavel;
import view.responsaveis.FormResponsavel;
import view.setor.CadastroSetor;
import view.setor.EditarSetor;
import view.setor.FormSetor;
import view.usuario.FormCriar;
import view.usuario.FormEditar;
import view.usuario.FormUsuarios;

/**
 *
 * @author leona
 */
public class Controller {
    UsuarioController usuarioController = new UsuarioController();
    FormUsuario telaGerenciarUsuarios;
    
    FormNaoConformidade telaNaoConformidade;
    CadastroNaoConformidade telaCadastroNaoConformidade;
    EditarNaoConformidade telaEditarNaoConformidade;
    
    FormResponsavel telaResponsavel;
    CadastrarResponsavel telaCadastrarResponsavel;
    EditarResponsavel telaEditarResponsavel;
    
    FormSetor telaSetor;
    CadastroSetor telaCadastrarSetor;
    EditarSetor telaEditarSetor;
    
    FormUsuarios telaUsuario;
    FormCriar telaCadastrarUsuario;
    FormEditar telaEditarUsuario;
    
    
    Principal telaPrincipal;

    Component componentePai;

    public void setTelaPrincipal(Component telaPrincipal) {
        this.componentePai = telaPrincipal;
    }
    
    Usuario usuario;

    //<editor-fold defaultstate="collapsed" desc="comment">

    
    public void insereUsuarioDao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Leonardo");
        usuario.setCpf("06543651130");
        usuario.setUsuario("leo");
        usuario.setSenha("123");
        usuario.setMaster(true);
        System.out.println(new UsuarioDao().criar(usuario));
    }
    
    public void listaUsuarios(){
        new UsuarioDao().lerTodos(usuario->{
            System.out.println(usuario.getId());
            System.out.println(usuario.getNome());
            System.out.println(usuario.getUsuario());
            System.out.println(usuario.getSenha());
        });
    }
    
    public void excluirUsuarioDao(){
        new UsuarioDao().excluir(16);
    }
    
    public void editarUsuarioDao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Léo");
        usuario.setUsuario("leo");
        usuario.setSenha("123");
        usuario.setId(2);
        
        System.out.println(new UsuarioDao().editar(usuario));
    }
    
    public void insereSetor(){
        Setor setor = new Setor();
        setor.setCodigo("0001145");
        setor.setNome("Casa das Galinhas");
        setor.setResponsavel("Fulano das Couve");
        
        System.out.println(new SetorDao().criar(setor));
    }
    
    public void listarSetores(){
        new SetorDao().lerTodos(setor->{
            System.out.println(setor.getId());
            System.out.println(setor.getNome());
            System.out.println(setor.getCodigo());
            System.out.println(setor.getResponsavel());
        });
    }
    
    public void editarSetor(){
        Setor setor = new Setor();
        setor.setCodigo("0001145");
        setor.setNome("Casa dos Galos");
        setor.setResponsavel("Fulano das Couve");
        setor.setId(1);
        System.out.println(new SetorDao().editar(setor));
    }
    
    public void excluirSetor(){
        new SetorDao().excluir(1);
    }
    
    public void salvarNaoConformidade(){
        NaoConformidade naoConformidade = new NaoConformidade();
        naoConformidade.setAbrangencia("xxxx");
        naoConformidade.setAcaoCorrecao("teste");
        naoConformidade.setDataAcontecimento(new Date(2020, 9, 24));
        naoConformidade.setDataRegistro(new Date(19, 9, 24));
        naoConformidade.setDescricao("uma gaiola quebrada");
        naoConformidade.setImagem("C:\001.png");
        naoConformidade.setOrigem("Baixa da égua");
        naoConformidade.setReincidencia(true);
        naoConformidade.setIdSetor(2);
        naoConformidade.setIdResponsavel(1);
        System.out.println(new NaoConformidadeDao().criar(naoConformidade));
    }
    
    public void editarNaoConformidade(){
        NaoConformidade naoConformidade = new NaoConformidade();
        naoConformidade.setAbrangencia("xxxx");
        naoConformidade.setAcaoCorrecao("teste");
        naoConformidade.setDataAcontecimento(new Date(2020, 9, 24));
        naoConformidade.setDataRegistro(new Date(19, 9, 24));
        naoConformidade.setDescricao("uma gaiola quebrada");
        naoConformidade.setIdSetor(2);
        naoConformidade.setImagem("C:\001.png");
        naoConformidade.setOrigem("Baixa da égua");
        naoConformidade.setReincidencia(false);
        naoConformidade.setIdResponsavel(1);
        naoConformidade.setId(1);
        System.out.println(new NaoConformidadeDao().editar(naoConformidade));
    }
    
    public void listarNaoConformidades(){
        new NaoConformidadeDao().lerTodos(naoConformidade->{
            System.out.println(naoConformidade.getId());
            System.out.println(naoConformidade.getIdResponsavel());
        });
    }
//</editor-fold>
    
    
    //TESTE COM ETAPAS....
    
    private boolean sucessoAcoes;
    
    public void executar(Acao acao){
        sucessoAcoes = true;
        RegraNegocio.obterEtapas(acao).forEach(etapa->{
            if(sucessoAcoes){
                separarEtapas(etapa);
            }
        });
        if(!sucessoAcoes){
            RegraNegocio.obterEtapasErros(acao).forEach(etapa->{
                separarEtapas(etapa);
            });
        }
    }
    
    public void separarEtapas(Etapas etapa){
        switch (etapa){
            case LOGIN : {
                if(!executarEtapa(etapa)){
                    sucessoAcoes = false;
                }
                break;
            }
            case LOGIN_MASTER : {
                if(!executarEtapa(etapa)){
                    sucessoAcoes = false;
                }
                break;
            }
            case ABRIR_FORMULARIO_PRINCIPAL : {
                executarEtapa(etapa);
                break;
            }
            case ABRIR_FORMULARIO_USUARIOS : {
                executarEtapa(etapa);
                break;
            }
            case SAIR : {
                executarEtapa(etapa);
                break;
            }
        }
    }
    
    public boolean executarEtapa(Etapas etapa){
        switch (etapa){
            case LOGIN : {
                usuario = usuarioController.login();
                return usuario!= null;
            }
            case LOGIN_MASTER : {
                usuario = usuarioController.loginMaster();
                return usuario != null;
            }
            case ABRIR_FORMULARIO_PRINCIPAL : {
                abreTelaPrincipal();
                break;
            }
            case ABRIR_FORMULARIO_USUARIOS : {
                abreTelaGerenciarUsuarios();
                break;
            }
            case ABRIR_FORMULARIO_RESPONSAVEL : {
                abreTelaResponsaveis();
                break;
            }
            case SAIR : {
                System.exit(0);
            }
        }
        return false;
    }
    
    
    public void abreTelaNaoConformidade(){
        telaNaoConformidade = new FormNaoConformidade((Frame) componentePai, true);
        telaNaoConformidade.setLocationRelativeTo(null);
        telaNaoConformidade.setVisible(true);
    }
    
    public void abreTelaCadastroNaoConformidade(){
        telaCadastroNaoConformidade.setVisible(true);
    }
    
    public void abreTelaEditarNaoConformidade(){
        telaEditarNaoConformidade.setVisible(true);
    }
    
    public void abreTelaResponsaveis(){
        telaResponsavel = new  FormResponsavel((Frame) componentePai, true);
        telaResponsavel.setLocationRelativeTo(null);
        telaResponsavel.setVisible(true);
    }
    
    public void abreTelaCadastrarResponsavel(){
        telaCadastrarResponsavel.setVisible(true);
    }
    
    public void abreTelaEditarResponsavel(){
        telaEditarResponsavel.setVisible(true);
    }
    
    public void abreTelaSetor(){
        telaSetor.setVisible(true);
    }
    
    public void abreTelaCadastrarSetor(){
        telaCadastrarSetor.setVisible(true);
    }
    
    public void abreTelaEditarSetor(){
        telaEditarSetor.setVisible(true);
    }
    
    public void abreTelaUsuario(){
        telaUsuario.setVisible(true);
    }
    
    public void abreTelaCadastrarUsuario(){
        telaCadastrarUsuario.setVisible(true);
    }
    
    public void abreTelaEditarUsuario(){
        telaEditarUsuario.setVisible(true);
    }
    
    public void inicio(){
        executar(Acao.INICIAR_SISTEMA);
    }
    
    public void abreTelaGerenciarUsuarios(){
        telaGerenciarUsuarios =  new FormUsuario((Frame) componentePai, true);
        telaGerenciarUsuarios.setLocationRelativeTo(null);
        telaGerenciarUsuarios.inicializarTabela();
        telaGerenciarUsuarios.setVisible(true);
    }
    
    private void abreTelaPrincipal(){
        telaPrincipal= new Principal();
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setExtendedState(Principal.MAXIMIZED_BOTH);
        telaPrincipal.setVisible(true);
    }
    
    public void login(){
        abreTelaPrincipal();
        usuarioController.setFormPai((Frame) componentePai);
        Usuario usuario = usuarioController.login();
        if(usuario!=null){
            //adiciona todo o conteúdo da tela principal
        }
        else{
            System.exit(0);
        }
    }
}
