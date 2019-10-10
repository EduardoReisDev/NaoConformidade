/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import model.Usuario;
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
public class UsuarioDao implements Dao<Usuario>{

    @Override
    public boolean salvar(Usuario dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "insert into usuario ("
                + "nome, cpf, usuario, senha, master) VALUES ( ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getCpf());
            ps.setString(3, dados.getUsuario());
            ps.setString(4, dados.getSenha());
            ps.setBoolean(5, dados.isMaster());
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
    public Usuario listarPorId(int id) {
        Connection conexao = new Conexao().abreConexao();
        Usuario usuario = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM usuario WHERE id = ? ");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setMaster(rs.getBoolean("master"));
            }
            return usuario;
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return usuario;
    }

    @Override
    public boolean editar(Usuario dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "update usuario set nome = ?, usuario = ?, cpf = ?, senha = ?, master = ? where id = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getCpf());
            ps.setString(3, dados.getUsuario());
            ps.setString(4, dados.getSenha());
            ps.setBoolean(5, dados.isMaster());
            ps.setInt(6, dados.getId());
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
        Connection conexao = new Conexao().abreConexao();
        String query = "delete from usuario where id = ?";
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
    public void listarTodos(Consumer<? super Usuario> resultado) {
        String query = "select * from usuario";
        Connection conexao = new Conexao().abreConexao();
        Usuario result;
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                result = new Usuario();
                result.setNome(res.getString("nome"));
                result.setId(res.getInt("id"));
                result.setUsuario(res.getString("usuario"));
                result.setCpf(res.getString("cpf"));
                result.setSenha(res.getString("senha"));
                result.setMaster(res.getBoolean("master"));
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
    
    public boolean existeUsuarios(){
        Connection conexao = new Conexao().abreConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT count(id) as quantidade from usuario");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return rs.getInt("quantidade")>0;
            }
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return false;
    }
    
    public Usuario listarPorCpf(String cpf){
        Connection conexao = new Conexao().abreConexao();
        Usuario usuario = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM usuario WHERE cpf = ? ");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setMaster(rs.getBoolean("master"));
            }
            return usuario;
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return usuario;
    }
    
    public Usuario login (String usuario, String senha){
        Connection conexao = new Conexao().abreConexao();
        Usuario usuarioLogin = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM usuario WHERE usuario = ? and senha= ? ");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                usuarioLogin = new Usuario();
                usuarioLogin.setId(rs.getInt("id"));
                usuarioLogin.setNome(rs.getString("nome"));
                usuarioLogin.setCpf(rs.getString("cpf"));
                usuarioLogin.setUsuario(rs.getString("usuario"));
                usuarioLogin.setSenha(rs.getString("senha"));
                usuarioLogin.setMaster(rs.getBoolean("master"));
            }
            return usuarioLogin;
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return usuarioLogin;
    } 
}
