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
import javax.swing.filechooser.FileNameExtensionFilter;
import model.NaoConformidade;
    import  view.Mensagens ;
    import view.naoconformidade.CadatroNaoCoformidade;

    public class NaoConformidadeController {
        CadatroNaoCoformidade cadastroNaoCoformidade;
        Component componentePai;
        String novoCaminho = null;
        NaoConformidadeDao naoConformidadeDao = new NaoConformidadeDao();
        NaoConformidade naoConformidade = new NaoConformidade();

    public  Component  getComponentePai () {
        return componentePai;
    }

    public  void  setComponentePai(Component  componentePai){
        this.componentePai = componentePai;
    }
    public Icon imagem(){
        JFileChooser file = new JFileChooser();
        file.setFileFilter(new FileNameExtensionFilter("Arquivos de imagem", new String[]{"png", "gif", "jpeg", "jpg"}));
        file.setAcceptAllFileFilterUsed(false);
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i= file.showOpenDialog(null);
        if (i==1){
            System.out.println("Nada pegado!");
            //visualizaImg.setIcon(null);
        } else {
            File arquivo = file.getSelectedFile();
            if(arquivo.exists()){
                System.out.println((arquivo.getPath()));
                BufferedImage imagem = null;
                try {
                    imagem = ImageIO.read(arquivo);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }

                float larguraAux = imagem.getWidth() / 300;
                float alturaAux = imagem.getHeight() / 300;
                int largura = 300;
                int altura = 300;
                int larguraFinal;
                int alturaFinal;
                
                float alturaAuxFinal = (float) imagem.getWidth()/720;
                float larguraAuxFinal = (float) imagem.getHeight()/720;
                int larguraFim;
                int alturaFim;

                BufferedImage new_img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = new_img.createGraphics();
                
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, 300, 300);

                //primeira divisão
                if(imagem.getWidth() >= imagem.getHeight()){ // se a largura é maior que a altura, usa a constante de calculo da largura
                    larguraFinal = (int)(imagem.getWidth()/larguraAux);
                    alturaFinal = (int) (imagem.getHeight()/larguraAux);
                    if(imagem.getWidth() < 720){
                        larguraFim = imagem.getWidth();
                        alturaFim = imagem.getHeight();
                    }
                    else{
                        larguraFim = (int) (imagem.getWidth()/larguraAuxFinal);
                        alturaFim = (int) (imagem.getHeight()/larguraAuxFinal);
                    }
                }
                else { //se não, usa a constante de cálculo da altura
                    larguraFinal = (int)(imagem.getWidth()/alturaAux);
                    alturaFinal = (int) (imagem.getHeight()/alturaAux);
                    if(imagem.getWidth()< 720){
                        larguraFim = imagem.getWidth();
                        alturaFim = imagem.getHeight();
                    }
                    else{
                        larguraFim = (int) (imagem.getWidth()/alturaAuxFinal);
                        alturaFim = (int) (imagem.getHeight()/alturaAuxFinal);
                    }
                }
                
                g.drawImage(imagem, (largura - larguraFinal)/2 ,(altura - alturaFinal)/2, larguraFinal, alturaFinal, null);
                ImageIcon icon = new ImageIcon(new_img);
                BufferedImage imagemNova = new BufferedImage(larguraFim, alturaFim, BufferedImage.TYPE_INT_RGB);
                Graphics2D k = imagemNova.createGraphics();
                k.fillRect(0, 0, larguraFim, alturaFim);
                k.drawImage(imagem, 0,0, larguraFim, alturaFim, null);
                try {
                    novoCaminho = String.valueOf("imagens não conformidades/imagem de não conformidade"+(cadastroNaoCoformidade.Codigo.getText())+".png");
                    File arquivoX = new File(novoCaminho);
                    arquivoX.getParentFile().mkdirs();
                    ImageIO.write(imagemNova, "png", arquivoX) ;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                
                return icon;
            }
            return null;
        }
            
        return null;
    }
    
    public boolean validarTexto(String texto) { 
        return texto.length()>2;
    }
    
    public  void  obrigatorio( Component  componentePai ) {
        Mensagens.mensagem(componentePai,"Preencha este campo corretamente!\nMínimo 3 caracteres! "," Atenção! ", 2 );
    }

    public NaoConformidade cadastrar () {
        
        cadastroNaoCoformidade = new CadatroNaoCoformidade(null, true, this);
        cadastroNaoCoformidade.setLocationRelativeTo(null);
        cadastroNaoCoformidade.setVisible(true);
        return null;
    }
    public void salvar(){
        
        naoConformidade.setId(Integer.parseInt(cadastroNaoCoformidade.Codigo.getText()));
        naoConformidade.setDescricao(cadastroNaoCoformidade.descricao.getText());
        naoConformidade.setAbrangencia(cadastroNaoCoformidade.abrangencia.getText());
        naoConformidade.setOrigem(cadastroNaoCoformidade.origem.getText());
        naoConformidade.setAcaoCorrecao(cadastroNaoCoformidade.acaoCorrecao.getText());
        naoConformidade.setDataRegistro(cadastroNaoCoformidade.dataRegistro.getSelectedDate().getTime());
        naoConformidade.setDataAcontecimento(cadastroNaoCoformidade.dataAcontecimento.getCurrent().getTime());
        naoConformidade.setReincidencia(cadastroNaoCoformidade.reincidencia.isSelected());
        naoConformidade.setImagem(novoCaminho);
        naoConformidade.setIdResponsavel(cadastroNaoCoformidade.getResponsavelNaoConformidade().get(cadastroNaoCoformidade.Responsavel.getSelectedIndex()).getId());
        if(!naoConformidadeDao.criar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "nao");
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "Dados cadastrados com sucesso!","Sucesso!",1);
            cadastroNaoCoformidade.dispose();
        }
    }
}