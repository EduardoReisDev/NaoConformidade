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
import model.Usuario;
import java.util.Date;
import javax.swing.JFrame;
import view.Principal;
import view.gerenciar.FormUsuario;
/**
 *
 * @author leona
 */
public class Controller {
    UsuarioController usuarioController = new UsuarioController();
    FormUsuario telaGerenciarUsuarios;
    JFrame componentePai;

    public void setTelaPrincipal(JFrame telaPrincipal) {
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
        switch (acao) {
            case INICIO : {
                RegraNegocio.obterEtapas(acao).forEach(etapa->{
                    if(sucessoAcoes){
                        separarEtapas(etapa);
                    }
                });
            }
            case ABRE_FORMULARIO_USUARIOS : {
                UsuarioBusinnesObject.obterEtapas(acao).forEach(etapa->{
                    if(sucessoAcoes){
                        separarEtapas(etapa);
                    }
                }); 
                break;
            }
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
        }
        return false;
    }
    
    public void inicio(){
        executar(Acao.INICIO);
    }
    
    public void abreTelaGerenciarUsuarios(){
        telaGerenciarUsuarios =  new FormUsuario(componentePai, true);
        telaGerenciarUsuarios.setLocationRelativeTo(null);
        telaGerenciarUsuarios.inicializarTabela();
        telaGerenciarUsuarios.setVisible(true);
    }
    
    private void abreTelaPrincipal(){
        componentePai = new Principal();
        componentePai.setLocationRelativeTo(null);
        componentePai.setExtendedState(Principal.MAXIMIZED_BOTH);
        componentePai.setVisible(true);
    }
    
    public void login(){
        abreTelaPrincipal();
        usuarioController.setFormPai(componentePai);
        Usuario usuario = usuarioController.login();
        if(usuario!=null){
            //adiciona todo o conteúdo da tela principal
        }
        else{
            System.exit(0);
        }
    }
}
