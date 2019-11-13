/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author leona
 */
public class Imagem {
    private Component componentePai;
    private BufferedImage imagem;
    
    public void escolherImagem(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de imagem", new String[]{"png", "gif", "jpeg", "jpg"}));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(componentePai)==JFileChooser.APPROVE_OPTION){//se o arquivo foi selecionado
            File arquivo = fileChooser.getSelectedFile();
            if(arquivo.exists()){
                try {
                    imagem = ImageIO.read(arquivo);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        }
    }
    
    public void removerImagem(){
        imagem = null;
    }
    
    public boolean isImagemValida(){
        return imagem != null;
    }
    
    public ImageIcon lerImagem(int largura, int altura){
        BufferedImage icone = ProcessarImagem(largura, altura);
        if(icone!=null){
            return new ImageIcon(ProcessarImagem(largura, altura));
        }
        return null;
    }
    
    public ImageIcon lerImagem(String caminho, int largura, int altura, Color... corFundo){
        if(caminho!=null){
            File arquivo = new File(caminho);
            if(arquivo.exists()){
                try {
                    imagem = ImageIO.read(arquivo);
                    return new ImageIcon(ProcessarImagem(largura, altura, corFundo));
                } catch (IOException ex) {

                }
            }
        }
        return null;
    }
    
    private BufferedImage ProcessarImagem(int largura, int altura, Color... corFundo){
        int larguraImagem;
        int alturaImagem;
        int larguraFinal;
        int alturaFinal;
        float aspectoImagem;
        if(imagem != null){
            larguraImagem = imagem.getWidth();
            alturaImagem = imagem.getHeight();
            aspectoImagem = (float) larguraImagem / alturaImagem;
            BufferedImage miniatura = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D graficosMiniatura = miniatura.createGraphics();
            if(corFundo.length == 0){
                graficosMiniatura.setColor(Color.WHITE);
            }
            else{
                graficosMiniatura.setColor(corFundo[0]);
            }
            graficosMiniatura.fillRect(0, 0, largura, altura);
            if(aspectoImagem >= 1.0 ){//se o a imagem tem a largura maior que altura
                if((int) alturaImagem/((float)larguraImagem/largura) > altura){//calcula a altura da imagem e verifica se não tem overflow na altura
                    larguraFinal=(int) ((int) larguraImagem/((float)alturaImagem/altura));
                    alturaFinal = (int) ((int) alturaImagem/((float)alturaImagem/altura));
                }else{
                    larguraFinal=(int) ((int) larguraImagem/((float)larguraImagem/largura));
                    alturaFinal = (int) ((int) alturaImagem/((float)larguraImagem/largura));
                }
            }
            else{
                if((int) larguraImagem/((float)alturaImagem/altura) > altura){//calcula a largura da imagem e verifica se não tem overflow na largura
                    larguraFinal=(int) ((int) larguraImagem/((float)larguraImagem/largura));
                    alturaFinal = (int) ((int) alturaImagem/((float)larguraImagem/largura));
                }else{
                    larguraFinal=(int) ((int) larguraImagem/((float)alturaImagem/altura));
                    alturaFinal = (int) ((int) alturaImagem/((float)alturaImagem/altura));
                }
            }
            graficosMiniatura.drawImage(
                    imagem, 
                    (largura - larguraFinal)/2,
                    (altura - alturaFinal)/2, 
                    larguraFinal, 
                    alturaFinal, 
                    null
            );
            return miniatura;
        }
        return null;
    }
    
    private BufferedImage processarImagemSalvar(int largura, int altura){
        int larguraImagem;
        int alturaImagem;
        int larguraFinal;
        int alturaFinal;
        float aspectoImagem;
        if(imagem != null){
            larguraImagem = imagem.getWidth();
            alturaImagem = imagem.getHeight();
            aspectoImagem = (float) larguraImagem / alturaImagem;
            if(aspectoImagem >= 1.0 ){//se o a imagem tem a largura maior que altura
                if((int) alturaImagem/((float)larguraImagem/largura) > altura){//calcula a altura da imagem e verifica se não tem overflow na altura
                    larguraFinal=(int) ((int) larguraImagem/((float)alturaImagem/altura));
                    alturaFinal = (int) ((int) alturaImagem/((float)alturaImagem/altura));
                }else{
                    larguraFinal=(int) ((int) larguraImagem/((float)larguraImagem/largura));
                    alturaFinal = (int) ((int) alturaImagem/((float)larguraImagem/largura));
                }
            }
            else{
                if((int) larguraImagem/((float)alturaImagem/altura) > altura){//calcula a largura da imagem e verifica se não tem overflow na largura
                    larguraFinal=(int) ((int) larguraImagem/((float)larguraImagem/largura));
                    alturaFinal = (int) ((int) alturaImagem/((float)larguraImagem/largura));
                }else{
                    larguraFinal=(int) ((int) larguraImagem/((float)alturaImagem/altura));
                    alturaFinal = (int) ((int) alturaImagem/((float)alturaImagem/altura));
                }
            }
            BufferedImage miniatura = new BufferedImage(larguraFinal, alturaFinal, BufferedImage.TYPE_INT_RGB);
            Graphics2D graficosMiniatura = miniatura.createGraphics();
            graficosMiniatura.setColor(Color.WHITE);
            graficosMiniatura.fillRect(0, 0, larguraFinal, alturaFinal);
            graficosMiniatura.drawImage(
                    imagem, 
                    0,
                    0,
                    larguraFinal, 
                    alturaFinal, 
                    null
            );
            return miniatura;
        }
        return null;
    }
    
    
    public void salvarImagem(String caminho){
        if(imagem!=null){
            try {
                File arquivoImagem = new File(caminho);
                if(!arquivoImagem.getParentFile().exists()){//se não existe o diretório, cria outro, se criar toda vez, as imagens são excluidas
                    arquivoImagem.getParentFile().mkdirs();
                }
                ImageIO.write(processarImagemSalvar(1280, 1280), "png", arquivoImagem);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
}
