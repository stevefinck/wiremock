package com.sf.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.ok
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor

@SpringBootApplication
class WiremockApplication {

	static void main(String[] args) {
		SpringApplication.run(WiremockApplication, args)
		startWireMockServer()
	}
	
	private static WireMockServer startWireMockServer() {
		WireMockServer wireMockServer = new WireMockServer(8089)

		// not you need to add the following to /etc/hosts
		// 127.0.0.1   oms.mt.bestbuy.com
		configureFor("oms.mt.bestbuy.com", 8089);
		wireMockServer.start()

		stubFor(get("/fine-with-body")
				.willReturn(ok("body content")));
	}
}
