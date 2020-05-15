package com.sahaj.input

import com.sahaj.dictionary.PersonDictionary
import com.sahaj.domain.graph.{ Female, Male }
import org.scalatest._

class RequestParserSpec extends FlatSpec with BeforeAndAfterAll {

  val parser = new RequestParser()

  override def beforeAll {
    val persoan = new Male("Test1", null)
    val persoan2 = new Female("Test2", null)
  }

  override def afterAll {
    PersonDictionary.clear()
  }

  "Request parser" should "parse ADD request" in {
    val input1 = "ADD Test1 Test2"

    val request = parser.parse(input1)
    assert(request.isInstanceOf[Add])
  }

  it should "parse input to requests for find relationship" in {
    val input1 = "GET_RELATIONSHIP Test1 Maternal-Aunt "

    val request = parser.parse(input1)
    assert(request.isInstanceOf[FindRelation])
  }

  it should "Parse add child to requests" in {
    val input1 = "ADD_CHILD Test2 Aria Female"

    val request = parser.parse(input1)
    assert(request.isInstanceOf[AddChildren])

  }

  it should "parse mary to marriage request" in {
    val input1 = "MARY Test1 Test2"
    val request = parser.parse(input1)
    assert(request.isInstanceOf[Marriage])

  }

  it should "throw NoSuchException for Invalid command" in {

    assertThrows[NoSuchMethodException] {
      val input1 = "UNKNOWN King Queen"
      val request = parser.parse(input1)
    }
  }
}
