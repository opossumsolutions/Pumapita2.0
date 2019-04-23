/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.controlador;

import is.mapita.modelo.Usuario;
import is.mapita.modelo.UsuarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juan
 */
@ManagedBean
@ViewScoped
public class EditaUsuario {
    private String nombre;
    private String contrasenia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public String editaUsuarioNombre(){
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        u.setNombre(nombre);
        udb.update(u);
        return "/comentarista/perfil?faces-redirect=true";
    }
    
    public String editaUsuarioContrasenia(){
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        u.setContrasenia(contrasenia);
        udb.update(u);
        return "/comentarista/perfil?faces-redirect=true";
    }
    
    public String eliminaUsuario(){
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        udb.delete(u);
        return "/index?faces-redirect=true";
    }
    
    public void eliminaUsuario(String correo){
        UsuarioDAO udb = new UsuarioDAO();
        Usuario u = udb.buscaPorCorreo(correo);
        udb.delete(u);
    }
}