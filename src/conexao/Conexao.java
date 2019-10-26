
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Eduardo
 */
public class Conexao {
    private final String URL;
    public final String DRIVER = "org.sqlite.JDBC";  

    public Conexao() {
        this.URL = "jdbc:sqlite:"+System.getProperty("user.dir")+"\\banco\\Banco.db";
    }
    
    public Connection abreConexao(){
        try{
            Class.forName(DRIVER);
            SQLiteConfig configuracao = new SQLiteConfig();
            configuracao.enforceForeignKeys(true);
            return DriverManager.getConnection(URL,configuracao.toProperties());
        }
        catch (SQLException sqlex){
            System.out.println("Erro na conexão "+sqlex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Connection abreConexao(String caminhoArquivo){
        try{
            //Class.forName("com.mysql.jdbc.DriverManager");
            return DriverManager.getConnection("jdbc:sqlite:"+caminhoArquivo);
        }
        catch (SQLException sqlex){
            System.out.println("Erro na conexão "+sqlex);
        }
        return null;
    }

    public static void fechaConexao(Connection conexao){
        try{
            conexao.close();
        }
        catch(SQLException sqlex){
            System.out.println("Erro na conexão: "+sqlex);
        }
    }
    
}
