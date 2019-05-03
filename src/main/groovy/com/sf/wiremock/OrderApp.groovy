package com.sf.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMockBuilder
import org.springframework.web.client.RestTemplate

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable


@RequestMapping('/serviceorder')
@RestController
class OrderApp {

    @RequestMapping(value = '/{serviceOrderId}', method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    public String echo(@PathVariable String serviceOrderId) {
        return serviceOrderId;
    }

    @RequestMapping(value = '/mock/{serviceOrderId}', method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    public String echoMock(@PathVariable String serviceOrderId) {

//        WireMockServer wireMockServer = startWireMockServer()

        RestTemplate template = new RestTemplate()

        String response = template.getForObject("http://oms.mt.bestbuy.com:8089/__admin", String.class)

//        wireMockServer.stop();
        return serviceOrderId + " mocks " + response;
    }




}
