/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

/**
 *
 * @author leona
 */
public class SetorDataObject implements DataAccessObject<SetorValueObject>{

    @Override
    public boolean salvar(SetorValueObject dados) {
        Connection conexao = Conexao.abreConexao();
        String query = "insert into setor ("
                + "setorCodigo, setorNome, setorResponsavel) VALUES ( ?, ?, ?)";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getCodigo());
            ps.setString(2, dados.getNome());
            ps.setString(3, dados.getResponsavel());
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
    public void listarTodos(Consumer<? super SetorValueObject> resultado) {
        String query = "select * from setor";
        Connection conexao = Conexao.abreConexao();
        SetorValueObject result;
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                result = new SetorValueObject();
                result.setNome(res.getString("setorNome"));
                result.setId(res.getInt("idsetor"));
                result.setCodigo(res.getString("setorCodigo"));
                result.setResponsavel(res.getString("setorResponsavel"));
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
    public SetorValueObject listarPorId(SetorValueObject value, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(SetorValueObject dados) {
        Connection conexao = Conexao.abreConexao();
        String query = "update setor set setorNome = ?, setorCodigo = ?, setorResponsavel = ? where idsetor = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getCodigo());
            ps.setString(3, dados.getResponsavel());
            ps.setInt(4, dados.getId());
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

    @Override
    public boolean excluir(int id) {
        Connection conexao = Conexao.abreConexao();
        String query = "delete from setor where idsetor = ?";
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
    
}
