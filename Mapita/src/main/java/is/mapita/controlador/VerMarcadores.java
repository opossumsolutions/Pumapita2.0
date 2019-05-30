/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.mapita.controlador;

import is.mapita.modelo.Marcador;
import is.mapita.modelo.MarcadorDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author juan
 */
@ManagedBean
@ViewScoped
public class VerMarcadores implements Serializable{
    
    private MapModel simpleModel;
    
    private MapModel userModel;
    
    private Marker marker;
    
    @PostConstruct
    public void verMarcadores(){
        simpleModel = new DefaultMapModel();
        MarcadorDAO mdb = new MarcadorDAO();
        List<Marcador> marcadores = mdb.findAll();
        if(marcadores!=null){
            for(Marcador m :marcadores){
                LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
                Marker marcador = new Marker(cord,m.getTema().getNombre(),m.getDescripcion());
                marcador.setIcon(m.getTema().getIcon());
                simpleModel.addOverlay(marcador);
            }
        }
    }
    
    public MapModel verMarcador(double lat, double lng){
        simpleModel = new DefaultMapModel();
        LatLng cord = new LatLng(lat,lng);
        MarcadorDAO mdb = new MarcadorDAO();
        Marcador marcador1 = mdb.buscaMarcadorPorLatLng(lat, lng);
        Marker marcador = new Marker(cord,marcador1.getTema().getNombre(),marcador1.getDescripcion());
        marcador.setIcon("../resources/images/"+marcador1.getTema().getColor()+".svg");
        simpleModel.addOverlay(marcador);
        return simpleModel;
    }
    
    public MapModel verMarcadorBusqueda(double lat, double lng){
        simpleModel = new DefaultMapModel();
        LatLng cord = new LatLng(lat,lng);
        MarcadorDAO mdb = new MarcadorDAO();
        Marcador marcador1 = mdb.buscaMarcadorPorLatLng(lat, lng);
        Marker marcador = new Marker(cord,marcador1.getTema().getNombre(),marcador1.getDescripcion());
        marcador.setIcon(marcador1.getTema().getIcon());
        simpleModel.addOverlay(marcador);
        return simpleModel;
    }
    
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       marker =(Marker) event.getOverlay();
       
    }

    public Marker getMarker() {
        return marker;
    }
    
    
}