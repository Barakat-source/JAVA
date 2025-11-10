import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class StockImporter {
    
    public static List<Produit> importStock() throws IOException {
        List<Produit> produits = new ArrayList<>();
        double valorisationTotale = 0.0;
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("workspace/data/stock_initial.csv"),
                    StandardCharsets.UTF_8))) {
            
            String line;
            boolean header = true;
            
            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                
                String[] tokens = line.split(";");
                if (tokens.length == 4) {
                    String ref = tokens[0];
                    String designation = tokens[1];
                    double prix = Double.parseDouble(tokens[2]);
                    int qte = Integer.parseInt(tokens[3]);
                    
                    Produit produit = new Produit(ref, designation, prix, qte);
                    produits.add(produit);
                    valorisationTotale += prix * qte;
                }
            }
        }
        
        System.out.println("Nombre de produits importés: " + produits.size());
        System.out.println("Valorisation totale du stock: " + valorisationTotale);
        
        return produits;
    }
}