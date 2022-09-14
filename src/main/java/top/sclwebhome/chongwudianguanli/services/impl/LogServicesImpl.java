package top.sclwebhome.chongwudianguanli.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.sclwebhome.chongwudianguanli.mapper.LogMapper;
import top.sclwebhome.chongwudianguanli.pojo.Log;
import top.sclwebhome.chongwudianguanli.services.LogServices;

@Service
public class LogServicesImpl implements LogServices {
    @Resource
    private LogMapper logMapper;
    @Override
    public List<Log> selectbyuserid(int userID) {
        return logMapper.selectbyuserid(userID);
    }
    @Override
    public List<Log> selectall() {
        return logMapper.selectall();
    }
}