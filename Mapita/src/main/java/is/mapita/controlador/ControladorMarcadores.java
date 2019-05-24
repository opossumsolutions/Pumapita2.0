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
public class ControladorMarcadores {
    private List<Marcador> resultadoMarcador;
    private List<Tema> resultadoTema;

    public List<Marcador> getResultadoMarcador() {
        return resultadoMarcador;
    }

    public void setResultadoMarcador(List<Marcador> resultadoMarcador) {
        this.resultadoMarcador = resultadoMarcador;
    }

    public List<Tema> getResultadoTema() {
        return resultadoTema;
    }

    public void setResultadoTema(List<Tema> resultadoTema) {
        this.resultadoTema = resultadoTema;
    }
    
    @PostConstruct
    public void init() {
        MarcadorDAO mdb = new MarcadorDAO();
        TemaDAO tdb = new TemaDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        resultadoMarcador = mdb.ObtenMarcadoresPorUsuario(us.getCorreo());
        resultadoTema = tdb.buscaPorUsuario(us.getCorreo());
    }
    
    public List<Marcador> marcadoresPorTema(String nombre){
        MarcadorDAO mdb = new MarcadorDAO();
        resultadoMarcador = mdb.ObtenMarcadoresPorTema(nombre);
        return resultadoMarcador;
    }
}
