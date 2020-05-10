package com.sahaj.input

import com.sahaj.dictionary.PersonDictionary._
import com.sahaj.domain.graph.Individual._

class RequestParser {

  def parse(input:String): Request = {
    val x = input.split(" ")
     x(0) match {
      case "START_FAMILY" => StartFamily(x(1), x(2))
      case "MARY" => Marriage(toMale(x(1)), toFemale(x(2)))
      case "ADD_CHILD" => AddChildren(x(1), x(2), x(3))
      case "GET_RELATIONSHIP" => FindRelation(x(1), x(2))
      case _ => throw new NoSuchMethodException("This command is not supported.")
    }

  }
}
