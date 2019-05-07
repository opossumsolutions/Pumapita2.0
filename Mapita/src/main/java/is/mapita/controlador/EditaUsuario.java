/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.controlador;

import is.mapita.modelo.Rol;
import is.mapita.modelo.Usuario;
import is.mapita.modelo.UsuarioDAO;
import java.util.Date;
import java.util.List;
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
    private String correo;
    private Date fechanacimiento;
    private List<Usuario> resultado;
    private List<Usuario> eliminados;

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Usuario> getResultado() {
        return resultado;
    }

    public void setResultado(List<Usuario> resultado) {
        this.resultado = resultado;
    }

    public List<Usuario> getEliminados() {
        return eliminados;
    }

    public void setEliminados(List<Usuario> eliminados) {
        this.eliminados = eliminados;
    }
    

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
    
    public String editaUsuario(){
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        if(contrasenia==""){
            contrasenia=u.getContrasenia();
        }
        if(correo==""){
            correo=u.getCorreo();
        }
        if(nombre==""){
            nombre=u.getNombre();
        }
        if(fechanacimiento==null){
            fechanacimiento=u.getFechanacimiento();
        }
        u.setContrasenia(contrasenia);
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setFechanacimiento(fechanacimiento);
        udb.update(u);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ControladorSesion sesion = new ControladorSesion();
        sesion.setCorreo(this.correo);
        sesion.setContrasenia(this.contrasenia);
        sesion.login();
        if(u.getRol()==Rol.COMENTARISTA){
                return "/comentarista/perfil?faces-redirect=true";
            }else if(u.getRol()==Rol.INFORMADOR){
                return "/informador/perfil?faces-redirect=true";
            }else{
                return "/administrador/perfil?faces-redirect=true";
            }
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