package is.mapita.modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan
 */
public class ComentarioDAO extends AbstractDAO<Comentario>{
    /**
     * 
     */
    public ComentarioDAO(){
        super();
    }
    
    
    /**
     * 
     * @param comentario 
     */
    @Override
    public void save(Comentario comentario){
        super.save(comentario);
    }
    
    /**
     * 
     * @param comentario 
     */
    @Override
    public void update(Comentario comentario){
        super.update(comentario);
    }
    
    /**
     * 
     * @param comentario 
     */
    @Override
    public void delete(Comentario comentario){
        super.delete(comentario);
    }
       
    /**
     * 
     * @param id
     * @return 
     */
    public Comentario find(int id){
        return super.find(Comentario.class, id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Comentario> findAll(){
        return super.findAll(Comentario.class);
    }
    
    public Comentario buscaComentarioPorMarcador(double lat,double lng) {
        Comentario c = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentario c where c.marcador.longitud = :lng and c.marcador.latitud = :lat";
            Query query = session.createQuery(hql);
            query.setParameter("lng", lng);
            query.setParameter("lat", lat);
            c = (Comentario)query.uniqueResult();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return c;
    
    }
    
    public List<Comentario> buscarComentariosPorUsuario(String correo){
        List<Comentario> c = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentario c where c.usuario.correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            c = (List<Comentario>)query.list();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return c;
    }
}