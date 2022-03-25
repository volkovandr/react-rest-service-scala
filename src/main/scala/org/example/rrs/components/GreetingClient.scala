package org.example.rrs.components

import org.example.rrs.model.Greeting
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.{RequestBodySpec, RequestHeadersUriSpec}
import reactor.core.publisher.Mono

@Component
class GreetingClient(
                      builder: WebClient.Builder,
                      @Value("${helloUri}") helloUri: String
                    ) {

  private val baseUrl = "http://localhost:8080"

  private val client: WebClient = builder.baseUrl(baseUrl).build()

  def getMessage(): Mono[String] = client
    .get()
    .uri(helloUri).asInstanceOf[RequestHeadersUriSpec[RequestBodySpec]]
    .accept(MediaType.APPLICATION_JSON)
    .retrieve()
    .bodyToMono(classOf[Greeting])
    .map(_.message)

}
