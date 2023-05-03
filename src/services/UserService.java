/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.DataSource;

/**
 *
 * @author ASUS
 */
public class UserService {
 
    private final Connection cnx;
    private static UserService instance;
    
    public UserService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static UserService getInstance()
    {
        if (instance == null) {
            instance = new UserService();
        }
        return instance; 
    }

    
    
        public boolean Registre(User user) {
        int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `membre`(`nom`,`prenom`,`email`,`role`,`password`,`date_nais`,`photo`,`tel`,`adresse`,`is_active`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement res=cnx.prepareStatement(req);
        
        res.setString(1, user.getNom());
        res.setString(2, user.getPrenom());
        res.setString(3, user.getEmail());
        res.setString(4, user.getRole());
        res.setString(5, user.getMdp());
        res.setDate(6, (Date) user.getDatenai());
        res.setString(7, user.getImage());  
        res.setString(8, user.getTel());
        res.setString(9, user.getAdresse());
        res.setLong(10, user.getIsavtive());
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
        
   public User Login(String email) {

        User user = new User();

        try {
            String requete = "select * from membre where email='"+email+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            int count = 0;
            while(rs.next()){
                count ++;
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setDatenai(rs.getDate("date_nais"));
                user.setMdp(rs.getString("password"));
                user.setTel(rs.getString("tel"));
                user.setAdresse(rs.getString("adresse"));
                user.setImage(rs.getString("photo"));
                user.setIsavtive(rs.getInt("is_active"));

            }
            
            if(count == 0){
             return null ;
            }else{
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
   
           public boolean modifieUser(User user) throws SQLDataException {
               
                String query = "UPDATE `membre` SET `email`=?,`role`=?,`nom`=?,`prenom`=?,`date_nais`=?,`tel`=?,`photo`=?,`adresse`=?,`is_active`=? WHERE `id` = ?";
		PreparedStatement res;
        try {
                res = cnx.prepareStatement(query);
        res.setString(3, user.getNom());
        res.setString(4, user.getPrenom());
        res.setString(1, user.getEmail());
        res.setString(2, user.getRole());
        res.setDate(5, (Date) user.getDatenai());
        res.setString(7, user.getImage());  
        res.setString(6, user.getTel());
        res.setString(8, user.getAdresse());
        res.setInt(9, user.getIsavtive());
        res.setInt(10, user.getId());
                res.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
           
           
    public boolean blouer(int id) throws SQLDataException {
               
                String query = "UPDATE `membre` SET `is_active`=? WHERE `id` = ?";
		PreparedStatement res;
        try {
                res = cnx.prepareStatement(query);

        res.setInt(1,0);
        res.setInt(2, id);
                res.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
        public boolean deblouer(int id) throws SQLDataException {
               
                String query = "UPDATE `membre` SET `is_active`=? WHERE `id` = ?";
		PreparedStatement res;
        try {
                res = cnx.prepareStatement(query);

        res.setInt(1,1);
        res.setInt(2, id);
                res.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
   
   
   
   
   
   
   
   
   
    public boolean checkEmailExist(String email)
    {
        
        int count = 0;
           
        String requete="select * from membre where email='"+email+"' ";
        try{
            Statement st = cnx.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            while(rsl.next())
            {
                count++;
            }
           if(count == 0){
                return false;
           }else{
               return true;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   }
    
    
   public boolean validerEmail(String s){
    Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    Matcher m = p.matcher(s);
    if (m.find() && m.group().equals(s)){
        return false;
    }
    else 
    {
      
        return true;
    
        
}
   }
   
    public List<User> getAllUser(){
        
        List<User> list = new ArrayList<User>();
        int count =0;
        
        String requete="select * from membre";
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setDatenai(rs.getDate("date_nais"));
                user.setMdp(rs.getString("password"));
                user.setTel(rs.getString("tel"));
                user.setAdresse(rs.getString("adresse"));
                user.setImage(rs.getString("photo"));
                user.setIsavtive(rs.getInt("is_active"));
 
                
                list.add(user);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
    
       
    public User findUserById(int id){
        
        User user = new User();
        int count =0;
        
        String requete="select * from membre where id="+id;
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setDatenai(rs.getDate("date_nais"));
                user.setMdp(rs.getString("password"));
                user.setTel(rs.getString("tel"));
                user.setAdresse(rs.getString("adresse"));
                user.setImage(rs.getString("photo"));
                user.setIsavtive(rs.getInt("is_active"));             
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return user;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       
           
        public boolean deleteUser(int id) throws SQLDataException {
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `membre` WHERE `id` ="+id;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }    
        }
        
        
         public User findUserByEmail(String email)
    {
        User user = new User();
        
           
        String requete="select * from membre where email='"+email+"' ";
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next())
            {  
                
               user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setDatenai(rs.getDate("date_nais"));
                user.setMdp(rs.getString("password"));
                user.setTel(rs.getString("tel"));
                user.setAdresse(rs.getString("adresse"));
                user.setImage(rs.getString("photo"));
                user.setIsavtive(rs.getInt("is_active"));  
                
            }
           
               
            
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
   }
        public boolean modifiePassword(User d) throws SQLDataException {
               
                String query = "UPDATE `membre` SET `password`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,d.getMdp());
                st.setInt(2,d.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
        
        
}
