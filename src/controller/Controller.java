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
import java.awt.Frame;
import model.Usuario;
import java.util.Date;
import view.Principal;
import view.usuario.GerenciarUsuarios;
/**
 *
 * @author leona
 */
public class Controller {
    UsuarioController usuarioController = new UsuarioController();
    GerenciarUsuarios telaGerenciarUsuarios;
    Principal telaPrincipal;
    //<editor-fold defaultstate="collapsed" desc="comment">

    
    public void insereUsuarioDao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Leonardo");
        usuario.setCpf("06543651130");
        usuario.setUsuario("leo");
        usuario.setSenha("123");
        usuario.setMaster(true);
        System.out.println(new UsuarioDao().salvar(usuario));
    }
    
    public void listaUsuarios(){
        new UsuarioDao().listarTodos(usuario->{
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
        
        System.out.println(new SetorDao().salvar(setor));
    }
    
    public void listarSetores(){
        new SetorDao().listarTodos(setor->{
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
        naoConformidade.setCodigo("00005463");
        naoConformidade.setDataAcontecimento(new Date(2020, 9, 24));
        naoConformidade.setDataRegistro(new Date(19, 9, 24));
        naoConformidade.setDescricao("uma gaiola quebrada");
        naoConformidade.setIdSetor(2);
        naoConformidade.setImagem("C:\001.png");
        naoConformidade.setOrigem("Baixa da égua");
        naoConformidade.setReincidencia(3);
        naoConformidade.setResponsavel("fulano");
        System.out.println(new NaoConformidadeDao().salvar(naoConformidade));
    }
    
    public void editarNaoConformidade(){
        NaoConformidade naoConformidade = new NaoConformidade();
        naoConformidade.setAbrangencia("xxxx");
        naoConformidade.setAcaoCorrecao("teste");
        naoConformidade.setCodigo("00005463");
        naoConformidade.setDataAcontecimento(new Date(2020, 9, 24));
        naoConformidade.setDataRegistro(new Date(19, 9, 24));
        naoConformidade.setDescricao("uma gaiola quebrada");
        naoConformidade.setIdSetor(2);
        naoConformidade.setImagem("C:\001.png");
        naoConformidade.setOrigem("Baixa da égua");
        naoConformidade.setReincidencia(3);
        naoConformidade.setResponsavel("eduardo");
        naoConformidade.setId(1);
        System.out.println(new NaoConformidadeDao().editar(naoConformidade));
    }
    
    public void listarNaoConformidades(){
        new NaoConformidadeDao().listarTodos(naoConformidade->{
            System.out.println(naoConformidade.getId());
            System.out.println(naoConformidade.getResponsavel());
        });
    }
//</editor-fold>
    
    public void inicio(){
        login();
    }
    
    public void abreTelaGerenciarUsuarios(Frame formPai){
        if(usuarioController.loginMaster()){
            telaGerenciarUsuarios =  new GerenciarUsuarios(formPai, true);
            telaGerenciarUsuarios.setLocationRelativeTo(null);
            telaGerenciarUsuarios.inicializarTabela();
            telaGerenciarUsuarios.setVisible(true);
        }
    }
    
    private void abreTelaPrincipal(){
        telaPrincipal = new Principal();
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setExtendedState(Principal.MAXIMIZED_BOTH);
        telaPrincipal.setVisible(true);
    }
    
    public void login(){
        abreTelaPrincipal();
        usuarioController.setFormPai(telaPrincipal);
        Usuario usuario = usuarioController.login();
        if(usuario!=null){
            //adiciona todo o conteúdo da tela principal
        }
        else{
            System.exit(0);
        }
    }
}
