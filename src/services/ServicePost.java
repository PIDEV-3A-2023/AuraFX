/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Comment;
import entities.membre;
import entities.Post;
import entities.Rating;
import entities.User;
import utils.MaConnection;
//import com.mysql.cj.xdevapi.Statement;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Sessions;

/**
 *
 * @author MSI
 */
public class ServicePost implements InterfacePost{
    
    Connection cnx;
    
     public ServicePost() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void addPost(Post p) {
        try {
            PreparedStatement stm = cnx.prepareStatement("insert into post (theme, nom, contenu, image,date_Creation) values (?,?,?,?,?)");
            
          
            stm.setString(1, p.getTheme());
            stm.setString(2, p.getNom());
            stm.setString(3, p.getContenu());
            stm.setString(4, p.getImage());
           stm.setDate(5, new java.sql.Date(p.getDate_Creation().getTime()) );
                       
            stm.executeUpdate();
            
            System.out.println("Post ajouté avec succés!!!");
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void addPost1(Post p) {
        try {
            PreparedStatement stm = cnx.prepareStatement("insert into post (theme, nom, contenu, image,date_Creation) values (?,?,?,?,?)");
            
          
            stm.setString(1, p.getTheme());
            stm.setString(2, p.getNom());
            stm.setString(3, p.getContenu());
            stm.setString(4, p.getImage());
           stm.setDate(5, new java.sql.Date(p.getDate_Creation().getTime()) );
                       
            stm.executeUpdate();
            
            System.out.println("Post ajouté avec succés!!!");
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void deletePost(int id) {   
        
     try {
         Statement st = cnx.createStatement();
         String req = "DELETE FROM commentaire WHERE post_id = " + id; // delete associated comments first
         st.executeUpdate(req);

         String req2 = "DELETE FROM rating WHERE post_id = " + id; // delete associated rates first
         st.executeUpdate(req2);

         req = "DELETE FROM post WHERE id = " + id; // delete post
         st.executeUpdate(req);
         System.out.println("Post deleted successfully.");
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());        
     }
    
} 


    @Override
    public void updatePost(Post p) {
        
        try {
            String req = "update post set theme=?,nom=?,image=?,contenu=?,date_Creation=? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, p.getTheme());
            ps.setString(2, p.getNom());
            ps.setString(3, p.getImage());
            ps.setString(4, p.getContenu());
           ps.setDate(5, new java.sql.Date(p.getDate_Creation().getTime()) );
            ps.setInt(6, (int) p.getId());
          
            ps.executeUpdate();
            System.out.println("Post modifié avec succés!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public ObservableList<Post> displayPosts() {
       ObservableList<Post> posts = FXCollections.observableArrayList();
       try {
            String req = "select * from post ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setTheme(rs.getString("theme"));
                p.setImage(rs.getString("image"));
                p.setContenu(rs.getString("Contenu"));
                p.setNbr_Vue(rs.getInt("nbr_vue"));
                p.setDate_Creation(rs.getDate("date_Creation"));
                p.setNom(rs.getString("nom"));
                posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
        
    }
      @Override
     public int nbrComments (int id){
        List<Comment> cmnts = new ArrayList<>();
        try {
            String req = "select * from commentaire where post_id = " +id+"";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
              Comment c = new Comment();
                c.setId(rs.getInt(1));
                Post post = new Post();
                post.setId(rs.getInt(2));
                c.setPost(post);
                User membre = new User();
                membre.setId(rs.getInt(3));
                c.setMembre(membre);
                c.setText(rs.getString("Text"));
            
                c.setDate(rs.getDate("Date"));
               
                cmnts.add(c);
    
            }
            System.out.print(cmnts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cmnts.size(); 
    }
     public membre getMemberById (int id){
         membre m = null;
        try {
            String req = "select * from membre where id = " +id+"";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            m = new membre();
                m.setId(rs.getInt(1));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setMail(rs.getString("email"));
               /* m.setPassword(rs.getString("password"));
                m.setTel(rs.getString("tel"));
                m.setAdresse(rs.getString("adresse"));
               m.setRole(rs.getString("role"));
               m.setDateNaiss(rs.getDate("date_nais"));*/
               
    
            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return m; 
    }
       @Override
       public void addRate(int idp,int idm,int rate,java.util.Date date) {
       
        try {
            PreparedStatement stm = cnx.prepareStatement("insert into rating (post_id,membre_id,rate,created_at) values (?,?,?,?)");
  
            stm.setInt(1, idp);
            stm.setInt(2, idm);
            stm.setInt(3, rate);
           stm.setDate(4, new java.sql.Date(date.getTime()) );
                       
            stm.executeUpdate();
            
           
            System.out.println("rate ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
     }
         @Override
        public void deleteRate(int idp, int idm ) {
        try
        { 
          Statement st = cnx.createStatement();
          String req = "DELETE FROM rating WHERE post_id = '"+idp+"' and membre_id= '"+idm+"';";
                    st.executeUpdate(req);      
          System.out.println("Rate supprimer avec succès...");
        } catch (SQLException ex) {
                    System.out.println(ex.getMessage());        
                  }
        }
        
          @Override
        public List<Rating> rates (int id){
        List<Rating> rates = new ArrayList<>();
        try {
            String req = "select * from rating where post_id =" +id+";";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Rating r = new Rating();
                r.setId(rs.getInt("id"));
                Post post = new Post();
                post.setId(rs.getInt(2));
                r.setPost(post);
               User membre = new User();
                membre.setId(rs.getInt(3));
                r.setMembre(membre);
                r.setRate(rs.getInt("rate"));
                r.setCreated_at(rs.getDate("created_at"));
                rates.add(r);
            }
            //System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rates ; 
    }
        
          @Override
        public List<Rating> isRatedByUser(int idp,int idm) {
        
        List<Rating> rates = new ArrayList<>();
        try {
            String req = "Select * from rating where membre_id= '"+idm+ "'and post_id ='"+idp+"';";
          
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Rating r = new Rating();
                r.setId(rs.getInt("id"));
                 Post post = new Post();
                post.setId(rs.getInt(2));
                r.setPost(post);
                User membre = new User();
                membre.setId(rs.getInt(3));
                r.setMembre(membre);
                r.setRate(rs.getInt("rate"));
                r.setCreated_at(rs.getDate("created_at"));
                
                rates.add(r);
            }
            System.out.println(rates);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return rates;
    }
        
          @Override
        public int getLikeCount(int postId) {
            String query = "SELECT COUNT(*) FROM rating WHERE post_id = ? AND rate = 1";
            try (
                 PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setInt(1, postId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return 0;
        }

        
    @Override
        public int getDislikeCount(int postId) {
            String query = "SELECT COUNT(*) FROM rating WHERE post_id = ? AND rate = -1";
            try (
                 PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setInt(1, postId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return 0;
        }

    @Override
    public List<Post> searchByTitle(String query) {
    List<Post> searchResults = new ArrayList<>();
    try {
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM post WHERE LOWER(nom) LIKE ?");
        ps.setString(1, "%" + query.toLowerCase() + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Post p = new Post();
                p.setId(rs.getInt(1));
                p.setTheme(rs.getString("theme"));
                p.setImage(rs.getString("image"));
                p.setContenu(rs.getString("Contenu"));
                p.setNbr_Vue(rs.getInt("nbr_vue"));
                p.setDate_Creation(rs.getDate("date_Creation"));
                p.setNom(rs.getString("nom"));
            searchResults.add(p);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return searchResults;
}

    
}
