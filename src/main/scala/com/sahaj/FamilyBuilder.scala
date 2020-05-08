package com.sahaj

import com.sahaj.dictionary.PersonDictionary
import com.sahaj.domain.graph.{ Family, Female, Individual, Male }

object FamilyBuilder {

  def createMale(name:String, parents: Family):Male = {
    val person = Male(name, parents)
    PersonDictionary.addPerson(person)
    person
  }

  def createFemale(name:String, parents: Family):Female = {
    val person = Female(name, List(), parents)
    PersonDictionary.addPerson(person)
    person
  }

}
