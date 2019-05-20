/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author juan
 */
public class Tema  implements java.io.Serializable {

     private int idtema;
     private Usuario usuario;
     private String nombre;
     private String color;
     private boolean selected;
     private Set marcadores = new HashSet(0);

    public Tema() {
    }

    public Tema(int idtema, String nombre, String color) {
        this.idtema = idtema;
        this.nombre = nombre;
        this.color = color;
    }

    public Tema(int idtema, Usuario usuario, String nombre, String color,Set marcadores) {
        this.idtema = idtema;
        this.usuario = usuario;
        this.nombre = nombre;
        this.color = color;
        this.marcadores = marcadores;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public Set getMarcadores() {
        return marcadores;
    }

    public void setMarcadores(Set marcadores) {
        this.marcadores = marcadores;
    }
    

    public int getIdtema() {
        return idtema;
    }

    public void setIdtema(int idtema) {
        this.idtema = idtema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
}