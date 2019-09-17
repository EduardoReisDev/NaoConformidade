package modal.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modal.bean.Naoconformidade;

/**
 *
 * @author Eduardo
 */
public class NaoconformidadeDAO {
    
    public void create(Naoconformidade a){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO contatos (nomeContato, telefoneContato, celularContato, emailContato, categoria) VALUES (?, ?, ? ,?, ?)");
            stmt.setString(1, a.getNomeContato());
            stmt.setString(2, a.getTelefoneContato());
            stmt.setString(3, a.getCelularContato());
            stmt.setString(4, a.getEmailContato());
            stmt.setString(5, a.getCategoria());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Naoconformidade> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs= null;
        
        List<Naoconformidade> contatos = new ArrayList<>();
        
        
        try{
            stmt = con.prepareStatement("SELECT * FROM contatos");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Naoconformidade naoconformidade = new Naoconformidade();
                agenda.setIdContato(rs.getInt("idContato"));
                agenda.setNomeContato(rs.getString("nomeContato"));
                agenda.setTelefoneContato(rs.getString("telefoneContato"));
                agenda.setCelularContato(rs.getString("celularContato"));
                agenda.setEmailContato(rs.getString("emailContato"));
                agenda.setCategoria(rs.getString("categoria"));
                contatos.add(agenda);
            }
            
        }catch(SQLException ex){
        
              }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return contatos;
    }
    
        public void update(Naoconformidade a){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("UPDATE contatos SET nomeContato = ?, telefoneContato = ?, celularContato = ?, emailContato = ?, categoria = ? WHERE idContato = ?");
                stmt.setString(1, a.getNomeContato());
                stmt.setString(2, a.getTelefoneContato());
                stmt.setString(3, a.getCelularContato());
                stmt.setString(4, a.getEmailContato());
                stmt.setString(5, a.getCategoria());
                stmt.setInt(6, a.getIdContato());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
    }
        
        public void delete(Naoconformidade a){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("DELETE FROM contatos WHERE idContato = ?");
                stmt.setInt(1, a.getIdContato());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }  
    }
        
        public List<Naoconformidade> readForDesc(String desc){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs= null;

            List<Naoconformidade> contatos = new ArrayList<>();


            try{
                stmt = con.prepareStatement("SELECT * FROM contatos WHERE nomeContato LIKE ?");
                stmt.setString(1, "%"+desc+"%");
                rs = stmt.executeQuery();

                while (rs.next()){
                    Naoconformidade naoconformidade = new Naoconformidade();
                    agenda.setIdContato(rs.getInt("idContato"));
                    agenda.setNomeContato(rs.getString("nomeContato"));
                    agenda.setTelefoneContato(rs.getString("telefoneContato"));
                    agenda.setCelularContato(rs.getString("celularContato"));
                    agenda.setCategoria(rs.getString("categoria"));
                    contatos.add(agenda);
                }

            }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Erro ao Buscar: "+ex);
                  }
            finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }

            return contatos;
    }
}
