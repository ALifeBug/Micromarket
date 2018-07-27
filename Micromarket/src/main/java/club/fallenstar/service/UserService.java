package club.fallenstar.service;

import club.fallenstar.dao.UserDao;
import club.fallenstar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    //查询用户
    public User findByName(String name){
        return userDao.findByName(name);
    }

    public User findByPhone(String phone){
        return userDao.findByPhone(phone);
    }

    //检查昵称是否被占用
    public boolean userExist(String param){
        User user = userDao.findByName(param);
        User user1 = userDao.findByPhone(param);
        if(user==null && user1==null) return false;
        else return true;
    }

    //注册用户
    public boolean save(User user){
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        if(userExist(user.getPhone()) || userExist(user.getNickname())){
            return false;
        }else {
            userDao.save(user);
            return true;
        }
    }

    public boolean checkpwd(String name,String pwd){
        User user = userDao.findByName(name);
        if(user!=null && user.getPassword().equals(DigestUtils.md5DigestAsHex(pwd.getBytes()))){
            return true;
        }else
            return false;
    }

    public boolean edit(String name,String nickname,String email,String grade,String academy,String contact){

        return userDao.updateUser(name,nickname,email,grade,academy,contact);
    }
}
