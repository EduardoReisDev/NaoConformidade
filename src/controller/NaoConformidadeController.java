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
    private CadastroNaoCoformidade cadastroNaoCoformidade;
    private EditarNaoConformidade editarNaoConformidade;
    private Component componentePai;
    private final NaoConformidadeDao naoConformidadeDao;
    private final SetorDao setorDao;
    private final ResponsavelDao responsavelDao;
    private final Imagem imagem;
    private FrameImagem frameImagem;
    private FormDetalheNaoConformidade formDetalheNaoConformidade;
    
    public NaoConformidadeController() {
        this.imagem = new Imagem();
        this.naoConformidadeDao = new NaoConformidadeDao();
        this.setorDao = new SetorDao();
        this.responsavelDao = new ResponsavelDao();
    }
    
    public Imagem getImagem() {
        return imagem;
    }

    public void setComponentePai(Component componentePai){
        this.componentePai = componentePai;
    }
    
    public void listarTodosSetores(Consumer<?super Setor> setor){
        setorDao.listarTodos(setor::accept);
    }
    
    public void listarTodosResponsaveis(Consumer<?super Responsavel> responsavel){
        responsavelDao.listarTodos(responsavel::accept);
    }
    
    public int getLastId(){
        return naoConformidadeDao.getLastId();
    }
    
    public NaoConformidade listarPorId(int id){
        return naoConformidadeDao.listarPorId(id);
    }
    
    public void listarTodos(Consumer<?super NaoConformidade> naoConformidade){
        naoConformidadeDao.listarTodos(naoConformidade::accept);
    }
    
    public void listarIntervaloDeData(Consumer<?super NaoConformidade> naoConformidade, Date dataInicio, Date dataFim){
        naoConformidadeDao.listarPorIntevaloData(naoConformidade::accept, dataInicio, dataFim);
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
        naoConformidadeDao.listarPorDescricao(resultado::accept,descricao);
    }
     
    public void salvar(NaoConformidade naoConformidade){
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
        formDetalheNaoConformidade = new FormDetalheNaoConformidade((Frame) componentePai, true, this);
        formDetalheNaoConformidade.setLocationRelativeTo(componentePai);
        formDetalheNaoConformidade.listar(id);
        formDetalheNaoConformidade.setVisible(true);
    }
    
    public void exibirImagem(){
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