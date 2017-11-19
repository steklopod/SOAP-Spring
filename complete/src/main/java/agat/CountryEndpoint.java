package agat;

import agat.toxsd.GetCountryRequest;
import agat.toxsd.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@PropertySource("classpath:application.properties")
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://agat.ru/name/";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @PayloadRoot(namespace = "http://agat.ru/name", localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }
}
