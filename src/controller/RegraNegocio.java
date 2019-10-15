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
            case INICIO : {
                etapas.add(Etapas.LOGIN);
                etapas.add(Etapas.ABRIR_FORMULARIO_PRINCIPAL);
            }
        }
        return etapas;
    }
}
