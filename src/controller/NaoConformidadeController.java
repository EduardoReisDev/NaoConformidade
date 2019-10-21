    package controller;

    import  java.awt.Component ;
    import  view.Mensagens ;
    import view.naoconformidade.CadatroNaoCoformidade;

    public class NaoConformidadeController {
    Component componentePai;

    public  Component  getComponentePai () {
        return componentePai;
    }

    public  void  setComponentePai ( Component  componentePai ) {
        this.componentePai = componentePai;
    }
    
    
     public boolean validarTexto(String texto) { 
        return texto.equals("");
    }
    
    public  void  obrigatorio( Component  componentePai ) {
        Mensagens.mensagem(componentePai," Preencha este campo! "," ATENÇÃO! ", 2 );
    }

    public static void  cadastrar () {
        CadatroNaoCoformidade cadatroNaoCoformidade;
        cadatroNaoCoformidade = new CadatroNaoCoformidade(null, true);
        cadatroNaoCoformidade.setLocationRelativeTo(null);
        cadatroNaoCoformidade.setVisible(true);
    }

    
    
}