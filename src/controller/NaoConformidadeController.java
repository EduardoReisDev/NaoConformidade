    package controller;

import dao.NaoConformidadeDao;
    import  java.awt.Component ;
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
        if(!naoConformidadeDao.criar(naoConformidade)){
            JOptionPane.showMessageDialog(componentePai, "nao");
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "foi");
        }
    }

    
    
}