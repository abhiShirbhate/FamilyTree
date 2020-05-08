package com.sahaj.domain.graph

import com.sahaj.dictionary.PersonDictionary

class Individual(name:String, parent:Family) {
  var family: Family = _
  def name():String = name
  def getParent:Family = parent
  def getFamily = family
  def setFamily(family: Family) = this.family = family
}

object Individual {
  def apply(name: String, parent: Family): Individual = {
    val person = new Individual(name, parent)
    PersonDictionary.addPerson(person)
    person
  }
}
