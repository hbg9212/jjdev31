package cafe.jjdev.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MallApplication {

	public static void main(String[] args) {
		System.out.println("[MallApplication]\t 톰켓실행전");
		SpringApplication.run(MallApplication.class, args);

		System.out.println("[MallApplication]\t 스프링구동");
		
	}

}
