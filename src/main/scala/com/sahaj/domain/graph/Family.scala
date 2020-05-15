package com.sahaj.domain.graph

class Family(father: Male, mother: Female) {
  def getFather: Male = father
  def getMother: Female = mother
  def getChildren: List[Individual] = mother.children

  def addChildren(child:List[Individual]) = mother.children = mother.children ++ child

  def getSpouse(n:Individual):Individual = n match {
    case _:Male => getMother
    case _ => getFather
  }

  def getSons(): List[Individual] = {
    getChildren.filter(_.isInstanceOf[Male])
  }

  def getDaughters(): List[Individual] = {
    getChildren.filter(_.isInstanceOf[Female])
  }

}

object Family {
  def apply(father: Male, mother: Female): Family = {
    val family = new Family(father, mother)
    father.setFamily(family)
    mother.setFamily(family)
    family
  }
}
