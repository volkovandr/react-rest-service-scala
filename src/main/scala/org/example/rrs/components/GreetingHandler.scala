package org.example.rrs.components

import org.example.rrs.model.Greeting
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.{ServerRequest, ServerResponse}
import reactor.core.publisher.Mono

@Component
class GreetingHandler(@Value("${greeting}") greeting: String) {

  def hello(request: ServerRequest): Mono[ServerResponse] =
    ServerResponse
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue(Greeting(greeting)))
}
