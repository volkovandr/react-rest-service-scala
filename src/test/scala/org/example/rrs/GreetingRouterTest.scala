package org.example.rrs

import org.assertj.core.api.Assertions.assertThat
import org.example.rrs.model.Greeting
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.WebTestClient.{RequestBodySpec, RequestHeadersUriSpec}

@ExtendWith(Array(classOf[SpringExtension]))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingRouterTest {

  @Autowired
  private var client: WebTestClient = _

  @Test
  def testHello(): Unit = {
    client
      .get()
      .uri("/hello").asInstanceOf[RequestHeadersUriSpec[RequestBodySpec]]
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().isOk
      .expectBody(classOf[Greeting])
      .value((greeting: Greeting) => assertThat(greeting.message).isEqualTo("Hello, Spring!"))
  }

}
