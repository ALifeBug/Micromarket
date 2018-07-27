package club.fallenstar.dao;

import club.fallenstar.entity.User;

public interface UserDao extends BaseDao<User>{

    /**
     *
     * @param name
     * @return 昵称查询
     */
     User findByName(String name);

    /**
     *
     * @param phone
     * @return 电话查询
     */
     User findByPhone(String phone);

    /**
     *
     * @param name
     * @param password
     * @return 昵称密码查询
     */
     User findByNameAndPassword(String name,String password);

    /**
     *
     * @param name
     * @param nickname
     * @param email
     * @param grade
     * @param academy
     * @param contact
     * 更新用户信息
     */
     boolean updateUser(String name,String nickname,String email,String grade,String academy,String contact);


}
