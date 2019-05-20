/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.controlador;

import is.mapita.modelo.Tema;
import is.mapita.modelo.TemaDAO;
import is.mapita.modelo.Usuario;
import is.mapita.modelo.UsuarioDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juan
 */
@ManagedBean
@SessionScoped
public class VerTemas {
    private List<Tema> resultado;
    private List<Tema> eliminados;

    public List<Tema> getResultado() {
        return resultado;
    }

    public void setResultado(List<Tema> resultado) {
        this.resultado = resultado;
    }

    @PostConstruct
    public void init() {
        TemaDAO tdb = new TemaDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        resultado = tdb.buscaPorUsuario(us.getCorreo());
    }
    
    public String eliminarTemas(){
        TemaDAO tdb = new TemaDAO();
        for(Tema r : resultado){
            if(r.isSelected()){
                tdb.buscaTemaPorNombre(r.getNombre());
                tdb.delete(r);
            }
        }
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        ControladorSesion cs =new ControladorSesion();
        cs.setContrasenia(u.getContrasenia());
        cs.setCorreo(u.getCorreo());
        cs.logout();
        cs.login();
        return "/informador/perfil?faces-redirect=true";
    }
}
