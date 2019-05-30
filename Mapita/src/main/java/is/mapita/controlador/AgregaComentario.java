/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.controlador;

import is.mapita.modelo.Comentario;
import is.mapita.modelo.ComentarioDAO;
import is.mapita.modelo.Marcador;
import is.mapita.modelo.MarcadorDAO;
import is.mapita.modelo.Usuario;
import is.mapita.modelo.UsuarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juan
 */
@ManagedBean
@RequestScoped
public class AgregaComentario {
    
    private double latitud;
    private double longitud;
    private String contenido;

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public String agregaComentario(){
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        MarcadorDAO mdb =new MarcadorDAO();
        Marcador m = mdb.buscaMarcadorPorLatLng(latitud, longitud);
        ComentarioDAO cdb = new ComentarioDAO();
        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setMarcador(m);
        comentario.setUsuario(u);
        cdb.save(comentario);
        Mensajes.info("Se guardo el comentario");
        ControladorSesion cs =new ControladorSesion();
        cs.setContrasenia(u.getContrasenia());
        cs.setCorreo(u.getCorreo());
        cs.logout();
        cs.login();
        return "/comentarista/perfil?faces-redirect=true";
    }
    
}