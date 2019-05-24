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
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static org.hibernate.type.TypeFactory.serializable;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author juan
 */
@ManagedBean
@SessionScoped
public class controladorTemas {
    private List<Tema> resultado;
    private List<Tema> eliminados;
    private String nuevoColor;
    private String nuevoNombre;

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public List<Tema> getEliminados() {
        return eliminados;
    }

    public void setEliminados(List<Tema> eliminados) {
        this.eliminados = eliminados;
    }

    public String getNuevoColor() {
        return nuevoColor;
    }

    public void setNuevoColor(String nuevoColor) {
        this.nuevoColor = nuevoColor;
    }
    
    public void actualizar(RowEditEvent event){
        Tema tema = (Tema) event.getObject();
        TemaDAO tdb= new TemaDAO();
        if(nuevoColor==""){
            nuevoColor=tema.getColor();
        }
        if(nuevoNombre==""){
            nuevoNombre=tema.getNombre();
        }
        tema.setColor(nuevoColor);
        tema.setNombre(nuevoNombre);
        tdb.update(tema);
    }
    
    public void cancelar(RowEditEvent event){
        
    }
    
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
