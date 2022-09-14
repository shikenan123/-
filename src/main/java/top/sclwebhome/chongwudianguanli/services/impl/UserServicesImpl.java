package top.sclwebhome.chongwudianguanli.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.sclwebhome.chongwudianguanli.mapper.LogMapper;
import top.sclwebhome.chongwudianguanli.mapper.UserMapper;
import top.sclwebhome.chongwudianguanli.pojo.Log;
import top.sclwebhome.chongwudianguanli.pojo.User;
import top.sclwebhome.chongwudianguanli.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LogMapper logMapper;
    @Override
    public User login(int UID) {
        return userMapper.login(UID);
    }
    @Override
    public List<User> selectall() {
        return userMapper.selectall();
    }
    @Override
    public List<User> selectbykwdall(Integer UID) {
        return userMapper.selectbykwdall(UID);
    }
    @Override
    public User selectbyuid(Integer UID) {
        return userMapper.selectbyuid(UID);
    }
    @Override
    public void deleteByUid(Integer UID,Integer UserID) {
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created = df.format(date);
            Log log = new Log();
            log.setUserID(String.valueOf(UserID));
            log.setTime(created);
            log.setEvent("进行删除用户UID为" + UID + "的操作");
            userMapper.deleteByUid(UID);
            logMapper.addlog(log);
        }catch (Exception e){
        }
    }
    @Override
    public void updateByUid(User user,Integer userID) {
       try {
           Date date=new Date();
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String created = df.format(date);
           Log log=new Log();
           log.setUserID(String.valueOf(userID));
           log.setTime(created);
           log.setEvent("进行修改用户的操作，修改后的数据为"+user.toString());
           userMapper.updateByUid(user);
           logMapper.addlog(log);
       }catch (Exception e){
       }
    }
    @Override
    public void adduser(User user,Integer userID) {
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created = df.format(date);
            Log log = new Log();
            log.setUserID(String.valueOf(userID));
            log.setTime(created);
            log.setEvent("进行添加用户的操作，添加后的数据为" + user.toString());
            userMapper.adduser(user);
            logMapper.addlog(log);
        }catch (Exception e){
        }
    }
    @Override
    public void passwordUpdate(User user) {
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created = df.format(date);
            Log log = new Log();
            log.setUserID(String.valueOf(user.getUID()));
            log.setTime(created);
            log.setEvent("进行修改自己密码的操作，修改后的数据为" + user.getPassword());
            userMapper.passwordUpdate(user);
            logMapper.addlog(log);
        }catch (Exception e){
        }
    }
}