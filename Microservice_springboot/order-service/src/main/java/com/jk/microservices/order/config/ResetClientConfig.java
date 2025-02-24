package com.jk.microservices.order.config;

import com.jk.microservices.order.client.InventoryClient;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ResetClientConfig {

    @Value("${inventory.service.url}")
    private String inventoryUrl;
    @Autowired
    private final ObservationRegistry observationRegistry;

    public ResetClientConfig(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
    }


    @Bean
    public InventoryClient inventoryClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryUrl)
//                .requestFactory(getClientRequestFactory())
                .observationRegistry(observationRegistry)
                .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

//    private ClientHttpRequestFactory getClientRequestFactory() {
//        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
//                .withConnectTimeout(Duration.ofSeconds(3))
//                .withReadTimeout(Duration.ofSeconds(3));
//        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
//    }
}
