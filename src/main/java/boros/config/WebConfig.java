package boros.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${empleosapp.ruta.imagenes}")
    private String rutaImagenes;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/"); // Windows
        registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagenes); // Windows
    }
}
