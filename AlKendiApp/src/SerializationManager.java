import java.io.*;
import java.util.*;

public class SerializationManager {
    
    @SuppressWarnings("unchecked")
    public static List<Produit> deserialiserProduits() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("workspace/data/produits.ser"))) {
            
            return (List<Produit>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Erreur lors de la désérialisation", e);
        }
    }
    
    public static void serialiserProduits(List<Produit> produits) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("workspace/data/produits.ser"))) {
            
            oos.writeObject(produits);
        }
        System.out.println(produits.size() + " produits sérialisés");
    }
}