package com.test.sahaj.domain.graph

//import javax.management.relation.Relation

class Node(name:String, var relation: List[Relation]) {

  def marry(spouse:Node):Node = {
    val marriage = new Married(spouse)
    new Node(this.name, relation.appended(marriage))
  }
}

class Male(name:String, relation: List[Relation]) extends Node(name, relation)

class Female(name:String, relation: List[Relation]) extends Node(name, relation) {

  def addChildren(children: List[Node]) = {
      children.map(new Children(_)).foreach(relation.appended(_))
  }
}


