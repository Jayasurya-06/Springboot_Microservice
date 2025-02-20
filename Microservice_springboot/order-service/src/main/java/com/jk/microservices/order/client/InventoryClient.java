package com.jk.microservices.order.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@Slf4j
//@FeignClient(value = "inventory", url = "${inventory.service.url}")
public interface InventoryClient {

//    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @Retry(name="inventory")
//    @TimeLimiter()
     boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallBackMethod(String code,Integer quantity, Throwable throwable){
        return false;
    }

}
