package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"service", "exceptions", "model"})
public class AppConfig {

    //Placeholder for future bean definitions
}
