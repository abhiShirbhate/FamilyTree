package com.sahaj.domain.graph

class Female(override val name:String, parent: Option[Family], var children:List[Individual] = List())
  extends Individual(name, parent) {

  def addChildren(newChildren: List[Individual]) = {
    children = children ::: newChildren
  }

  def addChild(name:String, gender:String): Individual = {
    val child: Individual = gender.toLowerCase match {
      case "male" => new Male(name, this.getFamily)
      case "female" => new Female(name, this.getFamily)
    }
    children = children :+ child
    child
  }
}
