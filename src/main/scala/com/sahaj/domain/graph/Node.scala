package com.sahaj.domain.graph

//import javax.management.relation.Relation

class Node(val name:String, var relations: List[Relation]) {

  def getSpouse() = {
    relations.filter {
      case _:Married => true
    }.map(_.getNode)
  }

  def getParents() = {
    relations.filter {
      case _:Parent => true
    }.map(_.getNode)
  }

  def getChildren():List[Node] = {
    relations.filter {
      case _:Children => true
    }.map(_.getNode)
  }

  def addRelation(relation:Relation) ={
    this.relations.appended(relation)
    this
  }

  def marry(spouse:Node):Node = {
    val husband = new Married(spouse)
    val wife = new Married(this)
    spouse.addRelation(wife)
    new Node(this.name, relations.appended(husband))
  }
}

class Male(name:String, relation: List[Relation] = List()) extends Node(name, relation)

class Female(name:String, relation: List[Relation] = List()) extends Node(name, relation) {

  def addChildren(children: List[Node]) = {
    val parent = new Parent(this)
    children.map(_.addRelation(parent)).map(new Children(_)).foreach(relation.appended(_))
  }
}


