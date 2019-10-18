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
import view.naoconformidade.FormNaoConformidade;
import view.responsaveis.FormResponsavel;
import view.setor.FormSetor;
import view.usuario.FormUsuario;

/**
 *
 * @author leona
 */
public class Controller {
    private Component componentePai;
    private DadosController dadosController;
    private UsuarioController usuarioController;
    private ResposavelController resposavelController;
    private Usuario usuario;
    
    private boolean sucessoAcoes;
    
    FormNaoConformidade telaNaoConformidade;
    FormResponsavel telaResponsavel;
    FormSetor telaSetor;
    FormUsuario telaUsuario;
    FormPrincipal telaPrincipal;
    
    
    public Controller(){
        dadosController =  new DadosController();
        resposavelController = new ResposavelController();
        usuarioController = new UsuarioController();
    }

    public DadosController getDadosController() {
        return dadosController;
    }
    
    
    public void setUsuarioController(UsuarioController usuarioController){
        this.usuarioController = usuarioController;
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }

    public ResposavelController getResposavelController() {
        return resposavelController;
    }

    public void setResposavelController(ResposavelController resposavelController) {
        this.resposavelController = resposavelController;
    }
    
    
   
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
                usuarioController.loginMaster();
                //return usuario != null;
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
            case ABRIR_FORMULARIO_NAO_CONFORMIDADES : {
                abreTelaNaoConformidade();
                break;
            }
            case SAIR : {
                System.exit(0);
            }
        }
        return true;
    }
    
    
    private void abreTelaNaoConformidade(){
        telaNaoConformidade = new FormNaoConformidade((Frame) componentePai, true, this);
        telaNaoConformidade.setLocationRelativeTo(null);
        telaNaoConformidade.setVisible(true);
    }
    
    private void abreTelaResponsavel(){
        telaResponsavel = new FormResponsavel((Frame) componentePai, true, this);
        telaResponsavel.setLocationRelativeTo(null);
        telaResponsavel.setVisible(true);
    }
    
    private void abreTelaSetor(){
        telaSetor= new FormSetor((Frame) componentePai, true, this);
        telaSetor.setLocationRelativeTo(null);
        telaSetor.setVisible(true);
    }
    
    private void abreTelaUsuario(){
        telaUsuario = new FormUsuario((Frame) componentePai, true, this);
        telaUsuario.setLocationRelativeTo(null);
        telaUsuario.setVisible(true);
    }
    
    private void abreTelaPrincipal(){
        telaPrincipal= new FormPrincipal(this);
        telaPrincipal.setLocationRelativeTo(null);
        //telaPrincipal.setTitle();
        telaPrincipal.setExtendedState(FormPrincipal.MAXIMIZED_BOTH);
        telaPrincipal.setVisible(true);
    }
    
    public void inicio(){
        new DadosController().verificarBanco();
        executar(Acao.INICIAR_SISTEMA, null);
    }
}
