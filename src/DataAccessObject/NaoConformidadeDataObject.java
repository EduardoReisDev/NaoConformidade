/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

/**
 *
 * @author leona
 */
public class NaoConformidadeDataObject implements DataAccessObject<NaoConformidadeValueObject>{

    @Override
    public boolean salvar(NaoConformidadeValueObject dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "insert into nc ("
                + "ncCodigo, ncDescricao, ncDataregistro, ncDataacontecimento, ncReincidencia, ncAbrangencia, "
                + "ncOrigem, ncResponsavel, ncAcaocorrecao, ncImagem, setor_idsetor) VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getCodigo());
            ps.setString(2, dados.getDescricao());
            ps.setDate(3, new Date(dados.getDataAcontecimento().getYear(), dados.getDataAcontecimento().getMonth() , dados.getDataAcontecimento().getDay()));
            ps.setDate(4, new Date(dados.getDataRegistro().getYear(), dados.getDataRegistro().getMonth() , dados.getDataRegistro().getDay()));
            ps.setInt(5, dados.getReincidencia());
            ps.setString(6, dados.getAbrangencia());
            ps.setString(7, dados.getOrigem());
            ps.setString(8, dados.getResponsavel());
            ps.setString(9, dados.getAcaoCorrecao());
            ps.setString(10, dados.getImagem());
            ps.setInt(11, dados.getIdSetor());
            return ps.executeUpdate()>0;
        }
        catch(SQLException sqlex){
            System.out.println("erro na inserção "+sqlex.getMessage());
            return false;
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public void listarTodos(Consumer<? super NaoConformidadeValueObject> resultado) {
        String query = "select * from nc ";
        Connection conexao = new Conexao().abreConexao();
        NaoConformidadeValueObject result;
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                result = new NaoConformidadeValueObject();
                result.setId(res.getInt("idnc"));
                result.setDescricao(res.getString("ncDescricao"));
                //result.set
                result.setResponsavel(res.getString("ncResponsavel"));
                
                resultado.accept(result);
                
            }
        }
        catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public void listarPorIntervalo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NaoConformidadeValueObject listarPorId( int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(NaoConformidadeValueObject dados) {
    Connection conexao = new Conexao().abreConexao();
        String query = "update nc set ncCodigo = ?, ncDescricao = ?, ncDataregistro = ?, ncDataacontecimento = ?, ncReincidencia = ?, ncAbrangencia = ?, "
                + "ncOrigem = ?, ncResponsavel = ?, ncAcaocorrecao = ?, ncImagem = ?, setor_idsetor = ? where idnc = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getCodigo());
            ps.setString(2, dados.getDescricao());
            ps.setDate(3, new Date(dados.getDataRegistro().getYear(), dados.getDataRegistro().getMonth() , dados.getDataRegistro().getDay()));
            ps.setDate(4, new Date(dados.getDataAcontecimento().getYear(), dados.getDataAcontecimento().getMonth() , dados.getDataAcontecimento().getDay()));
            ps.setInt(5, dados.getReincidencia());
            ps.setString(6, dados.getAbrangencia());
            ps.setString(7, dados.getOrigem());
            ps.setString(8, dados.getResponsavel());
            ps.setString(9, dados.getAcaoCorrecao());
            ps.setString(10, dados.getImagem());
            ps.setInt(11, dados.getIdSetor());
            ps.setInt(12, dados.getId());
            
            return ps.executeUpdate()>0;
        }
        catch(SQLException e){
            System.out.println("erro na alteração "+e.getMessage());
            return false;
        }
        finally{
            Conexao.fechaConexao(conexao);
        }    
    }

    @Override
    public boolean excluir(int id) {
        Connection conexao = new Conexao().abreConexao();
        String query = "delete from nc where idnc = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate()>0;
        }
        catch(SQLException e){
            System.out.println("erro na exclusao "+e.getMessage());
            return false;
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }
    
    public int listarUltimoId(){
        String query = "select max(idnc) as maxId from nc;";
        Connection conexao = new Conexao().abreConexao();
        NaoConformidadeValueObject result;
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                return res.getInt("maxId");
                
            }
        }
        catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return 0; 
    }
    
}
