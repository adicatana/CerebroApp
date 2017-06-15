package group15.cerebro;

import group15.cerebro.session.multi.SessionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
	public final static Logger logger = LoggerFactory.getLogger(MainApplication.class);
	public final static SessionPool pool = new SessionPool();

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
