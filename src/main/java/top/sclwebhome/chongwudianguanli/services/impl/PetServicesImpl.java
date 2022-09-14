package top.sclwebhome.chongwudianguanli.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.sclwebhome.chongwudianguanli.mapper.LogMapper;
import top.sclwebhome.chongwudianguanli.mapper.PetMapper;
import top.sclwebhome.chongwudianguanli.pojo.Log;
import top.sclwebhome.chongwudianguanli.pojo.Pet;
import top.sclwebhome.chongwudianguanli.services.PetServices;

@Service
public class PetServicesImpl implements PetServices {
    @Resource
    private PetMapper petMapper;
    @Resource
    private LogMapper logMapper;
    @Override
    public List<Pet> selectbykwdall(Integer UID) {
        return petMapper.selectbykwdall(UID);
    }
    @Override
    public List<Pet> findAll() {
        return petMapper.findAll();
    }
    @Override
    public void deleteByUid(Integer UID,Integer UserID) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String created = df.format(date);
        Log log = new Log();
        log.setUserID(String.valueOf(UserID));
        log.setTime(created);
        log.setEvent("进行删除宠物信息UID为" + UID + "的操作");
        petMapper.deleteByUid(UID);
        logMapper.addlog(log);
    }
    @Override
    public Pet selectbyuid(Integer UID) {
        return petMapper.selectbyuid(UID);
    }
    @Override
    public void updateByUID(Pet pet, Integer userID) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String created = df.format(date);
        Log log = new Log();
        log.setUserID(String.valueOf(userID));
        log.setTime(created);
        log.setEvent("进行修改宠物信息的操作，修改后为" + pet.toString());
        petMapper.updateByUID(pet);
        logMapper.addlog(log);
    }
    @Override
    public void addPet(Pet pet,Integer userID) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String created = df.format(date);
        Log log = new Log();
        log.setUserID(String.valueOf(userID));
        log.setTime(created);
        log.setEvent("进行添加宠物信息的操作，添加的数据为" + pet.toString());
        petMapper.addPet(pet);
        logMapper.addlog(log);
    }
    @Override
    public List<Pet> selectbykwdhf(Integer UID) {
        return petMapper.selectbykwdhf(UID);
    }
}