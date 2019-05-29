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
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.map.MarkerDragEvent;
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
public class MarkerSelectionView implements Serializable {
     
    private MapModel simpleModel;
    private List<Marcador> resultadoMarcador;
    private List <Tema> resultadoTema;
    private Marker marcador;
    private MapModel draggableModel;
      
    public MapModel getDraggableModel() {
        return draggableModel;
    }
      
    public void onMarkerDrag(MarkerDragEvent event) {
        marcador = event.getMarker();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marcador.getLatlng().getLat()+ marcador.getTitle() + ", Lng:" + marcador.getLatlng().getLng()));
    }

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
        simpleModel = new DefaultMapModel();
        MarcadorDAO mdb = new MarcadorDAO();
        TemaDAO tdb = new TemaDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        resultadoTema = tdb.buscaPorUsuario(us.getCorreo());
        if(resultadoTema!=null){
            for(Tema t : resultadoTema){
                resultadoMarcador = mdb.ObtenMarcadoresPorTema(t.getNombre());
                if(resultadoMarcador!=null){
                    for(Marcador m :resultadoMarcador){
                        LatLng cord = new LatLng(m.getLatitud(), m.getLongitud());
                        marcador = new Marker(cord,m.getTema().getNombre(),m.getDescripcion());
                        simpleModel.addOverlay(marcador);
                        for(Marker premarker : simpleModel.getMarkers()) {
                            premarker.setDraggable(true);
                        }
                    }
                }
            }
        }
    }
      
    public MapModel getSimpleModel() {
        return simpleModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        marcador = (Marker) event.getOverlay();
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marcador.getTitle()));
    }
      
    public Marker getMarker() {
        return marcador;
    }
    
}