package org.example.rrs

import org.example.rrs.components.GreetingClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

object Application extends App {
  val context = SpringApplication.run(classOf[Application], args: _*)

  val client = context.getBean(classOf[GreetingClient])

  println(s">> message ${client.getMessage().block()}")
}