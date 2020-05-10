package com.sahaj.domain.graph

class Male(override val name:String, parent: Family) extends Individual(name, parent) {

  def marry(bride:Female):Family = Family(this, bride)
}

class Female(override val name:String, parent: Family, var children:List[Individual] = List())
  extends Individual(name, parent) {

  def addChildren(newChildren: List[Individual]) = {
    children = children ::: newChildren
  }
}
