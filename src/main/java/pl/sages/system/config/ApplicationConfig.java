package pl.sages.system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import pl.sages.system.SystemManager;
import pl.sages.system.SystemRestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig
{

	@Bean(name = "systemService")
	public SystemRestService createSystemRestService()
	{
		return new SystemRestService();
	}

	@Bean(name = "jacksonProvider")
	public JacksonJaxbJsonProvider jacksonJsonProvider()
	{
		JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
		jacksonJaxbJsonProvider.setMapper(new ObjectMapper());
		return jacksonJaxbJsonProvider;
	}

	@Bean
	public SystemManager createSystemManager()
	{
		return new SystemManager();
	}
}
