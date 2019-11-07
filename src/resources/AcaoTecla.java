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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AcaoTecla implements KeyListener{
    private final Controller controller;

    public AcaoTecla(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void keyPressed(KeyEvent evt){
        switch(evt.getKeyCode()){
            case(KeyEvent.VK_F1):
                controller.abreTelaUsuario();
                break;
            case(KeyEvent.VK_F2):
                controller.abreTelaResponsavel();
                break;
            case(KeyEvent.VK_F3):
                controller.abreTelaNaoConformidade();
                break;
            case(KeyEvent.VK_F4):
                controller.abreTelaSetor();
                break;
            case(KeyEvent.VK_ESCAPE):
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
