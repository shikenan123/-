package top.sclwebhome.chongwudianguanli.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import top.sclwebhome.chongwudianguanli.pojo.Problem;

@Mapper
public interface ProblemMapper {
    @Select("select * from problem")
    List<Problem> findAll();
    @Select("select  * from problem where ID like concat('%',#{ID},'%')")
    List<Problem> selectbykwdall(Integer ID);
    @Delete("DELETE FROM problem WHERE ID = #{ID}")
    void deleteByid(Integer ID);
    @Select("select  * from problem where ID = #{ID}")
    Problem selectbyid(Integer ID);
    @Update("update problem set c=#{c},isi=#{isi} where ID=#{ID}")
    void updateByID(Problem problem);
    @Insert("insert into problem values(#{ID},#{title},#{body},#{pname},#{c},#{isi},#{timess})")
    void addproblem(Problem problem);
    @Select("select  * from problem where pname=#{pname}")
    List<Problem> findAllbyname(String username);
}
