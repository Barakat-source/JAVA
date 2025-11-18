package src;
import java.io.Serializable;
import java.util.Date;

public class Utilisateur implements Serializable, Comparable<Utilisateur> {
    private int idUser;
    private String nomComplet, email;
    private Date dateNaissance;
    private static int nbUsers = 0;

    public Utilisateur(String nomComplet, String email, Date dateNaissance) {
        this.idUser = incrUser();
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        
        if (verifEmail(email) != null) {
            this.email = email;
        } else {
            System.out.println("Attention email incorrect : email non ajouté");
        }
    }

    public static int incrUser() {
        nbUsers++;
        return nbUsers;
    }

    public static int countUsers() {
        return nbUsers;
    }

    public String verifEmail(String email) {
        if (email.contains("@bts-kendi.ma")) {
            return email;
        } else {
            System.out.println("Attention email incorrect : email non ajouté");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Nom: " + nomComplet + ", Email: " + email;
    }

    @Override
    public int compareTo(Utilisateur autre) {
        return this.nomComplet.compareTo(autre.nomComplet);
    }

    // Getters et Setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}