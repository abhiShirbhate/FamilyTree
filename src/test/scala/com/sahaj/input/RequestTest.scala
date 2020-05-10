package com.sahaj.input

import com.sahaj.dictionary.PersonDictionary
import com.sahaj.domain.graph.{ Female, Male }
import org.scalatest.{ BeforeAndAfter, FlatSpec }

class RequestTest extends FlatSpec with BeforeAndAfter {

  after {
    PersonDictionary.clear()
  }

  val parser = new RequestParser()

  "Request parser" should "create family for init family request" in {
    val input1 = "START_FAMILY King Queen"
    val request = parser.parse(input1)
    request.execute()

    val king = PersonDictionary.getPerson("King")
    assert(king != null)
    val queen = PersonDictionary.getPerson("Queen")
    assert(queen != null)

    assert(king.getFamily.getSpouse(king) == queen)

  }

  it should "create child relationship for Add_CHILD command" in {
    val input1 = "START_FAMILY King Queen"
    val request = parser.parse(input1)
    request.execute()
    val input2 = "ADD_CHILD Queen Princess Female"
    parser.parse(input2).execute()

    val king = PersonDictionary.getPerson("King")
    assert(king != null)
    assert(king.getFamily.getChildren.size == 1)
    assert(king.getFamily.getChildren(0).name() == "Princess")

  }

  it should "not create child relationship for Add_CHILD command on male" in {
    val input1 = "START_FAMILY King Queen"
    val request = parser.parse(input1)
    request.execute()
    val input2 = "ADD_CHILD King Princess Female"
    parser.parse(input2).execute()

    val king = PersonDictionary.getPerson("King")
    assert(king != null)
    assert(king.getFamily.getChildren.size == 1)
    assert(king.getFamily.getChildren(0).name() == "Princess")

  }

  it should "get daughter for command GET_RELATIONSHIP" in {
    val input1 = "START_FAMILY King Queen"
    val request = parser.parse(input1)
    request.execute()
    val input2 = "ADD_CHILD Queen Princess Female"
    parser.parse(input2).execute()
    val input3 = "GET_RELATIONSHIP Queen Daughter"
    parser.parse(input3).execute()

    val king = PersonDictionary.getPerson("King")
    assert(king != null)
    assert(king.getFamily.getChildren.size == 1)
    assert(king.getFamily.getChildren(0).name() == "Princess")

  }

  it should "get NONE for non existing GET_RELATIONSHIP" in {
    val input1 = "START_FAMILY King Queen"
    val request = parser.parse(input1)
    request.execute()
    val input2 = "ADD_CHILD Queen Princess Female"
    parser.parse(input2).execute()
    val input3 = "GET_RELATIONSHIP Queen Son"
    val output = parser.parse(input3).execute()

    assert(output == "NONE")
  }

  it should "create family relation for command MARY" in {
    val kingCreated = new Male("King", null)
    val QueenCreated = new Female("Queen", null)
    val input3 = "MARY King Queen"
    parser.parse(input3).execute()

    val king = PersonDictionary.getPerson("King")
    assert(king != null)
    assert(king.getFamily.getSpouse(king).name == "Queen")
  }

  it should "not create family relation with NonExisting person for command MARY" in {
    val input3 = "MARY King Queen"
    val output = parser.parse(input3).execute()
    assert(output == "PERSON_NOT_FOUND")
  }

}
