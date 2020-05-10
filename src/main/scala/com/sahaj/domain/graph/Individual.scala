package com.sahaj.domain.graph

import java.util.NoSuchElementException

import com.sahaj.dictionary.PersonDictionary

class Individual(name:String, parent:Family) {
  PersonDictionary.addPerson(this)

  var family: Family = _
  def name():String = name
  def getParent:Family = parent
  def getFamily = family
  def setFamily(family: Family) = this.family = family
}

object Individual {
  implicit def toMale(ind:Individual):Male = {
    ind match {
      case _:Male => ind.asInstanceOf[Male]
      case _ => throw new NoSuchElementException("")
    }
  }

  implicit def toFemale(ind:Individual):Female = {
    ind match {
      case _:Female => ind.asInstanceOf[Female]
      case _ => throw new NoSuchElementException("")
    }
  }
}
