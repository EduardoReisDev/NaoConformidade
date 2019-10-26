package controller;

import dao.NaoConformidadeDao;
import dao.ResponsavelDao;
import dao.SetorDao;
import java.awt.Component ;
import java.awt.Frame;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import model.NaoConformidade;
import model.Responsavel;
import model.Setor;
import view.DialogoImagemNaoConformidade;
import view.Mensagens ;
import view.naoconformidade.CadatroNaoCoformidade;
import view.naoconformidade.FormDetalheNaoConformidade;

public class NaoConformidadeController {
    CadatroNaoCoformidade cadastroNaoCoformidade;
    Component componentePai;
    String novoCaminho = null;
    NaoConformidadeDao naoConformidadeDao = new NaoConformidadeDao();
    NaoConformidade naoConformidade = new NaoConformidade();
    ImagemController imagemController = new ImagemController();

    public ImagemController getImagemController() {
        return imagemController;
    }

    public void setImagemController(ImagemController imagemController) {
        this.imagemController = imagemController;
    }
    
    
    
    
    public Component  getComponentePai () {
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
    
    public NaoConformidade listarPorId(int id){
        return new NaoConformidadeDao().listarPorId(id);
    }
    
    public void listarTodos(){
        
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
        imagemController.salvarImagem(naoConformidade.getImagem());
        if(naoConformidadeDao.criar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "Dados cadastrados com sucesso!","Sucesso!",1);
            cadastroNaoCoformidade.dispose();
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "nao");
        }
    }
     
    public void salvar(){
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
        if(naoConformidadeDao.criar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "Dados cadastrados com sucesso!","Sucesso!",1);
            cadastroNaoCoformidade.dispose();
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "nao");
        }
    }

    public void mostrarNaoConformidade(int id) {
        FormDetalheNaoConformidade formDetalheNaoConformidade = new FormDetalheNaoConformidade((Frame) componentePai, true, this);
        formDetalheNaoConformidade.setLocationRelativeTo(null);
        formDetalheNaoConformidade.listar(id);
        formDetalheNaoConformidade.setVisible(true);
    }
    
    public void exibirImagem(){
        DialogoImagemNaoConformidade dialogoImagemNaoConformidade;
        dialogoImagemNaoConformidade = new DialogoImagemNaoConformidade((Frame) componentePai, true, this);
        dialogoImagemNaoConformidade.setLocationRelativeTo(null);
        dialogoImagemNaoConformidade.setVisible(true);
    }
}