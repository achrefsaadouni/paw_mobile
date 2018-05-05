/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.codename1.io.File;
import com.codename1.ui.TextField;
import java.util.Date;

//import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AnnonceGarde extends Annonce {
    private Date dateSit;
    private int dureSit;
    private String todolist;

    public AnnonceGarde(Date dateSit, int dureSit, String todolist, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, String images) {
        super(id, age, couleur, sex, race, message_complementaire, type, images);
        this.dateSit = dateSit;
        this.dureSit = dureSit;
        this.todolist = todolist;
    }

    public AnnonceGarde(Date dateSit, int dureSit, String todolist, int id, int age, String couleur, String sex, String race, String message_complementaire, String type) {
        super(id, age, couleur, sex, race, message_complementaire, type);
        this.dateSit = dateSit;
        this.dureSit = dureSit;
        this.todolist = todolist;
    }

    public AnnonceGarde(Date dateSit, int dureSit, String todolist, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, int id_utilisateur, String images) {
        super(id, age, couleur, sex, race, message_complementaire, type, id_utilisateur, images);
        this.dateSit = dateSit;
        this.dureSit = dureSit;
        this.todolist = todolist;
    }

    public AnnonceGarde(Date dateSit, int dureSit, String todolist, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type, id_utilisateur);
        this.dateSit = dateSit;
        this.dureSit = dureSit;
        this.todolist = todolist;
    }

    public AnnonceGarde(int dureSit, String todolist, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, int id_utilisateur) {
        super(id, age, couleur, sex, race, message_complementaire, type, id_utilisateur);
        this.dureSit = dureSit;
        this.todolist = todolist;
    }

    public AnnonceGarde() {
    }

    

    
    public Date getDateSit() {
        return dateSit;
    }

    public int getDureSit() {
        return dureSit;
    }

    public String getTodolist() {
        return todolist;
    }

    public void setDateSit(Date dateSit) {
        this.dateSit = dateSit;
    }

    public void setDureSit(int dureSit) {
        this.dureSit = dureSit;
    }

    public void setTodolist(String todolist) {
        this.todolist = todolist;
    }
    
}
