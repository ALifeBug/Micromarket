package club.fallenstar.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

    public void save(T o); //保存

    public void delete(Serializable id);//删除

    public void delete(T o); //删除

    public void update(T o); //更新

    public void saveOrUpdate(T o);

    public T get(Serializable id); //获取

    public List<T> find(String hql);

    public T findUnique(String hql);

    public List<T> find(String hql, Map<String, Object> params);

    public int executeHql(String hql);

    public int executeHql(String hql, Map<String, Object> params);


}
