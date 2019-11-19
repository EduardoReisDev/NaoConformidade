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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Usuario;
import resources.Resources;
import view.FormPrincipal;
import view.Mensagens;
import view.naoconformidade.FormNaoConformidade;
import view.relatorio.FormRelatorio;
import view.responsaveis.FormResponsavel;
import view.setor.FormSetor;
import view.Splash;
import view.usuario.FormPerfil;

/**
 *
 * @author leona
 */
public class Controller {
    private Component componentePai;
    private final Dados dados;
    private final Propriedade propriedade;
    private final UsuarioController usuarioController;
    private final ResponsavelController responsavelController;
    private final SetorController setorController;
    private final NaoConformidadeController naoConformidadeController;
    private Usuario usuario;
    private FormNaoConformidade telaNaoConformidade;
    private FormResponsavel telaResponsavel;
    private FormSetor telaSetor;
    private FormPerfil telaUsuario;
    private FormPrincipal telaPrincipal;
    private FormRelatorio telaRelatorio;
            
    public Controller(){
        dados =  new Dados();
        responsavelController = new ResponsavelController();
        naoConformidadeController = new NaoConformidadeController();
        usuarioController = new UsuarioController();
        propriedade = new Propriedade(System.getProperty("user.dir")+"\\configuracoes.ini");
        setorController = new SetorController();
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
        return dados;
    }
    
    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void verificarBancoDeDados(){
        dados.verificarBanco();
    }
    
    public void verificaArquivoDeConfiguracoes(){
        propriedade.ler("Boas vindas");
    }

    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
        naoConformidadeController.setComponentePai(componentePai);
        usuarioController.setComponentePai(componentePai);
        setorController.setComponentePai(componentePai);
        responsavelController.setComponentePai(componentePai);
    }
    
    public boolean vericarExisteSetor(){
         if(setorController.getLastId()>0){
            return true;
        }
        else{
            Mensagens.mensagem(componentePai, "É necessário que pelo um um setor esteja cadastrado!", "Mensagem", 1);
            setorController.cadastrar();
            return false;  
        } 
    }
    
    public boolean verificaExisteResponsavel(){
        if(responsavelController.getLastId()>0){
            return true;
        }
        else{
            Mensagens.mensagem(componentePai, "É necessário que pelo menos um responsável esteja cadastrado!", "Mensagem", 1);
            responsavelController.abrirFormCadastro();
            return false;  
        }  
    }

    public void abreTelaNaoConformidade(){
        if(verificaExisteResponsavel() && vericarExisteSetor()){
            telaNaoConformidade = new FormNaoConformidade((Frame) componentePai, true, this);
            telaNaoConformidade.setLocationRelativeTo(componentePai);
            telaNaoConformidade.setVisible(true);
        }
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
        telaUsuario = new FormPerfil((Frame) componentePai, true, this);
        telaUsuario.setLocationRelativeTo(componentePai);
        telaUsuario.setVisible(true);
    }
    
    private void abreTelaPrincipal(){
        telaPrincipal= new FormPrincipal(this);
        telaPrincipal.setLocationRelativeTo(componentePai);
        telaPrincipal.setVisible(true);
    }
    
    public void setConfirmarFechar(boolean opcao){
        propriedade.escrever("confirmar.fechar", String.valueOf(opcao));
    }
    
    public void fechar(){
        if(Boolean.parseBoolean(propriedade.ler("confirmar.fechar"))){
            switch(Mensagens.mensagemComOpcoes(
                    componentePai, 
                    "Deseja sair do sistema de gerenciamento de Não Conformidades?", 
                    "Sair do Sistema", 
                    Resources.QUESTAO, 
                    null,
                    new String[]{"Sim","Não","Sair e não confirmar novamente","Não sair e não confirmar novamente"}
                )){
                case 0 :{
                    System.exit(0);
                    break;
                }
                case 2 : {
                    propriedade.escrever("confirmar.fechar", "false");
                    System.exit(0);
                    break;
                }
                case 3 : {
                    propriedade.escrever("confirmar.fechar", "false");
                    break;
                }
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
        usuario = usuarioController.login();
        if(usuario!=null){
            naoConformidadeController.setUsuario(usuario);
            abreTelaPrincipal();
        }
        else{
            System.exit(0);
        }
    }
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
        new Splash();
        new Controller().inicio();
    }  
}
