/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author user16
 */
public class Recette {
   
    private int id;
   
    private String libelle;
    private String duréé;
    private String diffic;
    private String ingd; 
    private String description;
    private SimpleStringProperty image=new SimpleStringProperty();   
    private BufferedImage bImage;
    private Long date; 
    private int id_client;

    public Recette(int id, String ingd, String description) {
        this.id = id;
        this.ingd = ingd;
        this.description = description;
    }

    
    
    
    public Recette(String libelle, String duréé, String diffic, String ingd, String description, String image, Long date) {
        this.libelle = libelle;
        this.duréé = duréé;
        this.diffic = diffic;
        this.ingd = ingd;
        this.description = description;
        this.image = new SimpleStringProperty(image);
        this.date = date;
    }

    public Recette(String libelle, String duréé, String diffic, String ingd, String description, BufferedImage bImage, Long date) {
        this.libelle = libelle;
        this.duréé = duréé;
        this.diffic = diffic;
        this.ingd = ingd;
        this.description = description;
        this.bImage = bImage;
        this.date = date;
    }
  
      public Recette(String libelle, String duréé, String diffic, String ingd, String description,String image) {
  
       this.libelle = libelle;
        this.duréé = duréé;
        this.diffic = diffic;
        this.ingd = ingd;
        this.description = description;
        this.image = new SimpleStringProperty(image);
      
    }
        public Recette(int id,String libelle, String duréé, String diffic, String ingd, String description,String image,Long date) {
       this.id=id;
       this.libelle = libelle;
        this.duréé = duréé;
        this.diffic = diffic;
        this.ingd = ingd;
        this.description = description;
        this.image = new SimpleStringProperty(image);
        this.date=date;
    
    }
        
           public Recette(int id,String libelle, String duréé, String diffic, String ingd, String description,String image,Long date,int idc) {
       this.id=id;
       this.libelle = libelle;
        this.duréé = duréé;
        this.diffic = diffic;
        this.ingd = ingd;
        this.description = description;
        this.image = new SimpleStringProperty(image);
        this.date=date;
        this.id_client=idc;
    
    }
    
    public Recette(int id, String libelle, String duréé, String diffic, String ingd, String description) {
        this.id = id;
        this.libelle = libelle;
        this.duréé = duréé;
        this.diffic = diffic;
        this.ingd = ingd;
        this.description = description;
    }

    public Recette(String libelle, String duréé, String diffic, String ingd, String description,String image, int usernid) {
       this.libelle = libelle;
        this.duréé = duréé;
        this.diffic = diffic;
        this.ingd = ingd;
        this.description = description;
        this.image = new SimpleStringProperty(image);
        this.id_client=usernid;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDuréé() {
        return duréé;
    }

    public void setDuréé(String duréé) {
        this.duréé = duréé;
    }

    public String getDiffic() {
        return diffic;
    }

    public void setDiffic(String diffic) {
        this.diffic = diffic;
    }

    public String getIngd() {
        return ingd;
    }

    public void setIngd(String ingd) {
        this.ingd = ingd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getImage() {
        return image.get();
    }

    public void setImage(String image) {
        this.image.set(image);
    }


    public BufferedImage getbImage() {
        return bImage;
    }

    public void setbImage(BufferedImage bImage) {
        this.bImage = bImage;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public SimpleStringProperty getLibelleO() {
     return new SimpleStringProperty(libelle);
    }

//    public ObservableValue<Image> getImageO(String img) {
//          }
//    
}