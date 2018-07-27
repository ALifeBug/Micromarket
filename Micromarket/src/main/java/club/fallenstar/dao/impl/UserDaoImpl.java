package club.fallenstar.dao.impl;

import club.fallenstar.dao.UserDao;
import club.fallenstar.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    public User findByName(String name) {
        String hql = "from User u where u.nickname = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,name);
        return (User)query.uniqueResult();
    }

    public User findByPhone(String phone) {
        String hql = "from User u where u.phone = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,phone);
        return (User)query.uniqueResult();
    }

    public User findByNameAndPassword(String name, String password) {
        String hql = "from User u where u.username = ? and u.password = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0, name);
        query.setParameter(1, password);
        return (User) query.uniqueResult();
    }

    public boolean updateUser(String name, String nickname, String email, String grade, String academy, String contact) {
        String hql = "update User u set u.nickname = ?,u.email = ?,u.grade = ?,u.academy = ?,u.contact = ? where u.nickname=?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,nickname).setParameter(1,email).setParameter(2,grade).setParameter(3,academy).setParameter(4,contact).setParameter(5,name);
        return query.executeUpdate()>0;
    }


}
