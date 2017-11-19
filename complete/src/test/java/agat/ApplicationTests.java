/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package agat;

import agat.config.DataProvider;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
        (classes = DataProvider.class, loader = AnnotationConfigContextLoader.class)
@PropertySource("classpath:application.properties")
public class ApplicationTests {


//    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

//    @LocalServerPort
//    private int port = 0;
//
//    @Before
//    public void init() throws Exception {
//        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetCountryRequest.class));
//        marshaller.afterPropertiesSet();
//    }

    @Test
    @Ignore
    public void testSendAndReceive() {
//        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
//        GetCountryRequest request = new GetCountryRequest();
//        request.setName("Spain");
//
//        assertThat(ws.marshalSendAndReceive("http://localhost:"
//                + port + "/ws", request)).isNotNull();
    }


    @Autowired
    BuildProperties buildProperties;

    @Test
    @Ignore
    public void pro() {
        System.err.println(buildProperties.getArtifact());
    }
}