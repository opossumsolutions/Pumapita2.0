/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.controlador;

import is.mapita.modelo.Marcador;
import is.mapita.modelo.MarcadorDAO;
import is.mapita.modelo.Tema;
import is.mapita.modelo.TemaDAO;
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
public class AgregaTema {
    private String nombre;
    private String color;
    private double longitud;
    private double latitud;
    private String descripcion;
    private String icon;
    
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    
    
    public String agregaTema(){
        TemaDAO tdb =new TemaDAO();
        UsuarioDAO udb = new UsuarioDAO();
        Tema t = tdb.buscaTemaPorNombre(nombre);
        if(t!= null){
            Mensajes.fatal("Ya existe un tema con este nombre \n");
            return "";
        }
        t = new Tema();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        MarcadorDAO mdb =new MarcadorDAO();
        Marcador m = mdb.buscaMarcadorPorLatLng(latitud, longitud);
        if(m!= null){
            this.descripcion ="";
            Mensajes.fatal("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        m = new Marcador();
        t.setNombre(nombre);
        t.setColor(color);
        t.setUsuario(u);
        tdb.save(t);
        m.setDescripcion(descripcion);
        m.setLatitud(latitud);
        m.setLongitud(longitud);
        m.setIcon(icon);
        m.setTema(t);
        mdb.save(m);
        Mensajes.info("Se guardo el tema");
        ControladorSesion cs =new ControladorSesion();
        cs.setContrasenia(u.getContrasenia());
        cs.setCorreo(u.getCorreo());
        cs.logout();
        cs.login();
        return "/informador/perfil?faces-redirect=true";
    }
    
    public String agregaMarcador(){
        MarcadorDAO mdb =new MarcadorDAO();
        TemaDAO tdb = new TemaDAO();
        Marcador m = mdb.buscaMarcadorPorLatLng(latitud, longitud);
        if(m!= null){
            this.descripcion ="";
            Mensajes.fatal("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        m = new Marcador();
        Tema t = tdb.buscaTemaPorNombre(nombre);
        m.setDescripcion(descripcion);
        m.setLatitud(latitud);
        m.setLongitud(longitud);
        m.setIcon(icon);
        m.setTema(t);
        mdb.save(m);
        this.descripcion ="";
        Mensajes.info("Se guardo el tema");
        ControladorSesion cs =new ControladorSesion();
        cs.setContrasenia(u.getContrasenia());
        cs.setCorreo(u.getCorreo());
        cs.logout();
        cs.login();
        return "/informador/perfil?faces-redirect=true";
    }
}