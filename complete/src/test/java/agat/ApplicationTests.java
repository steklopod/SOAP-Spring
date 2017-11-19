package agat;

import agat.config.DataProvider;
import agat.toxsd.Country;
import agat.toxsd.GetCountryRequest;
import agat.toxsd.GetCountryResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataProvider.class, loader = AnnotationConfigContextLoader.class)
@PropertySource("classpath:application.properties")
public class ApplicationTests {

    @Autowired
    BuildProperties buildProperties;

    @Value("${service.port.name}")
    private String PORT_NAME;
    @Value("${service.location.uri}")
    private String LOCATION_URL;
    @Value("${service.target.namespace}")
    private String TARGET_NAMESPACE;

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @Before
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetCountryRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
//    @Ignore
    public void testSendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetCountryRequest request = new GetCountryRequest();
        request.setName("Spain");

        String SERVICE_NAME = "photo";
        String address = "http://localhost:8080" + LOCATION_URL;
//        String addr = "http://localhost:8080" + LOCATION_URL + "/" + SERVICE_NAME + ".wsdl";

        assertThat(ws.marshalSendAndReceive(address, request)).isNotNull();

        GetCountryResponse countryResponse = (GetCountryResponse) ws.marshalSendAndReceive(address, request);
        Country country = countryResponse.getCountry();
        System.err.println(country.getName());
        System.err.println(country.getCapital());
        System.err.println(country.getPopulation());

    }

    @Test
    public void pro() {
        System.err.println(buildProperties.getArtifact());
    }
}