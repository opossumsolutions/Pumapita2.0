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
public class Comentario  implements java.io.Serializable {

     private int idcomentario;
     private Marcador marcador;
     private Usuario usuario;
     private String contenido;
     private boolean selected;

    public Comentario() {
    }

    public Comentario(int idcomentario, String contenido) {
        this.idcomentario = idcomentario;
        this.contenido = contenido;
    }

    public Comentario(int idcomentario,Marcador marcador, String contenido,Usuario usuario) {
        this.idcomentario = idcomentario;
        this.marcador = marcador;
        this.contenido = contenido;
        this.usuario = usuario;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

    public Marcador getMarcador() {
        return marcador;
    }

    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
