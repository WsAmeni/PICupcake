/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author jihed
 */
public class Reclamation {
    private int id_rec;
    private Client client;
    private Patisserie patisserie;
    private String content;
    private int id_client;
    private int id_patisserie;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
    private String Date = dtf.format(localDate);

    public Reclamation() {
    }

    public Reclamation(int id_patisserie) {
        this.id_patisserie = id_patisserie;
    }
    public Reclamation(String Date,Patisserie patisserie,Client client,String content) {
        this.Date = Date;
        this.client = client;
        this.patisserie = patisserie;
        this.content = content;
    }
    public Reclamation(int id_client, Patisserie patisserie,String content,String Date) {
        this.content = content;
        this.id_client = id_client;
        this.patisserie = patisserie;
        this.Date = Date;
    }
    public Reclamation(int id_client, int id_patisserie,String content) {
        this.content = content;
        this.id_client = id_client;
        this.id_patisserie = id_patisserie;
    }
    public Reclamation(int id_client, int id_patisserie , String content,String Date) {
        this.id_client = id_client;
        this.id_patisserie = id_patisserie;
        this.Date = Date;
        this.content = content;
    }
    
    public Reclamation(Client client, Patisserie patisserie , String content,String Date) {
        this.client = client;
        this.patisserie = patisserie;
        this.Date = Date;
        this.content = content;
    }

    public Reclamation(int id_rec, Client client, Patisserie patisserie, String Date, String content) {
        this.id_rec = id_rec;
        this.client = client;
        this.patisserie = patisserie;
        this.Date = Date;
        this.content = content;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(int id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Patisserie getPatisserie() {
        return patisserie;
    }

    public void setPatisserie(Patisserie patisserie) {
        this.patisserie = patisserie;
    }

    public String getDate() {
        return Date;
    }

    public void setDate_rec(String Date) {
        this.Date = Date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id_rec;
        hash = 43 * hash + Objects.hashCode(this.client);
        hash = 43 * hash + Objects.hashCode(this.patisserie);
        hash = 43 * hash + Objects.hashCode(this.localDate);
        hash = 43 * hash + Objects.hashCode(this.content);
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
        final Reclamation other = (Reclamation) obj;
        if (this.id_rec != other.id_rec) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.patisserie, other.patisserie)) {
            return false;
        }
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", client=" + client + ", patisserie=" + patisserie + ", date_rec=" + Date + ", content=" + content + '}';
    }
    
    
}
