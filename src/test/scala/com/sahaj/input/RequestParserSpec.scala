package com.sahaj.input

import org.scalatest._

class RequestParserSpec extends FlatSpec {

  "Request parser" should "Parse input to requests" in {
    val input1 = "ADD_CHILD Chitra Aria Female"
    val parser = new RequestParser()

    val request = parser.parse(input1)
    assert(request.isInstanceOf[AddChildren])

  }

  it should "parse input to requests for find relationship" in {
    val input1 = "GET_RELATIONSHIP Lavnya Maternal-Aunt "
    val parser = new RequestParser()

    val request = parser.parse(input1)
    assert(request.isInstanceOf[FindRelation])

  }
}
