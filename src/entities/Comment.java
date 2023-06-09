/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Comment {
    private int id;
    private Post post;
    private User membre;
    private String text;
    private Date date;

    public Comment(int id, Post Post, User membre, String text, Date date) {
        this.id = id;
        this.post = Post;
        this.membre = membre;
        this.text = text;
        this.date = date;
    }

    public Comment(Post Post, User membre, String text, Date date) {
        this.post = Post;
        this.membre = membre;
        this.text = text;
        this.date = date;
    }

    public Comment(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Comment(String text) {
        this.text = text;
    }

    
    public Comment(){
        
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", post=" + post + ", membre=" + membre + ", text=" + text + ", date=" + date + '}';
    }
    
   

    public void setId(int id) {
        this.id = id;
    }

    

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

    public int getId() {
        return id;
    }

   

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getMembre() {
        return membre;
    }

    public void setMembre(User membre) {
        this.membre = membre;
    }
    
}
