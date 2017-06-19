package group15.cerebro;

import group15.cerebro.session.multi.Match;
import group15.cerebro.session.multi.SessionPool;
import group15.cerebro.session.templates.SessionManagerEngine;
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

    public static void log(SessionManagerEngine manager, String s) {
		if (manager == null) {
			return;
		}
		logger.info(s + manager.dumpManager());
	}

	public static void log(Match match, SessionManagerEngine manager, String s) {
		if (manager == null) {
			return;
		}

		if (match != null) {
			logger.info("MATCH id " + match + " :" + s + manager.dumpManager());
		} else {
			logger.error("NULL MATCH:" + s + manager.dumpManager());
		}
	}
}
