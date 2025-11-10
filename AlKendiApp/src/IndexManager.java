import java.io.*;
import java.util.List;

public class IndexManager {
    
    public static void creerIndex(List<Produit> produits) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile("workspace/data/index.dat", "rw")) {
            long position = 0L;
            
            for (Produit produit : produits) {
                raf.writeUTF(produit.getRef());
                raf.writeLong(position);
                position += 100L; // Position fictive pour l'exemple
            }
        }
        System.out.println("Index créé avec " + produits.size() + " produits");
    }
    
    public static long seekByRef(String ref) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile("workspace/data/index.dat", "r")) {
            while (raf.getFilePointer() < raf.length()) {
                String currentRef = raf.readUTF();
                long position = raf.readLong();
                
                if (currentRef.equals(ref)) {
                    return position;
                }
            }
        }
        return -1L; // Non trouvé
    }
}