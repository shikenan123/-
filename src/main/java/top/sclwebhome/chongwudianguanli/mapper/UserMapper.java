package top.sclwebhome.chongwudianguanli.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import top.sclwebhome.chongwudianguanli.pojo.User;

@Mapper
public interface UserMapper {
@Select("select id,username,sex,age,UID,password,created,phone,classify from user where UID=#{UID}")
    User login(int UID);
@Select("select id,username,sex,age,UID,password,created,phone,classify from user")
    List<User> selectall();
@Select("select id,username,sex,age,UID,password,created,phone,classify from user where UID Like concat('%',#{UID},'%')")
    List<User> selectbykwdall(Integer UID);
@Select("select id,username,sex,age,UID,password,created,phone,classify from user where UID = #{UID}")
    User selectbyuid(Integer UID);
@Delete("delete from user where UID=#{UID}")
    void deleteByUid(Integer UID);
@Update("update user set username=#{username},age=#{age},sex=#{sex},classify=#{classify},phone=#{phone},password=#{password} where UID=#{UID}")
    void updateByUid(User user);
@Insert("insert into user(id,username,sex,age,UID,password,created,phone,classify) values (null,#{username},#{sex},#{age},#{UID},#{password},#{created},#{phone},#{classify})")
    void adduser(User user);
@Update("update user set  password=#{password} where UID=#{UID}")
    void passwordUpdate(User user);
}
