package Utils;

import java.sql.* ; 
 
/**
 *
 * @author Cyrine
 */
public class Maconnexion {
      private Connection cnx;
        String url = "jdbc:mysql://localhost:3306/pi";
        String user = "root";
        String pwd = "";
        public static Maconnexion ct;
  
        
    private Maconnexion() {
        try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Cnx etablie ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static Maconnexion getInstance(){
        if(ct ==null)
            ct= new Maconnexion();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
} 
