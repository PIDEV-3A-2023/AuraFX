package entites;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Don {

    private SimpleIntegerProperty id;
    private SimpleDoubleProperty montant;
    private LocalDateTime date;
    private SimpleStringProperty carte;
    private SimpleStringProperty message;
    private SimpleStringProperty email;

    public Don(int id, double montant, LocalDateTime date, String carte, String message, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.montant = new SimpleDoubleProperty(montant);
        this.date = date;
        this.carte = new SimpleStringProperty(carte);
        this.message = new SimpleStringProperty(message);
        this.email = new SimpleStringProperty(email);
    }

    public Don() {
        this(0, 0.0, null, null, null, null);
    }

    public Don(double montant, LocalDateTime date, String carte, String message, String email) {
        this(0, montant, date, carte, message, email);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public double getMontant() {
        return montant.get();
    }

    public void setMontant(double montant) {
        this.montant.set(montant);
    }

    public SimpleDoubleProperty montantProperty() {
        return montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public SimpleStringProperty carteProperty() {
        return carte;
    }

    public String getCarte() {
        return carte.get();
    }

    public void setCarte(String carte) {
        this.carte.set(carte);
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }

    public String getMessage() {
        return message.get();
    }

    public void setMessage(String message) {
        this.message.set(message);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return "Don{" + "id=" + id.get() + ", montant=" + montant.get() + ", date=" + date + ", carte=" + carte.get() + ", message=" + message.get() + ", email=" + email.get() + '}';
    }
}
