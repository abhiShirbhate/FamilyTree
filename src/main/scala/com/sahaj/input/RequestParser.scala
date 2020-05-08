package com.sahaj.input

class RequestParser {

  def parse(input:String): Request = {
    val x = input.split(" ")
    x(0) match {
      case "ADD_CHILD" => new AddChildren(x(1),x(2), x(3))
      case _ => new FindRelation(x(1), x(2))
    }
  }
}
