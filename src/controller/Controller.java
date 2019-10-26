/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.awt.Component;
import java.awt.Frame;
import model.Usuario;
import view.DialogoConfirmaSair;
import view.FormPrincipal;
import view.naoconformidade.FormNaoConformidade;
import view.responsaveis.FormResponsavel;
import view.setor.FormSetor;
import view.splash.Splash;
import view.usuario.FormUsuario;

/**
 *
 * @author leona
 */
public class Controller {
    private Component componentePai;
    private final DadosController dadosController;
    private final UsuarioController usuarioController;
    private final ResponsavelController responsavelController;
    private final SetorController setorController;
    private final PropriedadesController propriedadesController;
    private final NaoConformidadeController naoConformidadeController;
    private Usuario usuario;
    
    private FormNaoConformidade telaNaoConformidade;
    private FormResponsavel telaResponsavel;
    private FormSetor telaSetor;
    private FormUsuario telaUsuario;
    private FormPrincipal telaPrincipal;
    private DialogoConfirmaSair dialogoConfirmaSair;
            
    public Controller(){
        dadosController =  new DadosController();
        responsavelController = new ResponsavelController();
        naoConformidadeController = new NaoConformidadeController();
        usuarioController = new UsuarioController();
        propriedadesController = new PropriedadesController(System.getProperty("user.dir")+"\\configuracoes.ini");
        setorController = new SetorController();
    }

    public NaoConformidadeController getNaoConformidadeController() {
        return naoConformidadeController;
    }

    public ResponsavelController getResponsavelController() {
        return responsavelController;
    }

    public SetorController getSetorController() {
        return setorController;
    }
    
    public DadosController getDadosController() {
        return dadosController;
    }
    
    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void verificarBancoDeDados(){
        dadosController.verificarBanco();
    }
    
    public void verificaArquivoDeConfiguracoes(){
        propriedadesController.ler("Boas vindas");
    }

    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }

    public void abreTelaNaoConformidade(){
        telaNaoConformidade = new FormNaoConformidade((Frame) componentePai, true, this);
        telaNaoConformidade.setLocationRelativeTo(null);
        telaNaoConformidade.setVisible(true);
    }
    
    public void abreTelaResponsavel(){
        telaResponsavel = new FormResponsavel((Frame) componentePai, true, this);
        telaResponsavel.setLocationRelativeTo(null);
        telaResponsavel.setVisible(true);
    }
    
    public void abreTelaSetor(){
        telaSetor= new FormSetor((Frame) componentePai, true, this);
        telaSetor.setLocationRelativeTo(null);
        telaSetor.setVisible(true);
    }
    
    public void abreTelaUsuario(){
        usuarioController.setComponentePai(componentePai);
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
    
    public void setConfirmarFechar(boolean opcao){
        propriedadesController.escrever("confirmar.fechar", String.valueOf(opcao));
    }
    
    public void fechar(){
        if(Boolean.parseBoolean(propriedadesController.ler("confirmar.fechar"))){
            dialogoConfirmaSair = new DialogoConfirmaSair((Frame) componentePai, true);
            dialogoConfirmaSair.setLocationRelativeTo(null);
            dialogoConfirmaSair.setVisible(true);
            if(dialogoConfirmaSair.isNaoMostrarNovamente()){
                propriedadesController.escrever("confirmar.fechar", "false");
            }
            if(dialogoConfirmaSair.isSair()){
                System.exit(0);
            }
        }
        else{
            System.exit(0);
        }
    }
    
    public void inicio(){
        //new Splash();
        //abreTelaPrincipal();
        //abreTelaNaoConformidade();
       // 
       naoConformidadeController.mostrarNaoConformidade(19);
        //System.exit(0);
    }
}
