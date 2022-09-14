package top.sclwebhome.chongwudianguanli.services;

import java.util.List;

import top.sclwebhome.chongwudianguanli.pojo.Pet;

public interface PetServices {
    List<Pet> selectbykwdall(Integer UID);
    List<Pet> findAll();
    void deleteByUid(Integer UID,Integer UserID);
    Pet selectbyuid(Integer UID);
    void updateByUID(Pet pet,Integer userID);
    void addPet(Pet pet,Integer userID);
    List<Pet> selectbykwdhf(Integer UID);
}