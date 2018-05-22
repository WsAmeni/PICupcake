package Entities;

/**
 *
 * @author AMANI
 */
public class Commande {
    private int id;
    private int id_client;
    private String libellé; 
    private String description;
    private float prix;
    private String id_patisserie;
    private String type_produit;
    private int quantité;

    public Commande(int id, String libellé, String description, float prix, String id_patisserie, String type_produit, int quantité) {
        this.id = id;
        this.id_client=id_client;
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.id_patisserie = id_patisserie;
        this.type_produit = type_produit;
        this.quantité = quantité;
    }

    public Commande(String libellé, String description, float prix, String id_patisserie, int quantité) {
        this.libellé = libellé;
        this.description = description;
        this.prix = prix;
        this.id_patisserie = id_patisserie;
        this.quantité = quantité;
    }

    
    public Commande() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    
    

    public String getLibellé() {
        return libellé;
    }

    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(String id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

    public String getType_produit() {
        return type_produit;
    }

    public void setType_produit(String type_produit) {
        this.type_produit = type_produit;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }
  
    

   
    
    
    
    
    
    
    
}
