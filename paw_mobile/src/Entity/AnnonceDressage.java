/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.codename1.io.File;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AnnonceDressage extends Annonce {
    private Date dateTr;
    private String typeTr;

    public AnnonceDressage(Date dateTr, String typeTr, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, String images) {
        super(id, age, couleur, sex, race, message_complementaire, type, images);
        this.dateTr = dateTr;
        this.typeTr = typeTr;
    }

    public AnnonceDressage(Date dateTr, String typeTr, int id, int age, String couleur, String sex, String race, String message_complementaire, String type) {
        super(id, age, couleur, sex, race, message_complementaire, type);
        this.dateTr = dateTr;
        this.typeTr = typeTr;
    }

    public AnnonceDressage(Date dateTr, String typeTr, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, int id_utilisateur, String images) {
        super(id, age, couleur, sex, race, message_complementaire, type, id_utilisateur, images);
        this.dateTr = dateTr;
        this.typeTr = typeTr;
    }

    public AnnonceDressage(Date dateTr, String typeTr, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type, id_utilisateur);
        this.dateTr = dateTr;
        this.typeTr = typeTr;
    }

    public AnnonceDressage(String typeTr, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type, id_utilisateur);
        this.typeTr = typeTr;
    }

    public AnnonceDressage() {
    }
   

    

    public Date getDateTr() {
        return dateTr;
    }

    public String getTypeTr() {
        return typeTr;
    }

    public void setDateTr(Date dateTr) {
        this.dateTr = dateTr;
    }

    public void setTypeTr(String typeTr) {
        this.typeTr = typeTr;
    }
    
    
    
}
