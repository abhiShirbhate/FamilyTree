package com.sahaj.dictionary

import com.sahaj.domain.graph.Individual

object PersonDictionary {

  private val dictionary = scala.collection.mutable.Map[String, Individual]()

  implicit def getPerson(name:String):Individual = {
      dictionary(name)
  }

  def clear(): Unit = dictionary.clear()

  def addPerson(person:Individual):Unit = {
    dictionary.addOne(person.name(), person)
  }
}
