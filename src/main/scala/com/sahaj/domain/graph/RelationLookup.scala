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

  private val son:RelationTraversal = (n:Individual) => n.getFamily.getChildren.filter(_.isInstanceOf[Male])
  private val daughter = (n:Individual) => n.getFamily.getChildren.filter(_.isInstanceOf[Female])
  private val paternalUncle= (n:Individual) => n.getParent.getFather.getParent.getChildren.filter(_.isInstanceOf[Male]).filter(_!=n.getParent.getFather)
  private val maternalUncle = (n:Individual) => n.getParent.getMother.getParent.getChildren.filter(_.isInstanceOf[Male])
  private val paternalAunt = (n:Individual) => n.getParent.getFather.getParent.getChildren.filter(_.isInstanceOf[Female])
  private val maternalAunt = (n:Individual) => n.getParent.getMother.getParent.getChildren.filter(_.isInstanceOf[Female]).filter(_!=n.getParent.getMother)

  private val sisterInLaw = (n:Individual) => {
    val spouse = n.getFamily.getSpouse(n)
    val spouseSisters: List[Individual] = spouse.getParent.getChildren.filter(_.isInstanceOf[Female]).filter(_ != spouse)
    val wivesOfSiblings: List[Individual] = n.family.getChildren.filter(c => c.isInstanceOf[Male] && c != n).map(br => br.getFamily.getSpouse(br))
    spouseSisters ++ wivesOfSiblings
  }

  private val brotherInLaw = (n:Individual) => {
    val spouse = n.getFamily.getSpouse(n)
    val spouseBrothers = spouse.getParent.getChildren.filter(_.isInstanceOf[Male]).filter(_ != spouse)
    val husbandsOfSiblings = n.family.getChildren.filter(c => c.isInstanceOf[Male] && c != n).map(br => br.getFamily.getSpouse(br))
    spouseBrothers ++ husbandsOfSiblings
  }

  private val siblings = (n:Individual) => n.getFamily.getChildren.filter(_!=n)
}
