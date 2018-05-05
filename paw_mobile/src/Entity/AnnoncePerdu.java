/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.codename1.io.File;

/**
 *
 * @author Guideinfo
 */
public class AnnoncePerdu extends Annonce{
    private int utilisateur ; 
    private String colier  ; 
    private String lieu_perdu ;  
    public AnnoncePerdu() {
      
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }
    

   
    
    public AnnoncePerdu(String colier,String lieu_perdu , int id, int age, String couleur, String sex, String race, String message_complementaire, String type,String images,int id_utilisateur ) {
        super(id, age, couleur, sex, race, message_complementaire, type, id_utilisateur,images);
        this.colier = colier;
       // this.date_perte = date_perte;
        this.lieu_perdu=lieu_perdu ; 
    }
    
    public AnnoncePerdu(String colier,String lieu_perdu , int id, int age, String couleur, String sex, String race, String message_complementaire, String type,int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type,id_utilisateur);
        this.colier = colier;
 //       this.date_perte = date_perte;
        this.lieu_perdu=lieu_perdu ; 
    }
    
  
    
    public String getLieu_perdu() {
        return lieu_perdu;
    }

    public void setLieu_perdu(String lieu_perdu) {
        this.lieu_perdu = lieu_perdu;
    }

    public String getColier() {
        return colier;
    }

    public void setColier(String colier) {
        this.colier = colier;
    }

    @Override
    public String toString() {
        return "AnnoncePerdu{" +super.toString() +"colier=" + colier  + ",lieu_perdu" +lieu_perdu +'}';
    }
 
    
    
 
 
 
}
