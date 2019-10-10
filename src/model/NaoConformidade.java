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
    private String codigo;
    private Date DataAcontecimento;
    private Date dataRegistro;
    private String descricao;
    private String imagem;
    private String origem;
    private int reincidencia;
    private String responsavel;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataAcontecimento() {
        return DataAcontecimento;
    }

    public void setDataAcontecimento(Date DataAcontecimento) {
        this.DataAcontecimento = DataAcontecimento;
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

    public int getReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(int reincidencia) {
        this.reincidencia = reincidencia;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int setorIdSetor) {
        this.idSetor = setorIdSetor;
    }

   
}
