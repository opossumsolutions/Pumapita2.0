/*ssssss
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.controlador;

import is.lab.mapita.modelo.Tema;
import is.lab.mapita.modelo.TemaDAO;
import is.lab.mapita.modelo.Usuario;
import is.lab.mapita.modelo.UsuarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * @autor McFly
 */
@ManagedBean
@RequestScoped
public class AgregaTema {
    private String nombre;
    private String color;

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
            Mensajes.fatal("Ese nombre ya existe \n");
            return "";
        }
        t = new Tema();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        t.setNombre(nombre);
        t.setColor(color);
        t.setUsuario(u);
        tdb.save(t);
        Mensajes.info("Tema registrado");
        return "";
    }
}
