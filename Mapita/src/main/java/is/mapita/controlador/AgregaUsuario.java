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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juan
 */
@ManagedBean
@RequestScoped
public class AgregaUsuario {
    private String nombre;
    private String correo;
    private String contrasenia;
    private Date fechanacimiento;
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    
    public void agregaUsuario(String usuario){
        Usuario u = new Usuario();
        UsuarioDAO udao= new UsuarioDAO();
        if(udao.buscaPorCorreo(correo)==null){
            u.setNombre(nombre);
            u.setCorreo(correo);
            u.setContrasenia(contrasenia);
            u.setFechanacimiento(fechanacimiento);
            if(usuario.equals("comentarista")){
                u.setRol(Rol.COMENTARISTA);
            }else if(usuario.equals("informador")){
                Mail email = new Mail();
                email.sendMail("Registro","Bienvenido a mapita",correo);
                u.setRol(Rol.INFORMADOR);
            }else {
                u.setRol(Rol.ADMINISTRADOR);
            }

            UsuarioDAO udb = new UsuarioDAO();
            udb.save(u);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Has sido registrado con éxito " + nombre));
        }else{
            Mensajes.warn("correo ya registrado.\nInicie sesion o intente con uno diferente");
        }
    }
    
}
