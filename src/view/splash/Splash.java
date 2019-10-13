package view.splash;

import java.awt.*;
import javax.swing.*;

public class Splash extends JWindow {
    
    private int duration;
    
    public Splash(int d) {
        duration = d;
    }
    
// Este é um método simples para mostrar uma tela de apresentção

// no centro da tela durante a quantidade de tempo passada no construtor

    public void showSplash() {        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        // Configura a posição e o tamanho da janela
        int width = 750;
        int height = 420;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        // Constrói o splash screen
        JLabel label = new JLabel(new ImageIcon(System.getProperty("user.dir")+"//src//view//splash//gif.gif"));
        JLabel copyrt = new JLabel
                ("Ⓒ2019 Universidade Estadual de Goiás - Câmpus Itaberaí", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createLineBorder(Color.gray, 1));        
        // Torna visível
        setVisible(true);
        
        // Espera ate que os recursos estejam carregados
        try { Thread.sleep(duration); } catch (Exception e) {}        
        setVisible(false);        
    }
    
    public void showSplashAndExit() {        
        showSplash();        
    }
    
    public static void splash() {        
        // Mostra uma imagem com o título da aplicação
        Splash splash = new Splash(5000);
        splash.showSplashAndExit();        
    }
}