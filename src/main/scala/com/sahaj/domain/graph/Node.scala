package com.sahaj.domain.graph

case class Male(override val name:String, parent: Family) extends Individual(name, parent)

case class Female(override val name:String, var children:List[Individual], parent: Family)
  extends Individual(name, parent) {

  def addChildren(newChildren: List[Individual]) = {
    children = children ::: newChildren
  }
}


