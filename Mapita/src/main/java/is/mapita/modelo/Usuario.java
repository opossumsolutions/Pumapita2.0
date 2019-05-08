package is.mapita.modelo;
// Generated 08-feb-2019 13:44:51 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Usuario  implements java.io.Serializable {


     private int idusuario;
     private String nombre;
     private String correo;
     private String contrasenia;
     private Date fechanacimiento;
     private Rol rol;
     private boolean selected;
     private Set marcadors = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(int idusuario, String nombre, String correo, String contrasenia,Rol rol) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol =rol;
    }
    public Usuario(int idusuario, String nombre, String correo, String contrasenia, Date fechanacimiento,Rol rol,Set marcadors) {
       this.idusuario = idusuario;
       this.nombre = nombre;
       this.correo = correo;
       this.contrasenia = contrasenia;
       this.fechanacimiento = fechanacimiento;
       this.marcadors = marcadors;
       this.rol =rol;
    }

    public Set getMarcadors() {
        return marcadors;
    }

    public void setMarcadors(Set marcadors) {
        this.marcadors = marcadors;
    }
    
    public int getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContrasenia() {
        return this.contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public Date getFechanacimiento() {
        return this.fechanacimiento;
    }
    
    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    


}


