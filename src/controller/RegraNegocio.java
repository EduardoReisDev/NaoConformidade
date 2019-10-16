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
                etapas.add(Etapas.LOGIN);
                etapas.add(Etapas.ABRIR_FORMULARIO_PRINCIPAL);
                break;
            }
            case ABRE_FORMULARIO_USUARIOS : {
                etapas.add(Etapas.LOGIN_MASTER);
                etapas.add(Etapas.ABRIR_FORMULARIO_USUARIOS);
                break;
            }
            case ABRE_FORMULARIO_RESPONSAVEL : {
                etapas.add(Etapas.ABRIR_FORMULARIO_RESPONSAVEL);
                break;
            }
        }
        return etapas;
    }
    
    public static ArrayList<Etapas> obterEtapasErros(Acao acao){
        ArrayList<Etapas> etapas = new ArrayList<>();
        switch (acao) {
            case INICIAR_SISTEMA : {
                etapas.add(Etapas.SAIR);
            }
        }
        return etapas;
    }
}
