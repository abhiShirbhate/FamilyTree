package com.sahaj.input

import com.sahaj.dictionary.PersonDictionary
import com.sahaj.domain.graph.RelationLookup

sealed trait Request {

  def execute() = {
    this match {
      case AddChildren(name, childName, gender) =>
      case FindRelation(name, relation) =>
        val person = PersonDictionary.getPerson(name)
        RelationLookup.getLookUp(relation)(person).foreach(println(_))
    }
  }
}

case class AddChildren(name:String, childName:String, gender:String) extends Request
case class FindRelation(name:String, relation:String) extends Request
