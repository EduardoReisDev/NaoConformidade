    package controller;

import dao.NaoConformidadeDao;
import java.awt.Color;
    import  java.awt.Component ;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.NaoConformidade;
    import  view.Mensagens ;
    import view.naoconformidade.CadatroNaoCoformidade;

    public class NaoConformidadeController {
        CadatroNaoCoformidade cadatroNaoCoformidade;
        Component componentePai;

    public  Component  getComponentePai () {
        return componentePai;
    }

    public  void  setComponentePai ( Component  componentePai ) {
        this.componentePai = componentePai;
    }
    public Icon imagem(){
         JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i= file.showOpenDialog(null);
        if (i==1){
            System.out.println("Nada pegado!");
            //visualizaImg.setIcon(null);
        } else {
            File arquivo = file.getSelectedFile();
            System.out.println((arquivo.getPath()));
            BufferedImage imagem = null;
            try {
                imagem = ImageIO.read(arquivo);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

            float larguraAux = imagem.getWidth() / 300;
            float alturaAux = imagem.getHeight() / 300;
            int largura = 300;
            int altura = 300;
            int larguraFinal;
            int alturaFinal;

            BufferedImage new_img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 300, 300);

            //primeira divisão
            if(imagem.getWidth() > imagem.getHeight()){ // se a largura é maior que a altura, usa a constante de calculo da largura
                larguraFinal = (int)(imagem.getWidth()/larguraAux);
                alturaFinal = (int) (imagem.getHeight()/larguraAux);
            }
            else { //se não, usa a constante de cálculo da altura
                larguraFinal = (int)(imagem.getWidth()/alturaAux);
                alturaFinal = (int) (imagem.getHeight()/alturaAux);
            }

            g.drawImage(imagem, (largura - larguraFinal)/2 ,(altura - alturaFinal)/2, larguraFinal, alturaFinal, null);
            ImageIcon icon = new ImageIcon(new_img);
           

            /*try {
                String aux= "D:\\Desktop\\teste.png";
                ImageIO.write(new_img, "png", new File(aux));
                ImageIcon icon = new ImageIcon(new_img);
                visualizaImg.setIcon(icon);
                visualizaImg.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }*/
            return icon;
        }
            
        return null;
    }
    
    public boolean validarTexto(String texto) { 
        return texto.length()>0;
    }
    
    public  void  obrigatorio( Component  componentePai ) {
        Mensagens.mensagem(componentePai," Preencha este campo! "," ATENÇÃO! ", 2 );
    }

    public NaoConformidade cadastrar () {
        
        cadatroNaoCoformidade = new CadatroNaoCoformidade(null, true);
        cadatroNaoCoformidade.setLocationRelativeTo(null);
        cadatroNaoCoformidade.setVisible(true);
        
        return null;
    }
    public void salvar(){
        NaoConformidadeDao naoConformidadeDao = new NaoConformidadeDao();
        NaoConformidade naoConformidade = new NaoConformidade();
        naoConformidade.setDescricao(cadatroNaoCoformidade.descricao.getText());
        naoConformidade.setAbrangencia(cadatroNaoCoformidade.abrangencia.getText());
        naoConformidade.setOrigem(cadatroNaoCoformidade.origem.getText());
        naoConformidade.setAcaoCorrecao(cadatroNaoCoformidade.acaoCorrecao.getText());
        naoConformidade.setDataRegistro(cadatroNaoCoformidade.dataRegistro.getSelectedDate().getTime());
        naoConformidade.setDataAcontecimento(cadatroNaoCoformidade.dataAcontecimento.getCurrent().getTime());
        naoConformidade.setReincidencia(cadatroNaoCoformidade.reincidencia.isSelected());
        //naoConformidade.setImagem(cadatroNaoCoformidade);
        if(!naoConformidadeDao.criar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "nao");
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "foi");
        }
    }

    
    
}