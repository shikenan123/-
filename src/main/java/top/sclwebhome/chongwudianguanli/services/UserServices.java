package top.sclwebhome.chongwudianguanli.services;

import java.util.List;

import top.sclwebhome.chongwudianguanli.pojo.User;

public interface UserServices {
    User login(int UID);
    List<User> selectall();
    List<User> selectbykwdall(Integer UID);
    User selectbyuid(Integer UID);
    void deleteByUid(Integer UID,Integer userid);
    void updateByUid(User user,Integer userID);
    void adduser(User user,Integer userID);
    void passwordUpdate(User user);
}