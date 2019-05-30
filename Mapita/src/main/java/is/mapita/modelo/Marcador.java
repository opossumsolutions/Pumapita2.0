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
public class Marcador  implements java.io.Serializable {

     private int idmarcador;
     private Tema tema;
     private String descripcion;
     private double longitud;
     private double latitud;
     private boolean selected;
     private Set comentarios = new HashSet(0);

    public Marcador() {
    }

    public Marcador(int idmarcador, String descripcion, double longitud, double latitud) {
        this.idmarcador = idmarcador;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Marcador(int idmarcador, Tema tema, String descripcion, double longitud, double latitud,Set comentarios) {
        this.idmarcador = idmarcador;
        this.tema = tema;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.comentarios = comentarios;
    }

    public int getIdmarcador() {
        return idmarcador;
    }

    public boolean isSelected() {
        return selected;
    }

    public Set getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public void setIdmarcador(int idmarcador) {
        this.idmarcador = idmarcador;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
