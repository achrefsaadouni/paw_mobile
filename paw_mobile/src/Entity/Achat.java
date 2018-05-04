/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author vinga
 */
public class Achat {
    
    private int id_achat;
    private Utilisateur user;
    private List<LigneAchat> list;
    private Date date_achat;
    private double prix;
    private String etat;

    public List<LigneAchat> getList() {
        return list;
    }

    public void setList(List<LigneAchat> list) {
        this.list = list;
    }

  

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }


    
    
    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

 

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Achat(int id_achat, Utilisateur user, List<LigneAchat> list, Date date_achat, double prix, String etat) {
        this.id_achat = id_achat;
        this.user = user;
        this.list = list;
        this.date_achat = date_achat;
        this.prix = prix;
        this.etat = etat;
    }

    
    
}
