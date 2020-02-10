package com.sahaj.domain.graph

import com.sahaj.domain.RelationLookup

class RelationFinder(root: Node) {

  def findFor(name:String, relation:String):List[Node] = {
    val person = findPerson(name, root)
    if(person.isEmpty)
      throw new NoSuchElementException()
    println(person.get.name)
    RelationLookup.getLookUp(relation)(person.get)
  }

  private def findPerson(name:String, n:Node):Option[Node] = {
    if(n.name == name)
        return Some(n)
    val isSpousePresent = n.getSpouse().find(_.name == name)
    if(isSpousePresent.isDefined)
      return isSpousePresent
    n.getChildren().find(findPerson(name, _).isDefined)
  }

}
