import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static final String WORKSPACE_PATH = "workspace";
    
    public static void main(String[] args) {
        try {
            // 1. Organisation disque
            DiskOrganizer.createWorkspace();
            
            // 2. Import du stock
            List<Produit> produits = StockImporter.importStock();
            
            // 3. Journal des ventes
            VenteManager.enregistrerVentes();
            
            // 4. Test encodage UTF-8
            VenteManager.appendVenteUtf8("2025-11-09T11:30;BK001;1;220.00;Algorithmes en Java avec accents éàè");
            
            // 5. Copie binaire
            BinaryManager.copierFichierBinaire();
            
            // 6. Accès direct
            IndexManager.creerIndex(produits);
            long position = IndexManager.seekByRef("BK003");
            System.out.println("Position trouvée pour BK003: " + position);
            
            // 7. Sérialisation
            SerializationManager.serialiserProduits(produits);
            List<Produit> produitsDeserialises = SerializationManager.deserialiserProduits();
            
            // Vérification
            System.out.println("Produits originaux: " + produits.size());
            System.out.println("Produits désérialisés: " + produitsDeserialises.size());
            if (!produits.isEmpty()) {
                System.out.println("Premier produit original: " + produits.get(0));
                System.out.println("Premier produit désérialisé: " + produitsDeserialises.get(0));
            }
            
        } catch (IOException e) {
            Logger.logError("Erreur dans le main", e);
        }
    }
}