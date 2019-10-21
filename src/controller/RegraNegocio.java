/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class RegraNegocio {
    public static ArrayList<Etapas> obterEtapas(Acao acao){
        ArrayList<Etapas> etapas = new ArrayList<>();
        switch (acao) {
            case INICIAR_SISTEMA : {
                //etapas.add(Etapas.LOGIN);
                etapas.add(Etapas.ABRIR_FORMULARIO_PRINCIPAL);
                break;
            }
            case ABRE_FORMULARIO_USUARIO : {
                //etapas.add(Etapas.LOGIN_MASTER);
                etapas.add(Etapas.ABRIR_FORMULARIO_USUARIOS);
                break;
            }
            case CADASTRO_USUARIO : {
                etapas.add(Etapas.ABRIR_FORMULARIO_CADASTRO_USUARIOS);
                break;
            }
            case EDITA_USUARIO : {
                etapas.add(Etapas.ABRIR_FORMULARIO_EDITA_USUARIOS);
                break;
            }
            case EXCLUI_USUARIO : {
                etapas.add(Etapas.CONFIRMAR_EXCLUSAO_USUARIO);
                etapas.add(Etapas.EXCLUIR_USUARIO);
                etapas.add(Etapas.MOSTRAR_MENSAGEM_SUCESSO_EXCLIUR);
                break;
            }
            case ABRE_FORMULARIO_RESPONSAVEL : {
                etapas.add(Etapas.ABRIR_FORMULARIO_RESPONSAVEL);
                break;
            }
            case ABRE_FORMULARIO_SETOR : {
                etapas.add(Etapas.ABRIR_FORMULARIO_SETORES);
                break;
            }
            case ABRE_FORMULARIO_NAO_CONFORMIDADE : {
                etapas.add(Etapas.ABRIR_FORMULARIO_NAO_CONFORMIDADES);
                break;
            }
            case FECHAR : {
                etapas.add(Etapas.SAIR);
            }
        }
        return etapas;
    }
    
    public static ArrayList<Etapas> obterEtapasSeErro(Acao acao){
        ArrayList<Etapas> etapas = new ArrayList<>();
        switch (acao) {
            case INICIAR_SISTEMA : {
                etapas.add(Etapas.SAIR);
                break;
            }
            case EXCLUI_USUARIO : {
                etapas.add(Etapas.MOSTRAR_MENSAGEM_ERRO_EXCLIUR);
                break;
            }
        }
        return etapas;
    }
}
