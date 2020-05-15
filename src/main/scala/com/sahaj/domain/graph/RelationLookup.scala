package com.sahaj.domain.graph

import java.util.NoSuchElementException

object RelationLookup {
  type RelationTraversal = Individual => List[Individual]

  def getLookUp(relation:String):RelationTraversal = {
    relation.toLowerCase() match {
      case "son" => son
      case "daughter" =>  daughter
      case "paternal-uncle" => paternalUncle
      case "maternal-uncle" => maternalUncle
      case "paternal-aunt" => paternalAunt
      case "maternal-aunt" => maternalAunt
      case "sister-in-law" => sisterInLaw
      case "brother-in-law" => brotherInLaw
      case "siblings" => siblings
      case _ => throw new NoSuchElementException("Relationship not supported.")
    }
  }

  private val son:RelationTraversal = (n:Individual) => n.getFamily.map(family => family.getSons()).getOrElse(List())
  private val daughter:RelationTraversal = (n:Individual) => n.getFamily.map(family => family.getDaughters()).getOrElse(List())

  private val paternalUncle:RelationTraversal = (n:Individual) => paternalGrandParents(n).map(family => family.getSons().filter(!n.getParent.map(_.getFather).contains(_))).getOrElse(List())
  private val paternalAunt:RelationTraversal = (n:Individual) => paternalGrandParents(n).map(family => family.getDaughters()).getOrElse(List())

  private val maternalUncle:RelationTraversal = (n:Individual) => getMaternalGrandParents(n).map(family => family.getSons()).getOrElse(List())
  private val maternalAunt:RelationTraversal = (n:Individual) => getMaternalGrandParents(n).map(family => family.getDaughters()).map(_.filter(!n.getParent.map(_.getMother).contains(_))).getOrElse(List())

  private val sisterInLaw:RelationTraversal = (n:Individual) => {
    val spouse: Option[Individual] = n.getFamily.map(_.getSpouse(n))
    val spouseSisters: List[Individual] = spouse.flatMap(_.getParent.map(family => family.getDaughters()).map(_.filter(!spouse.contains(_)))).getOrElse(List())

    val wivesOfSiblings = n.family.map(_.getSons().filter(_ != n).filter(_.getFamily.isDefined).map(br => br.getFamily.get.getSpouse(br)))
    spouseSisters ++ wivesOfSiblings.getOrElse(List())
  }

  private val brotherInLaw:RelationTraversal = (n:Individual) => {
    val spouse = n.getFamily.map(_.getSpouse(n))
    val spouseBrothers: List[Individual] = spouse.flatMap(_.getParent.map(_.getSons()).map(_.filter(!spouse.contains(_)))).getOrElse(List())
    val husbandsOfSiblings: List[Individual] = n.family
      .map(_.getSons().filter(_ != n).filter(_.getFamily.isDefined).map(br => br.getFamily.get.getSpouse(br)))
      .getOrElse(List())
    spouseBrothers ::: husbandsOfSiblings
  }

  private val siblings:RelationTraversal = (n:Individual) => getSiblings(n).getOrElse(List())

  private def getSiblings(n: Individual) = {
    n.getFamily.map(_.getChildren.filter(_ != n))
  }

  private def paternalGrandParents(n: Individual) = {
    n.getParent.flatMap(_.getFather.getParent)
  }

  private def getMaternalGrandParents(n: Individual) = {
    n.getParent.flatMap(_.getMother.getParent)
  }
}
