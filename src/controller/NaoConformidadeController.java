package controller;

import resources.Imagem;
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
import view.naoconformidade.EditarNaoConformidade;
import view.naoconformidade.FormDetalheNaoConformidade;

public class NaoConformidadeController {
    CadatroNaoCoformidade cadastroNaoCoformidade;
    EditarNaoConformidade editarNaoConformidade;
    Component componentePai;
    String novoCaminho = null;
    NaoConformidadeDao naoConformidadeDao = new NaoConformidadeDao();
    NaoConformidade naoConformidade = new NaoConformidade();
    Imagem imagemController = new Imagem();

    public Imagem getImagemController() {
        return imagemController;
    }

    public void setImagemController(Imagem imagemController) {
        this.imagemController = imagemController;
    }
    
    public Component  getComponentePai (){
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
    
    public void listarTodos(Consumer<?super NaoConformidade> naoConformidade){
        new NaoConformidadeDao().listarTodos(naoConformidade::accept);
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
    
    public void listarPorDescricao(Consumer<? super NaoConformidade> resultado, String descricao){
        new NaoConformidadeDao().listarPorDescricao(resultado::accept,descricao);
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
     public void atualizar(NaoConformidade naoConformidade){
        //salva imagem
        imagemController.salvarImagem(naoConformidade.getImagem());
        if(naoConformidadeDao.editar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "Dados atualizados com sucesso!","Sucesso!",1);
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

    public void editar(int id){
        editarNaoConformidade = new EditarNaoConformidade(null, true, this, id);
        editarNaoConformidade.setLocationRelativeTo(null);
        editarNaoConformidade.setVisible(true);
    }
}