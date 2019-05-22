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
public class MarcadorDAO extends AbstractDAO<Marcador>{
    /**
     * 
     */
    public MarcadorDAO(){
        super();
    }
    
    
    /**
     * 
     * @param marcador 
     */
    @Override
    public void save(Marcador marcador){
        super.save(marcador);
    }
    
    /**
     * 
     * @param marcador 
     */
    @Override
    public void update(Marcador marcador){
        super.update(marcador);
    }
    
    /**
     * 
     * @param marcador 
     */
    @Override
    public void delete(Marcador marcador){
        super.delete(marcador);
    }
       
    /**
     * 
     * @param id
     * @return 
     */
    public Marcador find(int id){
        return super.find(Marcador.class, id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Marcador> findAll(){
        return super.findAll(Marcador.class);
    }
    
    public Marcador buscaMarcadorPorLatLng(double lat,double lng) {
        Marcador m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador where longitud = :lng and latitud = :lat";
            Query query = session.createQuery(hql);
            query.setParameter("lng", lng);
            query.setParameter("lat", lat);
            m = (Marcador)query.uniqueResult();
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
    
    public List<Marcador> ObtenMarcadoresPorUsuario(String correo){
        List<Marcador> m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador m where m.usuario.correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            m = (List<Marcador>)query.list();
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