/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author Ricardo
 */
import controller.Controller;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclas implements KeyListener{
    Controller controller;
    public void recebeController(Controller controller){
        this.controller=controller;
    }
    @Override
    public void keyPressed(KeyEvent evt){
        switch(evt.getKeyCode()){
            case(KeyEvent.VK_F1):
                System.out.println("Você pressionou a tecla F1");
                controller.abreTelaUsuario();
                break;
            case(KeyEvent.VK_F2):
                System.out.println("Você pressionou a tecla F2");
                controller.abreTelaResponsavel();
                break;
            case(KeyEvent.VK_F3):
                System.out.println("Você pressionou a tecla F3");
                controller.abreTelaNaoConformidade();
                break;
            case(KeyEvent.VK_F4):
                System.out.println("Você pressionou a tecla F4");
                controller.abreTelaSetor();
                break;
            case(KeyEvent.VK_ESCAPE):
                System.out.println("Você pressionou a tecla ESC");
                System.exit(0);
                break;
        }
            System.out.println("Código da tecla: " + evt.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
