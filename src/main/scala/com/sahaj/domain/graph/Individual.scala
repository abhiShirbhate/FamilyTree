package com.sahaj.domain.graph

import java.util.NoSuchElementException

import com.sahaj.dictionary.PersonDictionary

class Individual(name:String, parent:Option[Family]) {
  PersonDictionary.addPerson(this)

  var family: Option[Family] = None
  def name():String = name
  def getParent:Option[Family] = parent
  def getFamily = {
    family
  }
  def setFamily(family: Family) = this.family = Some(family)
}

object Individual {
  implicit def toMale(ind:Individual):Male = {
    ind match {
      case _:Male => ind.asInstanceOf[Male]
      case _ => throw new IllegalArgumentException("Invalid argument given for casting.")
    }
  }

  implicit def toFemale(ind:Individual):Female = {
    ind match {
      case _:Female => ind.asInstanceOf[Female]
      case _ => throw new IllegalArgumentException("Invalid argument given for casting.")
    }
  }
}
