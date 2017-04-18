package org.t4atf;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableConfigurationProperties(DemoApplication.DemoProperties.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@org.springframework.web.bind.annotation.RestController
	public static class RestController {

		@Autowired
		private DemoPropInterface properties;

		@GetMapping
		public String get() {
			return properties.getProp() == null ? "null" : properties.getProp();
		}
	}

	@ConfigurationProperties(prefix = "demo")
	@Validated
	public static class DemoProperties implements DemoPropInterface {

		@NotNull
		private String prop;

		public void setProp(String prop) {
			this.prop = prop;
		}

		public String getProp() {
			return prop;
		}
	}

	public interface DemoPropInterface {
		void setProp(String prop);
		String getProp();
	}
}
