package model;

import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class NaoConformidade {
    private int id;
    private String abrangencia;
    private String acaoCorrecao;
    private Date dataAcontecimento;
    private Date dataRegistro;
    private String descricao;
    private String imagem;
    private String origem;
    private boolean reincidencia;
    private int idResponsavel;
    private int idSetor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbrangencia() {
        return abrangencia;
    }

    public void setAbrangencia(String abrangencia) {
        this.abrangencia = abrangencia;
    }

    public String getAcaoCorrecao() {
        return acaoCorrecao;
    }

    public void setAcaoCorrecao(String acaoCorrecao) {
        this.acaoCorrecao = acaoCorrecao;
    }

    public Date getDataAcontecimento() {
        return dataAcontecimento;
    }

    public void setDataAcontecimento(Date DataAcontecimento) {
        this.dataAcontecimento = DataAcontecimento;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public boolean isReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(boolean reincidencia) {
        this.reincidencia = reincidencia;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

   
}
