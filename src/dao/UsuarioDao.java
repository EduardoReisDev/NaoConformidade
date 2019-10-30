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
public class UsuarioDao implements Crud<Usuario>, UsuarioDaoInterface{

    @Override
    public boolean criar(Usuario dados) {
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
    public Usuario listarPorId(int id) {
        Connection conexao = new Conexao().abreConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM usuario WHERE id = ? ");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("usuario"), 
                        rs.getString("senha"), 
                        rs.getBoolean("master")
                );
            }
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return null;
    }

    @Override
    public boolean editar(Usuario dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "update usuario set nome = ?, usuario = ?, cpf = ?, senha = ?, master = ? where id = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getUsuario());
            ps.setString(3, dados.getCpf());
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
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                resultado.accept(new Usuario(
                        res.getInt("id"),
                        res.getString("nome"),
                        res.getString("cpf"),
                        res.getString("usuario"), 
                        res.getString("senha"), 
                        res.getBoolean("master")
                ));
            }
        }
        catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }
    
    public void lerPorNome(Consumer<? super Usuario> resultado, String nome) {
        String query = "SELECT * FROM usuario WHERE nome like ?";
        Connection conexao = new Conexao().abreConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, nome+"%");
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                resultado.accept(new Usuario(
                        res.getInt("id"),
                        res.getString("nome"),
                        res.getString("cpf"),
                        res.getString("usuario"), 
                        res.getString("senha"), 
                        res.getBoolean("master")
                ));
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
    public boolean existeUsuariosMasters(){
        Connection conexao = new Conexao().abreConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT count(id) as quantidade from usuario where master = true");
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
    
    @Override
    public Usuario listarPorCpf(String cpf){
        Connection conexao = new Conexao().abreConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM usuario WHERE cpf = ? ");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("usuario"), 
                        rs.getString("senha"), 
                        rs.getBoolean("master")
                );
            }
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return null;
    }
    
    @Override
    public Usuario login (String usuario, String senha){
        Connection conexao = new Conexao().abreConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM usuario WHERE usuario = ? and senha= ? ");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("usuario"), 
                        rs.getString("senha"), 
                        rs.getBoolean("master")
                );
            }
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return null;
    } 
    
    @Override
    public int getLastId() {
        String query = "select max(id) as maxId from usuario;";
        Connection conexao = new Conexao().abreConexao();
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
