package top.sclwebhome.chongwudianguanli.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import top.sclwebhome.chongwudianguanli.pojo.Pet;

@Mapper
public interface PetMapper {
    @Select(" select * from pet where UID Like concat('%',#{UID},'%')")
    List<Pet> selectbykwdall(Integer UID);
    @Select("select * from pet")
    List<Pet> findAll();
    @Delete("DELETE FROM pet WHERE UID = #{UID}")
    void deleteByUid(Integer UID);
    @Select("select UID,nickname,age,sex,classify,vaccine,aggressivity,likeq,purchasenotes,mattersneedattention,offeringprice,anamnesis,drill,physicalcondition,commentone,record,dietarystructure,ifsell,times,commentthree from pet where UID = #{UID}")
    Pet selectbyuid(Integer UID);
    @Update("update pet set nickname=#{nickname},age=#{age},sex=#{sex},classify=#{classify},vaccine=#{vaccine},aggressivity=#{aggressivity},likeq=#{likeq},purchasenotes=#{purchasenotes},mattersneedattention=#{mattersneedattention},offeringprice=#{offeringprice},anamnesis=#{anamnesis},drill=#{drill},physicalcondition=#{physicalcondition},commentone=#{commentone},record=#{record}, dietarystructure=#{dietarystructure},ifsell=#{ifsell},times=#{times},commentthree=#{commentthree} where UID=#{UID}")
    void updateByUID(Pet pet);
    @Insert("INSERT INTO pet (id,UID,nickname,age,sex,classify,vaccine,aggressivity,likeq,purchasenotes,mattersneedattention,offeringprice,anamnesis,drill,physicalcondition,commentone,record,dietarystructure,ifsell,times,commentthree) VALUES (null,#{UID},#{nickname},#{age},#{sex},#{classify},#{vaccine},#{aggressivity},#{likeq},#{purchasenotes},#{mattersneedattention},#{offeringprice},#{anamnesis},#{drill},#{physicalcondition},#{commentone},#{record}, #{dietarystructure},#{ifsell},#{times},#{commentthree})")
    void addPet(Pet pet);
    @Select("select UID,nickname,age,sex,classify,vaccine,aggressivity,likeq,purchasenotes,mattersneedattention,offeringprice,anamnesis,drill,physicalcondition,commentone,record,dietarystructure,ifsell,times,commentthree from pet where UID Like concat('%',#{UID},'%')")
    List<Pet> selectbykwdhf(Integer UID);
    @Select("select UID,nickname,age,sex,classify,vaccine,aggressivity,likeq,purchasenotes,mattersneedattention,offeringprice,anamnesis,drill,physicalcondition,commentone,record,dietarystructure,ifsell,times,commentthree from pet")
    List<Pet> findAllhf();
    @Update("update pet set ifsell=#{ifsell},times=#{times} where UID=#{UID}")
    void UpdateByUid(Pet pet);
}
