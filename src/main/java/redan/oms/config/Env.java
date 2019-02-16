package redan.oms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redan.oms.FileUploadDao;
import redan.oms.FileUploadService;

@Configuration
public class Env {
	
	@Bean
	public FileUploadService fileUploadService() {
		return new FileUploadService();
	};

	@Bean
	public FileUploadDao fileUploadDao() {
		return new FileUploadDao();
	};

}
