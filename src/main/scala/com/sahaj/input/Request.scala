package com.sahaj.input

import com.sahaj.dictionary.PersonDictionary._
import com.sahaj.domain.graph.{ Female, Individual, Male, RelationLookup }

sealed trait Request {
  def execute():String
}

case class AddChildren(person:Individual, childName:String, gender:String) extends Request {
  override def execute(): String = {
    person.getFamily.addChild(childName, gender)
    "CHILD_ADDITION_SUCCEEDED"
  }
}

case class FindRelation(person:Individual, relation:String) extends Request {
  override def execute(): String = {
    try {
      val output = RelationLookup.getLookUp(relation)(person).map(_.name())
      if(output.isEmpty) "NONE"
      else output.mkString(" ")
    } catch {
      case x:Exception => "PERSON_NOT_FOUND"
    }
  }
}

case class Marriage(groom: Male, bridge: Female) extends Request {
  override def execute(): String = {
    try {
      groom.marry(bridge)
      "OPERATION_SUCCEEDED"
    } catch {
      case _:NoSuchElementException => "PERSON_NOT_FOUND"
    }
  }
}

case class StartFamily(maleName:String, femaleName: String) extends Request {
  override def execute(): String = {
    try {
      val male = new Male(maleName, null)
      val female = new Female(femaleName, null)
      male.marry(female)
      "OPERATION_SUCCEEDED"
    } catch {
      case _:NoSuchElementException => "PERSON_NOT_FOUND"
      case _:Exception => "OPERATION_FAILED"
    }
  }
}
