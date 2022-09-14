package top.sclwebhome.chongwudianguanli.services;

import java.util.List;

import top.sclwebhome.chongwudianguanli.pojo.Problem;

public interface ProblemsServices {
    List<Problem> findAll();
    List<Problem> selectbykwdall(Integer ID);
    void deleteByid(Integer ID);
    Problem selectbyid(Integer ID);
    void updateByID(Problem problem);
    void addproblem(Problem problem);
    List<Problem> findAllbyname(String username);
}