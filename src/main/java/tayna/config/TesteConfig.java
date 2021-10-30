package tayna.config;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tayna.services.DBService;

	@Configuration
	@Profile("test")
	public class TesteConfig {

		@Autowired
		private DBService dbService;

		@Bean
		public boolean instantiateDatabase() throws ParseException, java.text.ParseException {
			dbService.instantiateTestDatabase();
			return true;
		}
}
