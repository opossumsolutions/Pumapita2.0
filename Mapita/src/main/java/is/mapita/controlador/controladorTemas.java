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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
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
        if(nuevoColor==null){
            nuevoColor=tema.getColor();
        }
        if(nuevoNombre==""){
            nuevoNombre=tema.getNombre();
        }
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String destination = (servletContext.getRealPath("/")+tema.getIcon());
        File fichero = new File(destination);
        fichero.delete();
        tema.setColor(nuevoColor);
        tema.setNombre(nuevoNombre);
        this.creaIcono(tema.getColor(),50,50);
        tema.setIcon("resources/images/"+tema.getColor()+".svg");
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
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String destination = (servletContext.getRealPath("/")+r.getIcon());
                File fichero = new File(destination);
                fichero.delete();
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
    
    private void creaIcono(String color,int largo,int ancho){
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        s+="<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n";
			s+="<svg width=\""+largo+"\" height=\""+ancho+"\" version=\"1.1\" id=\"Capa_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" style=\"enable-background:new 0 0 512 512;\" xml:space=\"preserve\">\n<g>\n";
        int x =largo/2;
        int y = (ancho/3);
        int radio = ((largo+ancho)/2)/4;

        int[] p ={x-radio,y,x+radio,y,x,(y*3)};
        s+= creaPoligono(p,"#"+color);
        s+=creaCirculo(x,y,radio,"#"+color,true);
        s+=creaCirculo(x,y,radio/2,"black",true);

        s+="</g>\n"+"</svg>";
        
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String destination = (servletContext.getRealPath("/"))+"/resources/images/";
            System.out.println(destination);
            FileOutputStream fileOut = new FileOutputStream(new File(destination + color+".svg"));
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            out.write(s);
            out.close();
        } catch (IOException ioe) {
            System.out.println("No pude guardar en el archivo" );
//            System.exit(1);
        }


    }

    private String creaCirculo(int x ,int y , int r,String color,boolean stroke){
        String s = stroke ? "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"black\" stroke-width=\"1\"  fill=\"" + color + "\" />\n" : "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"black\" stroke-width=\"0\"  fill=\"" + color + "\" />\n";
        return  s;

    }

    private String creaPoligono(int[] puntos,String color){
        String p = "";
        if(puntos.length%2 != 0)
          return "Los puntos estan mal";
        for(int i=0;i<puntos.length;i+=2){
          p+=puntos[i]+","+puntos[i+1]+" ";
        }
        return "<polygon points=\""+p+"\" \n style=\" fill:" +color+";stroke:black;stroke-width:1;\" /> \n";
    }
}
