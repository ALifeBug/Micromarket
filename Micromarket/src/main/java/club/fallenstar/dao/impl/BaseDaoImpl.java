package club.fallenstar.dao.impl;

import club.fallenstar.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Repository("baseDao")
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> clz;

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

    private Class<T> getClz(){
        if(clz==null){
            //获取泛型的Class对象
            clz = ((Class<T>)
                    (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }

    public void save(T o) {
        this.getCurrentSession().save(o);
    }

    public void delete(Serializable id) {
        T o = this.get(id);
        this.getCurrentSession().delete(o);
    }

    public void delete(T o) {
        this.getCurrentSession().delete(o);
    }

    public void update(T o) {
        this.getCurrentSession().update(o);
    }

    public void saveOrUpdate(T o) {
        this.getCurrentSession().saveOrUpdate(o);
    }

    public T get(Serializable id) {
        return (T)this.getCurrentSession().get(getClz(),id);
    }

    public List<T> find(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    public T findUnique(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return (T)q.uniqueResult();
    }

    public List<T> find(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if(params!=null && !params.isEmpty()){
            for(String key:params.keySet()){
                q.setParameter(key,params.get(key));
            }
        }
        return q.list();
    }

    public int executeHql(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    public int executeHql(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }
}
