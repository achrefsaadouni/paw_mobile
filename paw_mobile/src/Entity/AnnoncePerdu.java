/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

//import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
//import java.awt.List;
//import java.io.File;
//import static java.nio.file.Files.list;
//import java.util.Date;
import com.codename1.io.File;
//import java.util.Objects;

/**
 *
 * @author Guideinfo
 */
public class AnnoncePerdu extends Annonce{
    private int utilisateur ; 
 private String colier  ; 
 //private Date date_perte ; 
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
    
  /*  public AnnoncePerdu(String colier,String lieu_perdu , int id, int age, String couleur, String sex, String race, String message_complementaire, String type,int id_utilisateur,File images) {
        super(id, age, couleur, sex, race, message_complementaire,id_utilisateur,images);
        this.colier = colier;
      //  this.date_perte = date_perte;
        this.lieu_perdu=lieu_perdu ; 
    }
*/
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
/*
    public Date getDate_perte() {
        return date_perte;
    }

    public void setDate_perte(Date date_perte) {
        this.date_perte = date_perte;
    }
*/
  /*  @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.colier);
        hash = 59 * hash + Objects.hashCode(this.date_perte);
        return hash;
    }
*/
    /*
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
        final AnnoncePerdu other = (AnnoncePerdu) obj;
        if (!Objects.equals(this.colier, other.colier)) {
            return false;
        }
        if (!Objects.equals(this.lieu_perdu, other.lieu_perdu)) {
            return false;
        }
      
        return true;
    }

*/   
    @Override
    public String toString() {
        return "AnnoncePerdu{" +super.toString() +"colier=" + colier  + ",lieu_perdu" +lieu_perdu +'}';
    }
 
    
    
 
 
 
}
