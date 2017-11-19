package agat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.annotation.PostConstruct;
import java.io.IOException;

@EnableWs
@Configuration
@PropertySource("classpath:application.properties")
public class WebServiceConfig extends WsConfigurerAdapter {

    //НАЗВАНИЕ СЕРВИСА (конец адресной строки):
    //////////////////////////////////////////
    private static final String SERVICE_NAME = "photo";
    //////////////////////////////////////////

    @Value("${service.port.name}")
    private String PORT_NAME;
    @Value("${service.location.uri}")
    private String LOCATION_URL;
    @Value("${service.target.namespace}")
    private String TARGET_NAMESPACE;

    @Autowired
    BuildProperties buildProperties;

    @PostConstruct
    @Bean
    public String address() {
        String addr = "http://localhost:8080" + LOCATION_URL + "/" + SERVICE_NAME + ".wsdl";

        System.err.println("СЕРВИС ДОСТУПЕН ПО АДРЕССУ: " + addr);

        return addr;
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, LOCATION_URL + "/*");
    }

    @Bean(name = SERVICE_NAME)
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema getSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(PORT_NAME);
        wsdl11Definition.setLocationUri(LOCATION_URL);
        wsdl11Definition.setTargetNamespace(TARGET_NAMESPACE);
        wsdl11Definition.setSchema(getSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema getSchema() throws IOException {
        return new SimpleXsdSchema(
                new ClassPathResource("xml/" + buildProperties.getArtifact() + "_schema1.xsd"));
    }
}
