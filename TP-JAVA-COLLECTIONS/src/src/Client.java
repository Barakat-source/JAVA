import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client {
    private int codeClient;
    private String nomClient;
    private String adrClient;
    private String telClient;
    private List<Commande> listCommandes;

    // Constructeur d'initialisation
    public Client(int codeClient, String nomClient, String adrClient) {
        this.codeClient = codeClient;
        this.nomClient = nomClient;
        this.adrClient = adrClient;
        this.listCommandes = new ArrayList<>();
    }

    // Getters et Setters
    public int getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getAdrClient() {
        return adrClient;
    }

    public void setAdrClient(String adrClient) {
        this.adrClient = adrClient;
    }

    public String getTelClient() {
        return telClient;
    }

    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }

    public List<Commande> getListCommandes() {
        return listCommandes;
    }

    // Méthode pour enregistrer une commande
    public void enregistrerCommande(Commande commande) {
        this.listCommandes.add(commande);
    }

    // Méthode pour supprimer une commande
    public boolean supprimerCommande(int numCommande) {
        Iterator<Commande> iterator = listCommandes.iterator();
        while (iterator.hasNext()) {
            Commande commande = iterator.next();
            if (commande.getNumCommande() == numCommande) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Méthode toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client{")
          .append("codeClient=").append(codeClient)
          .append(", nomClient='").append(nomClient).append('\'')
          .append(", adrClient='").append(adrClient).append('\'')
          .append(", telClient='").append(telClient).append('\'')
          .append(", commandes=");

        if (listCommandes.isEmpty()) {
            sb.append("Aucune commande");
        } else {
            sb.append(listCommandes);
        }
        sb.append('}');
        return sb.toString();
    }
}
