package top.sclwebhome.chongwudianguanli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan

@MapperScan(value = "top.sclwebhome.chongwudianguanli.mapper")  
@ConfigurationPropertiesScan
public class ChongwudianguanliApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChongwudianguanliApplication.class, args);
    }

}
