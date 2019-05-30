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
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author juan
 */
@ManagedBean
@SessionScoped
//@RequestScoped
public class BuscaPorNombre {
    private String nombre;
    private String nombreTema;
    private List<Tema> resultadoTema;
    private List<Usuario> resultado;
    private List<Usuario> eliminados;

    public List<Tema> getResultadoTema() {
        return resultadoTema;
    }

    public void setResultadoTema(List<Tema> resultadoTema) {
        this.resultadoTema = resultadoTema;
    }
    
    public void setResultado(List<Usuario> resultado) {
        this.resultado = resultado;
    }

    public List<Usuario> getResultado() {
        return resultado;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public String buscaPorNombre(){
        if(nombre.equals(""))
            return "";
        UsuarioDAO ubd = new UsuarioDAO();
        resultado =  ubd.buscaPorNombre(nombre);
        return "/resultado?faces-redirect=true";
    }
    public List<Marcador> marcadoresPorTema(String nombre){
        MarcadorDAO mdb = new MarcadorDAO();
        List<Marcador> resultadoMarcador = mdb.ObtenMarcadoresPorTema(nombre);
        return resultadoMarcador;
    }
    
    public MapModel verMarcadorBusqueda(double lat, double lng){
        MapModel simpleModel = new DefaultMapModel();
        LatLng cord = new LatLng(lat,lng);
        MarcadorDAO mdb = new MarcadorDAO();
        Marcador marcador1 = mdb.buscaMarcadorPorLatLng(lat, lng);
        Marker marcador = new Marker(cord,marcador1.getTema().getNombre(),marcador1.getDescripcion());
        marcador.setIcon(marcador1.getTema().getIcon());
        simpleModel.addOverlay(marcador);
        return simpleModel;
    }
    
    public String buscaPorNombreTema(){
        if(nombreTema.equals(""))
            return "";
        TemaDAO tdb = new TemaDAO();
        resultadoTema =  tdb.buscaPorNombreTema(nombreTema);
        return "/resultadoTema?faces-redirect=true";
    }
    
    public String eliminarUsuarios(){
        UsuarioDAO udb = new UsuarioDAO();
        for(Usuario r : resultado){
            if(r.isSelected()){
                udb.delete(r);
            }
        }
        UsuarioDAO ubd = new UsuarioDAO();
        resultado =  ubd.buscaPorNombre(nombre);
        return "/resultado?faces-redirect=true";
    }
    
    public String eliminarTemas(){
        TemaDAO tdb = new TemaDAO();
        for(Tema r : resultadoTema){
            if(r.isSelected()){
                tdb.delete(r);
            }
        }
        TemaDAO tbd = new TemaDAO();
        resultadoTema =  tbd.buscaPorNombreTema(nombreTema);
        return "";
    }
}
