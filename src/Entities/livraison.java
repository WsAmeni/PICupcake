/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user16
 */
public class livraison {
  private int idL;
  private  int id_comande;
  private String etat;

    public livraison(int id_comande, String etat) {
        this.id_comande = id_comande;
        this.etat = etat;
    }

    public livraison(int idL, int id_comande, String etat) {
        this.idL = idL;
        this.id_comande = id_comande;
        this.etat = etat;
    }

    public int getIdL() {
        return idL;
    }

    public void setIdL(int idL) {
        this.idL = idL;
    }

    public int getId_comande() {
        return id_comande;
    }

    public void setId_comande(int id_comande) {
        this.id_comande = id_comande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "livraison{" + "idL=" + idL + ", id_comande=" + id_comande + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.idL;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final livraison other = (livraison) obj;
        if (this.idL != other.idL) {
            return false;
        }
        return true;
    }
 
 
}
