/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import resources.Dados;
import resources.Propriedade;
import java.awt.Component;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Usuario;
import view.DialogoConfirmaSair;
import view.FormPrincipal;
import view.naoconformidade.FormNaoConformidade;
import view.relatorio.FormRelatorio;
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
    private final Dados dadosController;
    private final UsuarioController usuarioController;
    private final ResponsavelController responsavelController;
    private final SetorController setorController;
    private final Propriedade propriedadesController;
    private final NaoConformidadeController naoConformidadeController;
    private final RelatorioController relatorioController;
    private Usuario usuario;
    
    private FormNaoConformidade telaNaoConformidade;
    private FormResponsavel telaResponsavel;
    private FormSetor telaSetor;
    private FormUsuario telaUsuario;
    private FormPrincipal telaPrincipal;
    private FormRelatorio telaRelatorio;
    private DialogoConfirmaSair dialogoConfirmaSair;
            
    public Controller(){
        this.relatorioController = new RelatorioController();
        dadosController =  new Dados();
        responsavelController = new ResponsavelController();
        naoConformidadeController = new NaoConformidadeController();
        usuarioController = new UsuarioController();
        propriedadesController = new Propriedade(System.getProperty("user.dir")+"\\configuracoes.ini");
        setorController = new SetorController();
    }

    public RelatorioController getRelatorioController() {
        return relatorioController;
    }

    public Usuario getUsuario() {
        return usuario;
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
    
    public Dados getDadosController() {
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
        naoConformidadeController.setComponentePai(componentePai);
        usuarioController.setComponentePai(componentePai);
        setorController.setComponentePai(componentePai);
        responsavelController.setComponentePai(componentePai);
    }

    public void abreTelaNaoConformidade(){
        telaNaoConformidade = new FormNaoConformidade((Frame) componentePai, true, this);
        telaNaoConformidade.setLocationRelativeTo(componentePai);
        telaNaoConformidade.setVisible(true);
    }
    
    public void abreTelaResponsavel(){
        telaResponsavel = new FormResponsavel((Frame) componentePai, true, this);
        telaResponsavel.setLocationRelativeTo(componentePai);
        telaResponsavel.setVisible(true);
    }
    
    public void abreTelaSetor(){
        telaSetor= new FormSetor((Frame) componentePai, true, this);
        telaSetor.setLocationRelativeTo(componentePai);
        telaSetor.setVisible(true);
    }
    
    public void abreTelaUsuario(){
        usuarioController.setComponentePai(componentePai);
        telaUsuario = new FormUsuario((Frame) componentePai, true, this);
        telaUsuario.setLocationRelativeTo(componentePai);
        telaUsuario.setVisible(true);
    }
    
    private void abreTelaPrincipal(){
        telaPrincipal= new FormPrincipal(this);
        telaPrincipal.setLocationRelativeTo(componentePai);
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
            dialogoConfirmaSair.setLocationRelativeTo(componentePai);
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
    
    
    public void abrirTelaRelatorio(){
        telaRelatorio = new FormRelatorio((Frame) componentePai, true, this);
        telaRelatorio.setLocationRelativeTo(componentePai);
        telaRelatorio.setVisible(true);
    }
    
    public void inicio(){
        //usuario = usuarioController.login();
        //if(usuario!=null){
            abreTelaPrincipal();
        //}
        //abreTelaNaoConformidade();
        //abrirTelaRelatorio();
    }
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
       // new Splash();
        new Controller().inicio();
    }

    
}
