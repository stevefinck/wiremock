package com.sf.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.ok
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor

@SpringBootApplication
class WiremockApplication {

	static void main(String[] args) {
		SpringApplication.run(WiremockApplication, args)
	}

	@Bean(name = 'wireMockServer')
	private static WireMockServer wireMockServer() {
		WireMockServer wireMockServer = new WireMockServer(8081)

		// note you need to add the following to /etc/hosts
		// 127.0.0.1   oms.mt.sf.com
		configureFor("oms.mt.sf.com", 8081);
		wireMockServer.start()

		stubFor(get("/order")
				.willReturn(ok("orderId: 32, sku: 6")));

		stubFor(get("/order-payment")
				.willReturn(ok("orderId: 32, cc: visa, price: 32")));

		return wireMockServer
	}
}
