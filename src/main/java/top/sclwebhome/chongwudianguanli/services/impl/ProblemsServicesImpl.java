package top.sclwebhome.chongwudianguanli.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.sclwebhome.chongwudianguanli.mapper.ProblemMapper;
import top.sclwebhome.chongwudianguanli.pojo.Problem;
import top.sclwebhome.chongwudianguanli.services.ProblemsServices;

@Service
public class ProblemsServicesImpl  implements ProblemsServices {
    @Resource
    private ProblemMapper problemMapper;
    @Override
    public List<Problem> findAll() {
        return problemMapper.findAll();
    }
    @Override
    public List<Problem> selectbykwdall(Integer ID) {
        return problemMapper.selectbykwdall(ID);
    }
    @Override
    public void deleteByid(Integer ID) {
problemMapper.deleteByid(ID);
    }
    @Override
    public Problem selectbyid(Integer ID) {
        return problemMapper.selectbyid(ID);
    }
    @Override
    public void updateByID(Problem problem) {
problemMapper.updateByID(problem);
    }
    @Override
    public void addproblem(Problem problem) {
problemMapper.addproblem(problem);
    }
    @Override
    public List<Problem> findAllbyname(String username) {
        return problemMapper.findAllbyname(username);
    }
}