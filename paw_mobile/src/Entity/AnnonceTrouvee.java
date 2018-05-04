/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

//import java.io.File;
//import java.util.Date;
import com.codename1.io.File;
//import java.util.Objects;

/**
 *
 * @author Guideinfo
 */
public class AnnonceTrouvee extends Annonce {
    
    private String colier ; 
   // private Date date_trouvee ; 
    private String lieu_trouve ; 
     public AnnonceTrouvee(String colier,  String lieu_trouve, int id, int age, String couleur, String sex, String race, String message_complementaire, String type,String images) {
        super(id, age, couleur, sex, race, message_complementaire, type,images);
        this.colier = colier;
      //  this.date_trouvee = date_trouvee;
        this.lieu_trouve = lieu_trouve;
    }
    

    
    public AnnonceTrouvee(String colier,  String lieu_trouve, int id, int age, String couleur, String sex, String race, String message_complementaire, String type,int id_utilisateur,String images) {
        super(id, age, couleur, sex, race, message_complementaire, type,id_utilisateur,images);
        this.colier = colier;
      //  this.date_trouvee = date_trouvee;
        this.lieu_trouve = lieu_trouve;
    }
 public AnnonceTrouvee(String colier, String lieu_trouve, int id, int age, String couleur, String sex, String race, String message_complementaire, String type,int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type, id_utilisateur);
        this.colier = colier;
        //this.date_trouvee = date_trouvee;
        this.lieu_trouve = lieu_trouve;
    }

    public AnnonceTrouvee(String colier, String lieu_trouve, int id, int age, String couleur, String sex, String race, String message_complementaire, String type) {
        super(id, age, couleur, sex, race, message_complementaire, type);
        this.colier = colier;
        //this.date_trouvee = date_trouvee;
        this.lieu_trouve = lieu_trouve;
    }

    public AnnonceTrouvee()
    {
    } 

     

    public String getColier() {
        return colier;
    }

    public void setColier(String colier) {
        this.colier = colier;
    }

   /* public Date getDate_trouvee() {
        return date_trouvee;
    }

    public void setDate_trouvee(Date date_trouvee) {
        this.date_trouvee = date_trouvee;
    }
*/
    public String getLieu_trouve() {
        return lieu_trouve;
    }

    public void setLieu_trouve(String lieu_trouve) {
        this.lieu_trouve = lieu_trouve;
    }

    @Override
    public String toString() {
        return "AnnonceTrouvee{" +super.toString()+  "colier=" + colier + ", lieu_perdu=" + lieu_trouve + '}';
    }


    
    
    
    
    
}
