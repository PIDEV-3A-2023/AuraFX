package entites;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Association {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty adresse;
    private SimpleStringProperty email;
    private SimpleStringProperty rib;

    public Association(int id, String nom, String adresse, String email, String rib) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.adresse = new SimpleStringProperty(adresse);
        this.email = new SimpleStringProperty(email);
        this.rib = new SimpleStringProperty(rib);
    }

    public Association() {
        this(0, null, null, null, null);
    }

    public Association(String nom, String adresse, String email, String rib) {
        this(0, nom, adresse, email, rib);
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

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public SimpleStringProperty adresseProperty() {
        return adresse;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getRib() {
        return rib.get();
    }

    public void setRib(String rib) {
        this.rib.set(rib);
    }

    public SimpleStringProperty ribProperty() {
        return rib;
    }

    @Override
    public String toString() {
        return "Association{" + "id=" + id.get() + ", nom=" + nom.get() + ", adresse=" + adresse.get() + ", email=" + email.get() + ", rib=" + rib.get() + '}';
    }
}
