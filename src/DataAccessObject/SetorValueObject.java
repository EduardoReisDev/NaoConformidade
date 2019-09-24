package DataAccessObject;

/**
 *
 * @author Eduardo
 */
public class SetorValueObject {
    private int idsetor;
    private String setorCodigo;
    private String setorNome;
    private String setorResponsavel;

    public int getIdsetor() {
        return idsetor;
    }

    public void setIdsetor(int idsetor) {
        this.idsetor = idsetor;
    }

    public String getSetorCodigo() {
        return setorCodigo;
    }

    public void setSetorCodigo(String setorCodigo) {
        this.setorCodigo = setorCodigo;
    }

    public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }
}
