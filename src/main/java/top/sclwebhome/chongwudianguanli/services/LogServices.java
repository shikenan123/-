package top.sclwebhome.chongwudianguanli.services;

import java.util.List;

import top.sclwebhome.chongwudianguanli.pojo.Log;

public interface LogServices {
    List<Log> selectbyuserid(int userID);
    List<Log> selectall();
}