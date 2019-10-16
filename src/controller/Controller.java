/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.awt.Component;
import java.awt.Frame;
import model.Usuario;
import view.FormPrincipal;
import view.naoconformidade.CadastroNaoConformidade;
import view.naoconformidade.EditarNaoConformidade;
import view.naoconformidade.FormNaoConformidade;
import view.responsaveis.CadastrarResponsavel;
import view.responsaveis.EditarResponsavel;
import view.responsaveis.FormResponsavel;
import view.setor.CadastroSetor;
import view.setor.EditarSetor;
import view.setor.FormSetor;
import view.usuario.FormUsuario;

/**
 *
 * @author leona
 */
public class Controller {
    UsuarioController usuarioController;
    
    FormNaoConformidade telaNaoConformidade;
    CadastroNaoConformidade telaCadastroNaoConformidade;
    EditarNaoConformidade telaEditarNaoConformidade;
    
    FormResponsavel telaResponsavel;
    CadastrarResponsavel telaCadastrarResponsavel;
    EditarResponsavel telaEditarResponsavel;
    
    FormSetor telaSetor;
    CadastroSetor telaCadastrarSetor;
    EditarSetor telaEditarSetor;
    
    FormUsuario telaUsuario;
    FormPrincipal telaPrincipal;

    Component componentePai;

    public void setUsuarioController(UsuarioController usuarioController){
        this.usuarioController = usuarioController;
    }

    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }
    
    Usuario usuario;
    //TESTE COM ETAPAS....
    
    private boolean sucessoAcoes;
    
    public void executar(Acao acao, Object[] parametros){
        sucessoAcoes = true;
        RegraNegocio.obterEtapas(acao).forEach(etapa->{
            if(sucessoAcoes){
                sucessoAcoes = executarEtapa(etapa, parametros);
            }
        });
        if(!sucessoAcoes){
            RegraNegocio.obterEtapasSeErro(acao).forEach(etapa->{
                executarEtapa(etapa, parametros);
            });
        }
    }
    
    private boolean executarEtapa(Etapas etapa, Object[] parametros){
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
                abreTelaUsuario();
                break;
            }
            case ABRIR_FORMULARIO_CADASTRO_USUARIOS : {
                return usuarioController.cadastrar();
            }
            case ABRIR_FORMULARIO_EDITA_USUARIOS : {
                return usuarioController.editar((int)parametros[0]);
            }
            case CONFIRMAR_EXCLUSAO_USUARIO : {
                return usuarioController.confirmarExclusao((int) parametros[0]);
            }
            case EXCLUIR_USUARIO : {
                return usuarioController.excluir((int) parametros[0]);
            }
            case MOSTRAR_MENSAGEM_SUCESSO_EXCLIUR : {
                usuarioController.exibirSucessoExclusao();
                break;
            }
            case MOSTRAR_MENSAGEM_ERRO_EXCLIUR : {
                usuarioController.exibirErroExclusao();
                break;
            }
            case ABRIR_FORMULARIO_RESPONSAVEL : {
                abreTelaResponsavel();
                break;
            }
            case ABRIR_FORMULARIO_SETORES : {
                abreTelaSetor();
                break;
            }
            
            case SAIR : {
                System.exit(0);
            }
        }
        return true;
    }
    
    
    public void abreTelaNaoConformidade(){
        telaNaoConformidade = new FormNaoConformidade((Frame) componentePai, true);
        telaNaoConformidade.setLocationRelativeTo(null);
        telaNaoConformidade.setVisible(true);
    }
    
    public void abreTelaCadastroNaoConformidade(){
        telaCadastroNaoConformidade = new CadastroNaoConformidade((Frame) componentePai, true);
        telaCadastroNaoConformidade.setLocationRelativeTo(null);
        telaCadastroNaoConformidade.setVisible(true);
    }
    
    public void abreTelaEditarNaoConformidade(){
        telaEditarNaoConformidade = new EditarNaoConformidade((Frame) componentePai, true);
        telaEditarNaoConformidade.setLocationRelativeTo(null);
        telaEditarNaoConformidade.setVisible(true);
    }
    
    public void abreTelaResponsavel(){
        telaResponsavel = new FormResponsavel((Frame) componentePai, true);
        telaResponsavel.setLocationRelativeTo(null);
        telaResponsavel.setVisible(true);
    }
    
    public void abreTelaCadastrarResponsavel(){
        telaCadastrarResponsavel = new CadastrarResponsavel((Frame) componentePai, true);
        telaCadastrarResponsavel.setLocationRelativeTo(null);
        telaCadastrarResponsavel.setVisible(true);
    }
    
    public void abreTelaEditarResponsavel(){
        telaEditarResponsavel = new EditarResponsavel((Frame) componentePai, true);
        telaEditarResponsavel.setLocationRelativeTo(null);
        telaEditarResponsavel.setVisible(true);
    }
    
    public void abreTelaSetor(){
        telaSetor= new FormSetor((Frame) componentePai, true);
        telaSetor.setLocationRelativeTo(null);
        telaSetor.setVisible(true);
    }
    
    public void abreTelaCadastrarSetor(){
        telaCadastrarSetor = new CadastroSetor((Frame) componentePai, true);
        telaCadastrarSetor.setLocationRelativeTo(null);
        telaCadastrarSetor.setVisible(true);
    }
    
    public void abreTelaEditarSetor(){
        telaEditarSetor = new EditarSetor((Frame) componentePai, true);
        telaEditarSetor.setLocationRelativeTo(null);
        telaEditarSetor.setVisible(true);
    }
    
    private void abreTelaUsuario(){
        telaUsuario = new FormUsuario((Frame) componentePai, true);
        telaUsuario.setLocationRelativeTo(null);
        telaUsuario.inicializarTabela();
        telaUsuario.listar();
        telaUsuario.setVisible(true);
    }
    
    public void inicio(){
        setUsuarioController(new UsuarioController());
        executar(Acao.INICIAR_SISTEMA, null);
    }
    
    private void abreTelaPrincipal(){
        telaPrincipal= new FormPrincipal();
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setExtendedState(FormPrincipal.MAXIMIZED_BOTH);
        telaPrincipal.setVisible(true);
    }
}
