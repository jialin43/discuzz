package cn.skycer.discuzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.skycer.discuzz.mapper")
public class DiscuzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscuzzApplication.class, args);
	}

}
