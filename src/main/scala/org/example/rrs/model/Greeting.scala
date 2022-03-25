package org.example.rrs.model

import scala.beans.BeanProperty

case class Greeting(@BeanProperty message: String) {
  def this() = this("")
}
