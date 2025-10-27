public class ClientFidel extends Client {
    private String codeFidelite;
    private float tauxReduction;

    // Constructeur d'initialisation
    public ClientFidel(int codeClient, String nomClient, String adrClient, 
                      String codeFidelite, float tauxReduction) {
        super(codeClient, nomClient, adrClient);
        this.codeFidelite = codeFidelite;
        this.tauxReduction = tauxReduction;
    }

    // Getters et Setters
    public String getCodeFidelite() {
        return codeFidelite;
    }

    public void setCodeFidelite(String codeFidelite) {
        this.codeFidelite = codeFidelite;
    }

    public float getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(float tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    // Méthode toString redéfinie
    @Override
    public String toString() {
        return "ClientFidel{" +
                "codeClient=" + getCodeClient() +
                ", nomClient='" + getNomClient() + '\'' +
                ", adrClient='" + getAdrClient() + '\'' +
                ", telClient='" + getTelClient() + '\'' +
                ", codeFidelite='" + codeFidelite + '\'' +
                ", tauxReduction=" + tauxReduction +
                ", commandes=" + getListCommandes() +
                '}';
    }
}
