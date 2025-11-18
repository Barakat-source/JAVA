package src;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionUtilisateurs {
    private List<Utilisateur> utilisateurs;

    public GestionUtilisateurs() {
        this.utilisateurs = new ArrayList<>();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public void trierUtilisateurs() {
        Collections.sort(utilisateurs);
    }

    public void afficherUtilisateurs() {
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println(utilisateur.toString());
        }
    }

    public void sauvegarderDansFichier(String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(utilisateurs);
            System.out.println("Utilisateurs sauvegardés avec succès dans " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void chargerDepuisFichier(String nomFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            utilisateurs = (List<Utilisateur>) ois.readObject();
            System.out.println("Utilisateurs chargés avec succès depuis " + nomFichier);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement: " + e.getMessage());
        }
    }

    // Getter pour la liste des utilisateurs
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
}