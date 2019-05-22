package is.mapita.controlador;

import is.mapita.modelo.Marcador;
import is.mapita.modelo.MarcadorDAO;
import is.mapita.modelo.Tema;
import is.mapita.modelo.TemaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author juan
 */
@ManagedBean
@RequestScoped
public class AgregaMarcador implements Serializable {
    
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
    
    public String agregaMarcador(String color ,String tema){
        MarcadorDAO mdb =new MarcadorDAO();
        TemaDAO tdb = new TemaDAO();
        Marcador m = mdb.buscaMarcadorPorLatLng(latitud, longitud);
        if(m!= null){
            this.descripcion ="";
            Mensajes.fatal("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        m = new Marcador();
        Tema t = tdb.buscaTemaPorNombre(tema);
        m.setDescripcion(descripcion);
        m.setLatitud(latitud);
        m.setLongitud(longitud);
        //this.creaIcono(color,50,50);
        //m.setIcon("resources/images/"+color+".svg");
        m.setIcon(icon);
        m.setTema(t);
        mdb.save(m);
        this.descripcion ="";
        Mensajes.info("Se guardo el marcador");
        return "/informador/perfil?faces-redirect=true";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}