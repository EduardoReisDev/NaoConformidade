package controller;

import dao.NaoConformidadeDao;
import dao.ResponsavelDao;
import dao.SetorDao;
import java.awt.Color;
import java.awt.Component ;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.NaoConformidade;
import model.Responsavel;
import model.Setor;
import view.Mensagens ;
import view.naoconformidade.CadatroNaoCoformidade;

public class NaoConformidadeController {
    CadatroNaoCoformidade cadastroNaoCoformidade;
    Component componentePai;
    String novoCaminho = null;
    NaoConformidadeDao naoConformidadeDao = new NaoConformidadeDao();
    NaoConformidade naoConformidade = new NaoConformidade();
    
    private BufferedImage imagem;
    
    public  Component  getComponentePai () {
        return componentePai;
    }

    public  void  setComponentePai(Component  componentePai){
        this.componentePai = componentePai;
    }
    
    public void listarTodosSetores(Consumer<?super Setor> setor){
        new SetorDao().listarTodos(setor::accept);
    }
    
    public void listarTodosResponsaveis(Consumer<?super Responsavel> responsavel){
        new ResponsavelDao().listarTodos(responsavel::accept);
    }
    
    public int getLastId(){
        return new NaoConformidadeDao().getLastId();
    }
    
    
    public Icon escolherImagem(){
        float constanteCalculoLargura;
        float constanteCalculoAltura;
        int larguraLabel = 300;
        int alturaLabel = 300;
        int larguraFinal;
        int alturaFinal;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de imagem", new String[]{"png", "gif", "jpeg", "jpg"}));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(componentePai)==JFileChooser.APPROVE_OPTION){//se o arquivo foi selecionado
            File arquivo = fileChooser.getSelectedFile();
            if(arquivo.exists()){
                try {
                    imagem = ImageIO.read(arquivo);
                    constanteCalculoLargura = imagem.getWidth() / larguraLabel;
                    constanteCalculoAltura = imagem.getHeight() / alturaLabel;
                    BufferedImage miniatura = new BufferedImage(larguraLabel, alturaLabel, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graficosMiniatura = miniatura.createGraphics();
                    graficosMiniatura.setColor(Color.WHITE);
                    graficosMiniatura.fillRect(0, 0, larguraLabel, alturaLabel);
                    if(imagem.getWidth() >= imagem.getHeight()){ // se a largura é maior que a altura, usa a constante de calculo da largura
                        if(imagem.getWidth() < larguraLabel){
                            larguraFinal = imagem.getWidth();
                            alturaFinal = imagem.getHeight();
                        }
                        else{
                            larguraFinal = (int)(imagem.getWidth()/constanteCalculoLargura);
                            alturaFinal = (int) (imagem.getHeight()/constanteCalculoLargura);
                        }
                    }
                    else { //se não, usa a constante de cálculo da altura
                        if(imagem.getHeight()< alturaLabel){
                            larguraFinal = imagem.getWidth();
                            alturaFinal = imagem.getHeight();
                        }
                        else{
                            larguraFinal = (int)(imagem.getWidth()/constanteCalculoAltura);
                            alturaFinal = (int) (imagem.getHeight()/constanteCalculoAltura);
                        }
                    }
                    graficosMiniatura.drawImage(
                            imagem, 
                            (larguraLabel - larguraFinal)/2,
                            (alturaLabel - alturaFinal)/2, 
                            larguraFinal, 
                            alturaFinal, 
                            null
                    );
                    return new ImageIcon(miniatura);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        }
        return null;
    }
    
    public void salvarImagem(String caminho){
        if(imagem!=null){
            float constanteCalculoAltura = (float) imagem.getWidth()/720;
            float constanteCalculoLargura = (float) imagem.getHeight()/720;
            int larguraFinal;
            int alturaFinal;
            BufferedImage imagemSalvar;
            Graphics2D graficosImagemSalvar;

            //primeira divisão
            if(imagem.getWidth() >= imagem.getHeight()){ // se a largura é maior que a altura, usa a constante de calculo da largura
                if(imagem.getWidth() < 720){
                    larguraFinal = imagem.getWidth();
                    alturaFinal = imagem.getHeight();
                }
                else{
                    larguraFinal = (int) (imagem.getWidth()/constanteCalculoLargura);
                    alturaFinal = (int) (imagem.getHeight()/constanteCalculoLargura);
                }
            }
            else { //se não, usa a constante de cálculo da altura
                if(imagem.getWidth()< 720){
                    larguraFinal = imagem.getWidth();
                    alturaFinal = imagem.getHeight();
                }
                else{
                    larguraFinal = (int) (imagem.getWidth()/constanteCalculoAltura);
                    alturaFinal = (int) (imagem.getHeight()/constanteCalculoAltura);
                }
            }
            imagemSalvar = new BufferedImage(larguraFinal, alturaFinal, BufferedImage.TYPE_INT_RGB);
            graficosImagemSalvar = imagemSalvar.createGraphics();
            graficosImagemSalvar.fillRect(0, 0, larguraFinal, alturaFinal);
            graficosImagemSalvar.drawImage(imagem, 0,0, larguraFinal, alturaFinal, null);
            try {
                File arquivoImagem = new File(caminho);
                if(!arquivoImagem.getParentFile().exists()){//se não existe o diretório, cria outro, se criar toda vez, as imagens são excluidas
                    arquivoImagem.getParentFile().mkdirs();
                }
                ImageIO.write(imagemSalvar, "png", arquivoImagem) ;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
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

    public void cadastrar () {
        cadastroNaoCoformidade = new CadatroNaoCoformidade(null, true, this);
        cadastroNaoCoformidade.setLocationRelativeTo(null);
        cadastroNaoCoformidade.setVisible(true);
    }
    
    public void listarNaoConformidades(Consumer<? super NaoConformidade> resultado){
        new NaoConformidadeDao().listarTodos(resultado::accept);
    }
    
     
     
     
    public void salvar(NaoConformidade naoConformidade){
        //salva imagem
        salvarImagem(naoConformidade.getImagem());
        if(naoConformidadeDao.criar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "Dados cadastrados com sucesso!","Sucesso!",1);
            cadastroNaoCoformidade.dispose();
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "nao");
        }
    }
     
    public void salvar(){
        
        /*naoConformidade.setId(Integer.parseInt(cadastroNaoCoformidade.Codigo.getText()));
        naoConformidade.setDescricao(cadastroNaoCoformidade.descricao.getText());
        naoConformidade.setAbrangencia(cadastroNaoCoformidade.abrangencia.getText());
        naoConformidade.setOrigem(cadastroNaoCoformidade.origem.getText());
        naoConformidade.setAcaoCorrecao(cadastroNaoCoformidade.acaoCorrecao.getText());
        naoConformidade.setDataRegistro(cadastroNaoCoformidade.dataRegistro.getSelectedDate().getTime());
        naoConformidade.setDataAcontecimento(cadastroNaoCoformidade.dataAcontecimento.getCurrent().getTime());
        naoConformidade.setReincidencia(cadastroNaoCoformidade.reincidencia.isSelected());
        naoConformidade.setImagem(novoCaminho);
        naoConformidade.setIdResponsavel(
                cadastroNaoCoformidade.getResponsavelNaoConformidade().
                        get(cadastroNaoCoformidade.Responsavel.getSelectedIndex()).getIdResponsavel());
        
        */
        naoConformidade = new NaoConformidade(
                0, 
                cadastroNaoCoformidade.abrangencia.getText(),
                cadastroNaoCoformidade.abrangencia.getText(), 
                cadastroNaoCoformidade.dataAcontecimento.getCurrent().getTime(),
                cadastroNaoCoformidade.dataRegistro.getSelectedDate().getTime(),
                cadastroNaoCoformidade.descricao.getText(), 
                novoCaminho,
                cadastroNaoCoformidade.origem.getText(), 
                cadastroNaoCoformidade.reincidencia.isSelected(), 
                new Setor(
                    cadastroNaoCoformidade.getSetorNaoConformidade().get(cadastroNaoCoformidade.Setor.getSelectedIndex()).getId()
                ),
                new Responsavel(
                        cadastroNaoCoformidade.getResponsavelNaoConformidade().get(cadastroNaoCoformidade.Responsavel.getSelectedIndex()).getId()//id do responsável
                )
        );
        System.out.println(naoConformidade.toString());
        if(naoConformidadeDao.criar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "Dados cadastrados com sucesso!","Sucesso!",1);
            cadastroNaoCoformidade.dispose();
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "nao");
        }
    }
}