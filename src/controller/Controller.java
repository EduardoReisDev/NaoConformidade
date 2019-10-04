/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAccessObject.NaoConformidadeDataObject;
import DataAccessObject.NaoConformidadeValueObject;
import DataAccessObject.SetorDataObject;
import DataAccessObject.SetorValueObject;
import DataAccessObject.UsuarioDataObject;
import DataAccessObject.UsuarioValueObject;
import java.util.Date;
import view.Principal;
/**
 *
 * @author leona
 */
public class Controller {
    
    public void insereUsuario(){
        UsuarioValueObject usuario = new UsuarioValueObject();
        usuario.setNome("Leonardo");
        usuario.setCpf("06543651130");
        usuario.setUsuario("leo");
        usuario.setSenha("123");
        usuario.setMaster(true);
        System.out.println(new UsuarioDataObject().salvar(usuario));
    }
    
    public void listaUsuarios(){
        new UsuarioDataObject().listarTodos(usuario->{
            System.out.println(usuario.getId());
            System.out.println(usuario.getNome());
            System.out.println(usuario.getUsuario());
            System.out.println(usuario.getSenha());
        });
    }
    
    public void excluirUsuario(){
        new UsuarioDataObject().excluir(1);
    }
    
    public void editarUsuario(){
        UsuarioValueObject usuario = new UsuarioValueObject();
        usuario.setNome("Léo");
        usuario.setUsuario("leo");
        usuario.setSenha("123");
        usuario.setId(2);
        
        System.out.println(new UsuarioDataObject().editar(usuario));
    }
    
    public void insereSetor(){
        SetorValueObject setor = new SetorValueObject();
        setor.setCodigo("0001145");
        setor.setNome("Casa das Galinhas");
        setor.setResponsavel("Fulano das Couve");
        
        System.out.println(new SetorDataObject().salvar(setor));
    }
    
    public void listarSetores(){
        new SetorDataObject().listarTodos(setor->{
            System.out.println(setor.getId());
            System.out.println(setor.getNome());
            System.out.println(setor.getCodigo());
            System.out.println(setor.getResponsavel());
        });
    }
    
    public void editarSetor(){
        SetorValueObject setor = new SetorValueObject();
        setor.setCodigo("0001145");
        setor.setNome("Casa dos Galos");
        setor.setResponsavel("Fulano das Couve");
        setor.setId(1);
        System.out.println(new SetorDataObject().editar(setor));
    }
    
    public void excluirSetor(){
        new SetorDataObject().excluir(1);
    }
    
    public void salvarNaoConformidade(){
        NaoConformidadeValueObject naoConformidade = new NaoConformidadeValueObject();
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
        System.out.println(new NaoConformidadeDataObject().salvar(naoConformidade));
    }
    
    public void editarNaoConformidade(){
        NaoConformidadeValueObject naoConformidade = new NaoConformidadeValueObject();
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
        System.out.println(new NaoConformidadeDataObject().editar(naoConformidade));
    }
    
    public void listarNaoConformidades(){
        new NaoConformidadeDataObject().listarTodos(naoConformidade->{
            System.out.println(naoConformidade.getId());
            System.out.println(naoConformidade.getResponsavel());
        });
    }
    
    public void inicio(){
        login();
        
    }
    
    UsuarioController controllerUsuario = new UsuarioController();
    Principal telaPrincipal = new Principal();
    public void login(){
        if(controllerUsuario.login()){
        
            telaPrincipal.setVisible(true);
        }
    }
    
    public static void main(String[] args){
        new Controller().inicio();
    }
}
