/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.modelo;

/**
 *
 * @author juan
 */
public class Marcador  implements java.io.Serializable {

     private int idmarcador;
     private Usuario usuario;
     private Tema tema;
     private String descripcion;
     private double longitud;
     private double latitud;
     private String icon;

    public Marcador() {
    }

    public Marcador(int idmarcador, String descripcion, double longitud, double latitud, String icon) {
        this.idmarcador = idmarcador;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.icon = icon;
    }

    public Marcador(int idmarcador, Usuario usuario, String descripcion, double longitud, double latitud, String icon) {
        this.idmarcador = idmarcador;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.icon = icon;
    }

    public int getIdmarcador() {
        return idmarcador;
    }

    public void setIdmarcador(int idmarcador) {
        this.idmarcador = idmarcador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
