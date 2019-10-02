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
public class UsuarioDataObject implements DataAccessObject<UsuarioValueObject>{

    @Override
    public boolean salvar(UsuarioValueObject dados) {
        Connection conexao = Conexao.abreConexao();
        String query = "insert into usuario ("
                + "usuarioNome, usuarioEmail, usuarioSenha) VALUES ( ?, ?, ?)";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getEmail());
            ps.setString(3, dados.getSenha());
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
    public void listarPorIntervalo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioValueObject listarPorId(UsuarioValueObject value, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(UsuarioValueObject dados) {
        Connection conexao = Conexao.abreConexao();
        String query = "update usuario set usuarioNome = ?, usuarioEmail = ?, usuarioSenha = ? where idusuario = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getEmail());
            ps.setString(3, dados.getSenha());
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
        String query = "delete from usuario where idusuario = ?";
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

    @Override
    public void listarTodos(Consumer<? super UsuarioValueObject> resultado) {
        String query = "select * from usuario";
        Connection conexao = Conexao.abreConexao();
        UsuarioValueObject result;
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                result = new UsuarioValueObject();
                result.setNome(res.getString("usuarioNome"));
                result.setId(res.getInt("idusuario"));
                result.setEmail(res.getString("usuarioEmail"));
                result.setSenha(res.getString("usuarioSenha"));
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
    
   public boolean checkLogin (String usuarioEmail, String usuarioSenha){
       Connection conexao = Conexao.abreConexao();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       boolean check = false;
       
       try{
           stmt = conexao.prepareStatement("SELECT * FROM usuario WHERE usuarioEmail = ? and usuarioSenha= ? ");
           stmt.setString(1, usuarioEmail);
           stmt.setString(2, usuarioSenha);
           
           rs = stmt.executeQuery();
          
           if(rs.next()){
               check = true;
           }
           
       }
       catch (SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
       
       return check;
   } 
}
