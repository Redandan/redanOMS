package redan.oms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redan.oms.FileUploadDao;
import redan.oms.FileUploadService;

@Configuration
public class Env {
	
	public class RedanConfig{
//		public static final String filePath = "C://temp//";
		public static final String filePath = "/tmp";
	}
	
	@Bean
	public RedanConfig redanConfig() {
		return new RedanConfig();
	};
	
	@Bean
	public FileUploadService fileUploadService() {
		return new FileUploadService();
	};

	@Bean
	public FileUploadDao fileUploadDao() {
		return new FileUploadDao();
	};

}
