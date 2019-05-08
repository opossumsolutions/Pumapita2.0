/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TemaDAO extends AbstractDAO<Tema>{
    /**
     * 
     */
    public TemaDAO(){
        super();
    }
    
    
    /**
     * 
     * @param tema 
     */
    @Override
    public void save(Tema tema){
        super.save(tema);
    }
    
    /**
     * 
     * @param tema 
     */
    @Override
    public void update(Tema tema){
        super.save(tema);
    }
    
    /**
     * 
     * @param tema 
     */
    @Override
    public void delete(Tema tema){
        super.delete(tema);
    }
       
    /**
     * 
     * @param id
     * @return 
     */
    public Tema find(int id){
        return super.find(Tema.class, id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Tema> findAll(){
        return super.findAll(Tema.class);
    }
    
    public Tema buscaTemaPorColor(String color) {
        Tema m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Tema where color = :color";
            Query query = session.createQuery(hql);
            query.setParameter("color", color);
            m = (Tema)query.uniqueResult();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return m;
    
    }
    
    public Tema buscaTemaPorNombre(String nombre) {
        Tema m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Tema where nombre = :nombre";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", nombre);
            m = (Tema)query.uniqueResult();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return m;
    
    }
    
    
    
    public List<Tema> ObtenTemasPorUsuario(String correo){
        List<Tema> m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Tema m where m.usuario.correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            m = (List<Tema>)query.list();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return m;
    }
}