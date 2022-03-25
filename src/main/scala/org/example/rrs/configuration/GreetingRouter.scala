package org.example.rrs.configuration

import org.example.rrs.components.GreetingHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates.{GET, accept}
import org.springframework.web.reactive.function.server.{RouterFunction, RouterFunctions, ServerResponse}

@Configuration(proxyBeanMethods = false)
class GreetingRouter(@Value("${helloUri}") helloUri: String) {

  @Bean
  def route(greetingHandler: GreetingHandler): RouterFunction[ServerResponse] =
    RouterFunctions
      .route(
        GET(helloUri).and(accept(MediaType.APPLICATION_JSON)),
        greetingHandler.hello)

}
