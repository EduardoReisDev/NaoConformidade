package controller;

import resources.Imagem;
import dao.NaoConformidadeDao;
import dao.ResponsavelDao;
import dao.SetorDao;
import java.awt.Component ;
import java.awt.Frame;
import java.util.Date;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import model.NaoConformidade;
import model.Responsavel;
import model.Setor;
import view.Mensagens ;
import view.naoconformidade.CadastroNaoCoformidade;
import view.naoconformidade.EditarNaoConformidade;
import view.naoconformidade.FormDetalheNaoConformidade;
import view.naoconformidade.FrameImagem;

public class NaoConformidadeController {
    CadastroNaoCoformidade cadastroNaoCoformidade;
    EditarNaoConformidade editarNaoConformidade;
    Component componentePai;
    String novoCaminho = null;
    NaoConformidadeDao naoConformidadeDao = new NaoConformidadeDao();
    NaoConformidade naoConformidade = new NaoConformidade();
    Imagem imagem = new Imagem();
    
    public Imagem getImagemController() {
        return imagem;
    }

    public void setImagemController(Imagem imagemController) {
        this.imagem = imagemController;
    }
    
    public Component getComponentePai(){
        return componentePai;
    }

    public void setComponentePai(Component componentePai){
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
    
    public void listarIntervaloDeData(Consumer<?super NaoConformidade> naoConformidade, Date dataInicio, Date dataFim){
        new NaoConformidadeDao().listarPorIntevaloData(naoConformidade::accept, dataInicio, dataFim);
    }
    
    public boolean validarTexto(String texto){ 
        return texto.length()>2;
    }
    
    public void obrigatorio(Component componentePai){
        Mensagens.mensagem(componentePai,"Preencha este campo corretamente!\nMínimo 3 caracteres! "," Atenção! ", 2 );
    }

    public void cadastrar(){
        cadastroNaoCoformidade = new CadastroNaoCoformidade((Frame) componentePai, true, this);
        cadastroNaoCoformidade.setLocationRelativeTo(componentePai);
        cadastroNaoCoformidade.setVisible(true);
    }
    
    public void listarPorDescricao(Consumer<? super NaoConformidade> resultado, String descricao){
        new NaoConformidadeDao().listarPorDescricao(resultado::accept,descricao);
    }
     
    public void salvar(NaoConformidade naoConformidade){
        //salva imagem
        imagem.salvarImagem(naoConformidade.getImagem());
        if(naoConformidadeDao.criar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "Dados cadastrados com sucesso!","Sucesso!",1);
            cadastroNaoCoformidade.dispose();
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "Não foi possível cadastrar!");
        }
    }
    
    public void atualizar(NaoConformidade naoConformidade){
        //salva imagem
        imagem.salvarImagem(naoConformidade.getImagem());
        if(naoConformidadeDao.editar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "Dados atualizados com sucesso!","Sucesso!",1);
            editarNaoConformidade.dispose();
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "Não foi possível atualizar!");
        }
    }

    public void mostrarNaoConformidade(int id) {
        FormDetalheNaoConformidade formDetalheNaoConformidade = new FormDetalheNaoConformidade((Frame) componentePai, true, this);
        formDetalheNaoConformidade.setLocationRelativeTo(componentePai);
        formDetalheNaoConformidade.listar(id);
        formDetalheNaoConformidade.setVisible(true);
    }
    
    public void exibirImagem(){
        FrameImagem frameImagem;
        frameImagem = new FrameImagem(this);
        frameImagem.setLocationRelativeTo(componentePai);
        frameImagem.setVisible(true);
            
    }

    public void editar(int id){
        editarNaoConformidade = new EditarNaoConformidade((Frame) componentePai, true, this, id);
        editarNaoConformidade.setLocationRelativeTo(componentePai);
        editarNaoConformidade.setVisible(true);
    }
    
    public void excluir(int id){
        if(naoConformidadeDao.excluir(id)){
            JOptionPane.showMessageDialog(componentePai, "Não conformidade excluída com sucesso!","Sucesso!",1);
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "Não foi possível excluir!");
        }
    }
            
}