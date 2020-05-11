package com.sahaj.input

import com.sahaj.Messages
import com.sahaj.Messages._
import com.sahaj.dictionary.PersonDictionary._
import com.sahaj.domain.graph.Individual._
import com.sahaj.domain.graph.{ Female, Individual, Male, RelationLookup }

sealed trait Request {
  def execute():String
}

case class AddChildren(person:String, childName:String, gender:String) extends Request {
  override def execute(): String = {
    try {
      toFemale(person).addChild(childName, gender)
      CHILD_ADDITION_SUCCEEDED
    } catch {
      case _:IllegalArgumentException => CHILD_ADDITION_FAILED
      case _:NoSuchElementException => PERSON_NOT_FOUND
    }
  }
}

case class FindRelation(person:String, relation:String) extends Request {
  override def execute(): String = {
    try {
      val output = RelationLookup.getLookUp(relation)(person).map(_.name())
      if(output.isEmpty) NONE
      else output.mkString(" ")
    } catch {
      case x:Exception => PERSON_NOT_FOUND
    }
  }
}

case class Marriage(groom: String, bridge: String) extends Request {
  override def execute(): String = {
    try {
      toMale(groom).marry(toFemale(bridge))
      OPERATION_SUCCEEDED
    } catch {
      case _:NoSuchElementException => PERSON_NOT_FOUND
    }
  }
}

case class Add(name:String, gender: String) extends Request {
  override def execute(): String = {
    gender.toLowerCase match {
      case "male" => new Male(name, null)
      case "female" => new Female(name, null)
    }
      OPERATION_SUCCEEDED
  }
}
