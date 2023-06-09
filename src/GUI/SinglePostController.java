package GUI;

import entities.Comment;
import entities.membre;
import entities.Post;
import entities.Rating;
import services.ServiceComment;
import services.ServicePost;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import entities.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.UserService;
import services.panier;
import utils.Sessions;



public class SinglePostController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Label contentLabel;
    private UserService us;

    private Post post;
    
    private User membre;
    @FXML
    private Label dateLabel;
    @FXML
    private TextArea commentTextArea;
    @FXML
    private Button commentButton;
    @FXML
    private VBox commentSection;

    private ServiceComment sc;
    private ServicePost sp;
    @FXML
    private Label themeLabel;
    @FXML
    private Label commentsLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button likeButton;
    @FXML
    private Button dislikeButton;
    @FXML
    private Label likeLabel;
    @FXML
    private Label dislikeLabel;
    
    private int likeCount = 0;
    private int dislikeCount = 0;
    
    private Map<Integer, Integer> membreBadWordCounts = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.sc = new ServiceComment();
        this.sp = new ServicePost();
        
     //  this.membre = panier.getInstance();
     

    }

    public void setPost(Post post) {
        this.post = post;
        displayPost();
        displayComments(); 
        displayRates();
       
    }

    private void displayPost() {
        titleLabel.setText(post.getNom());
        themeLabel.setText(post.getTheme());
        if (post.getImage() != null) {
            imageView.setImage(new Image(new File(post.getImage()).toURI().toString()));
        }
        contentLabel.setText(post.getContenu());
        dateLabel.setText(post.getDate_Creation().toString());
    }

    private void displayComments() {
        commentSection.getChildren().clear();
        List<Comment> comments = sc.displayComments();
        
        for (Comment comment : comments) {
            if (comment.getPost().getId() == post.getId()) {
                 Label commentLabel = new Label(comment.getText());
                 commentLabel.setStyle("-fx-font-family: 'Open Sans', sans-serif; -fx-font-size: 13pt; -fx-text-fill: #222222;");
            VBox commentBox = new VBox(commentLabel);
            commentBox.setSpacing(5);
               commentSection.getChildren().add(commentBox);
               System.out.println(panier.getInstance().getId());
                if(comment.getMembre().getId()==panier.getInstance().getId()){
                Button deleteButton = new Button("DELETE");
               deleteButton.setStyle("-fx-background-color: red; -fx-font-family: 'Arial'; -fx-font-size: 12pt; -fx-text-fill: white;");

            deleteButton.setOnAction(e -> {
                
                
        Dialog<ButtonType> confirmationDialog = new Dialog<>();
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Are you sure to delete this comment?");

        // set the graphic for the confirmation dialog window
        Stage stage = (Stage) confirmationDialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/image/logo.png"));

        ButtonType confirmButton = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationDialog.getDialogPane().getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            // delete the comment
           sc.deleteComment(comment.getId());
       //    this.post.removeComment(comment);
            displayComments();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setContentText("Comment deleted successfully");
              alert.showAndWait();  
              
        }
            });
            
                 Button updateButton = new Button("UPDATE");
                 updateButton.setStyle("-fx-background-color: #17a2b8; -fx-font-family: 'Arial'; -fx-font-size: 12pt; -fx-text-fill: white;");
            updateButton.setOnAction(e -> {
                TextArea editCommentArea = new TextArea(comment.getText());
                Button saveButton = new Button("Save");
                Button cancelButton = new Button("Cancel");
                HBox buttonBox = new HBox(saveButton, cancelButton);
                buttonBox.setSpacing(5);
                VBox editBox = new VBox(editCommentArea, buttonBox);
                editBox.setSpacing(10);
                Stage editStage = new Stage();
                editStage.setScene(new Scene(editBox));
                editStage.getIcons().add(new Image("/image/logo.png"));
                editStage.setTitle("Update your comment");
                editStage.show();
                cancelButton.setOnAction(event -> editStage.close());
                saveButton.setOnAction(event -> {
                    String updatedComment = editCommentArea.getText();
                    if (updatedComment.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Comment cannot be empty");
                        alert.showAndWait();
                    } else {
                        
                        // Send HTTP request to Neutrino API to check for bad words
                     try {
                        URL url = new URL("https://neutrinoapi.net/bad-word-filter");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("User-ID", "leoo10");
                        connection.setRequestProperty("API-Key", "HA5e6GzK9rJBTnI1y3Tk70Y9tdmbptkR8NOr6Oa70412SZHR");
                        
                        // Send request content
                       connection.setDoOutput(true);
                       OutputStream os = connection.getOutputStream();
                       os.write(("content=" + updatedComment).getBytes());
                       os.flush();
                       os.close();
                       
                        // Read API response
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        // Check if content contains bad words
                        if (response.toString().contains("true")) {
                            // Handle bad word found
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Your comment contains inappropriate language and cannot be posted.");
                            alert.showAndWait();
                             // Increment the count of comments containing bad words for this member
                             incrementBadWordCountForMembre();
                        }
                        else{
                        comment.setText(updatedComment);
                        sc.updateComment(comment);
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Comment updated successfully");
                         alert.showAndWait();
                        editStage.close();
                        displayComments();
                    }
                         } catch (IOException ex) {
        System.out.println("Error checking for bad words: " + ex.getMessage());
    }                   catch (SQLDataException ex) {
                            Logger.getLogger(SinglePostController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });
              HBox buttonBox = new HBox(deleteButton, updateButton);
            buttonBox.setSpacing(5);
                 commentSection.getChildren().add(buttonBox);
        }
        
            }
        }
    }

    @FXML
private void addComment() throws SQLDataException {
String content = commentTextArea.getText();


if (content.isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Comment cannot be empty");
    alert.showAndWait();
} else {
    // Send HTTP request to Neutrino API to check for bad words
    try {
        URL url = new URL("https://neutrinoapi.net/bad-word-filter");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-ID", "leoo10");
        connection.setRequestProperty("API-Key", "HA5e6GzK9rJBTnI1y3Tk70Y9tdmbptkR8NOr6Oa70412SZHR");

        // Send request content
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(("content=" + content).getBytes());
        os.flush();
        os.close();

        // Read API response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Check if content contains bad words
        if (response.toString().contains("true")) {
            // Handle bad word found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Your comment contains inappropriate language and cannot be posted.");
            alert.showAndWait();
            // Increment the count of comments containing bad words for this member
            incrementBadWordCountForMembre();
            
            
        } else {
            // Add comment to list of comments
            Comment c = new Comment();
            c.setText(content);
            c.setDate(new Date());
            c.setPost(this.post);

            // this.post.addComment(c);
           // this.membre.setId(1);
           User u =new User(panier.getInstance().getId());
            c.setMembre(/*panier.getInstance().getU()*/u);
            
            sc.addComment(c);
            commentTextArea.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Comment saved successfully");
            alert.showAndWait();
            displayComments();
        }
    } catch (IOException e) {
        System.out.println("Error checking for bad words: " + e.getMessage());
    }
}
}

    private void sendWarningEmail(String toEmail) {
    String subject = "Warning: Inappropriate language detected in your comments";
    String body = "Hello "+this.membre.getPrenom()+", your comments have been flagged for containing inappropriate language. Please refrain from using inappropriate language in your comments.";
    sendEmail(toEmail, subject, body);
}
    
   private void sendEmail(String toEmail, String subject, String body) {
    // Créer une propriété pour configurer l'envoi de l'email
        Properties properties = new Properties();
       /* props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
       props.put("mail.smtp.ssl.trust", "*");*/
       properties.put("com.hof.email.starttime","20170519094544");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.connectiontimeout","60000");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.timeout","60000");
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
    
    
    // Créer une session avec l'adresse e-mail et le mot de passe de l'expéditeur
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ayed.youssef@esprit.tn", "14017399");
            }
        });
    
    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("ayed.youssef@esprit.tn"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}


    private void incrementBadWordCountForMembre() throws SQLDataException {
    this.us = new UserService();
    AcceuilAdminController ad = new AcceuilAdminController();
    int badWordCount = 0;
    this.membre=panier.getInstance().getU();
    if (membreBadWordCounts.containsKey(panier.getInstance().getId())) {
        badWordCount = membreBadWordCounts.get(panier.getInstance().getId());
    }
    badWordCount++;
    membreBadWordCounts.put(panier.getInstance().getId(), badWordCount);
    
    // Check if member should be blocked
    if (badWordCount >= 4) {
       // blockMembre();
      us.blouer(panier.getInstance().getId());
      try {
          Parent  root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage myWindow = (Stage) commentSection.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("back ");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      
      // Show alert
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("You have been blocked from posting comments due to repeated use of inappropriate language.");
        alert.showAndWait();

        sendEmail(this.membre.getEmail(), "You have been blocked from posting comments", "Oops.."+this.membre.getPrenom()+", You have been blocked from posting comments due to repeated use of inappropriate language.");
    } else if (badWordCount == 2) {
      
    System.out.println(membre.getEmail()); 
        sendWarningEmail(this.membre.getEmail());
    }
}

    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("baseFront.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Post Front");
                    stage.setScene(scene);
                 
                    stage.show(); 
    }
    
   private void displayRates() {
    List<Rating> rates = sp.isRatedByUser(post.getId(), panier.getInstance().getId());
   // List<Rating> allRates = sp.rates(post.getId());
    boolean hasRated = false;
    
    for (Rating rate : rates) {
        if (rate.getRate() == 1) {
            likeButton.setStyle("-fx-background-color: blue;");
            dislikeButton.setDisable(true);
            hasRated = true;
        } else {
            dislikeButton.setStyle("-fx-background-color: blue;");
            likeButton.setDisable(true);
            hasRated = true;
        }
    }
    if (!hasRated) {
        likeButton.setStyle("");
        dislikeButton.setStyle("");
    }  
    likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
    dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
}

    
@FXML
private void rater(ActionEvent event) {
    Date now = new Date();
    List<Rating> rates = sp.isRatedByUser(post.getId(), panier.getInstance().getId());
    boolean hasRated = false;
    for (Rating rate : rates) {
        if (rate.getRate() == 1 && event.getSource() == likeButton) {
            sp.deleteRate(post.getId(), panier.getInstance().getId());
            likeButton.setStyle("");
            dislikeButton.setDisable(false);
            displayRates();
            hasRated = true;
        } else if (rate.getRate() == -1 && event.getSource() == dislikeButton) {
            sp.deleteRate(post.getId(), panier.getInstance().getId());
            dislikeButton.setStyle("");
            likeButton.setDisable(false);
            displayRates();
            hasRated = true;
        }
    }

    if (!hasRated) {
        if (event.getSource() == likeButton) {
            sp.addRate(post.getId(), panier.getInstance().getId(), 1, now);
            likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
            dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
            likeButton.setStyle("-fx-background-color: blue;");
            dislikeButton.setDisable(true);
            likeButton.setOnAction(e -> {
                sp.deleteRate(post.getId(), panier.getInstance().getId());
                likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
                dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
                likeButton.setStyle("");
                dislikeButton.setDisable(false);
                likeButton.setOnAction(this::rater);
                displayRates();
            });
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thanks for rating this post.");
            alert.showAndWait();
        } else if (event.getSource() == dislikeButton) {
            sp.addRate(post.getId(), panier.getInstance().getId(), -1, now);
            likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
            dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
            dislikeButton.setStyle("-fx-background-color: blue;");
            likeButton.setDisable(true);
            dislikeButton.setOnAction(e -> {
                sp.deleteRate(post.getId(), panier.getInstance().getId());
                likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
                dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
                dislikeButton.setStyle("");
                likeButton.setDisable(false);
                dislikeButton.setOnAction(this::rater);
                displayRates();
            });
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thanks for rating this post.");
            alert.showAndWait();
        }
    }
}

   
    
}
