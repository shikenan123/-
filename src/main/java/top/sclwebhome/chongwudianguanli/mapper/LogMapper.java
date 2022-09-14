package top.sclwebhome.chongwudianguanli.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import top.sclwebhome.chongwudianguanli.pojo.Log;

@Mapper
public interface LogMapper {
    @Select("select ID,UserID,event,time from log where UserID=#{UserID}")
    List<Log> selectbyuserid(int userID);
    @Select("select ID,UserID,event,time from log")
    List<Log> selectall();
    @Insert("insert into log(ID,UserID,event,time) values (null,#{UserID},#{event},#{time})")
    void addlog(Log log);
}
