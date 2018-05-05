/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

//import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
//import java.io.File;
//import java.util.Date;
import com.codename1.io.File;
//import java.util.Objects;

/**
 *
 * @author Guideinfo
 */
public class Annonce 
{
   private int id  ; 
   private int age ; 
   private String couleur ;
   private String sex ; 
   private String race ; 
   private String message_complementaire ; 
   private String type  ; 
  // private Date date;
   private String images;
   private int id_utilisateur;
   private String nomUtilisater ;
   private String EmailUtilisateur  ; 
   private String PrenomUtilisateur ; 
   private String NumeroUtilisateur  ; 
   private String adresseUtilisateur ; 

    public String getAdresseUtilisateur() {
        return adresseUtilisateur;
    }

    public void setAdresseUtilisateur(String adresseUtilisateur) {
        this.adresseUtilisateur = adresseUtilisateur;
    }

    public String getEmailUtilisateur() {
        return EmailUtilisateur;
    }

    public void setEmailUtilisateur(String EmailUtilisateur) {
        this.EmailUtilisateur = EmailUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return PrenomUtilisateur;
    }

    public void setPrenomUtilisateur(String PrenomUtilisateur) {
        this.PrenomUtilisateur = PrenomUtilisateur;
    }

    public String getNumeroUtilisateur() {
        return NumeroUtilisateur;
    }

    public void setNumeroUtilisateur(String NumeroUtilisateur) {
        this.NumeroUtilisateur = NumeroUtilisateur;
    }

    public String getNomUtilisater() {
        return nomUtilisater;
    }

 public void setNomUtilisater(String nomUtilisater) {
        this.nomUtilisater = nomUtilisater;
    }

  /*  public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
*/
 
    public Annonce() {
    }
     public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type,String images,int id_utilisateur) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
  //      this.date=date;
        this.images=images ; 
        this.id_utilisateur = id_utilisateur;
    }
    public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire,  String type) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
      //  this.date=date;
    }
    
    public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type,int id_utilisateur,String images) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
     //   this.date=date;
        this.id_utilisateur=id_utilisateur;
         this.images = images;
    }
    
                        

      public Annonce(int id , int age, String couleur, String sex, String race, String message_complementaire, String type, int id_utilisateur) {
        this.id=id ; 
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
        this.id_utilisateur=id_utilisateur;
   //     this.date=date;
    }                        

    public Annonce(int id, int age, String couleur, String sex, String race, String message_complementaire, String type, String images) {
        this.id = id;
        this.age = age;
        this.couleur = couleur;
        this.sex = sex;
        this.race = race;
        this.message_complementaire = message_complementaire;
        this.type = type;
        this.images = images;
    }
                        
   

    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCouleur() {
        return couleur;
    }

      public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", age=" + age + ", couleur=" + couleur + ", sex=" + sex + ", race=" + race + ", message_complementaire=" + message_complementaire + ", type=" + type + ", images=" + images + '}';
    }
    

    public void setRace(String race) {
        this.race = race;
    }

    public String getMessage_complementaire() {
        return message_complementaire;
    }

    public void setMessage_complementaire(String message_complementaire) {
        this.message_complementaire = message_complementaire;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

   
   
    
    
    
    }
   
   
   
    

