package com.sahaj.input

class RequestParser {

  def parse(input:String): Request = {
    val inputToken = input.split(" ")
     inputToken(0) match {
      case "START_FAMILY" => StartFamily(inputToken(1), inputToken(2))
      case "MARY" => Marriage(inputToken(1), inputToken(2))
      case "ADD_CHILD" => AddChildren(inputToken(1), inputToken(2), inputToken(3))
      case "GET_RELATIONSHIP" => FindRelation(inputToken(1), inputToken(2))
      case _ => throw new NoSuchMethodException("This command is not supported.")
    }
  }
}
