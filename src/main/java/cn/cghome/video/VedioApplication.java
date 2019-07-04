package cn.cghome.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.cghome.video.mapper")
public class VedioApplication {

    public static void main(String[] args) {
        SpringApplication.run(VedioApplication.class, args);
    }

}
