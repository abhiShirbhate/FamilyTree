package com.test.sahaj.domain

class RelationFinder(root:Family) {

  def isSon(person: Person, name:String) = {
    person.
    true
  }

  def findFor(name1:String, name2:String): String = {
    val person1 = findPerson(name1, root)
    if (isSon(person1, name2)) return "Son"
    if (isSon()) return "Son"
    if (isSon()) return "Son"
    return "Unknown"
  }

  private def findPerson(name:String, parent:Family): Person = {
    if(parent.father.name.equals(name))
      return parent.father
    if(parent.mother.name.equals(name))
      return parent.mother
    parent.children.find(_.name.equals(name)).get
  }
}
