package com.sahaj.domain.graph

import java.util.NoSuchElementException


object RelationLookup {

  def getLookUp(relation:String): Individual => List[Individual] = {
    relation.toLowerCase() match {
      case "son" => (n) => n.getFamily.getChildren.filter { case _: Male => true }
      case "daughter" =>  (n) => n.getFamily.getChildren.filter { case _: Female => true }
      case "paternal-uncle" => (n) => n.getParent.getFather.getParent.getChildren.filter(_.isInstanceOf[Male]).filter(_!=n.getParent.getFather)
      case "maternal-uncle" => (n) => n.getParent.getMother.getParent.getChildren.filter(_.isInstanceOf[Male])
      case "paternal-aunt" => (n) => n.getParent.getFather.getParent.getChildren.filter(_.isInstanceOf[Female])
      case "maternal-aunt" => (n) => n.getParent.getMother.getParent.getChildren.filter(_.isInstanceOf[Female]).filter(_!=n.getParent.getMother)
      case "sister-in-law" => (n) => n.getFamily.getSpouse(n).getParent.getChildren.filter(_.isInstanceOf[Female]).filter(_!=n)
      case "brother-in-law" => (n) => n.getFamily.getSpouse(n).getParent.getChildren.filter(_.isInstanceOf[Male]).filter(_!=n)
      case "siblings" => (n) => n.getFamily.getChildren.filter(_!=n)
      case _ => throw new NoSuchElementException("Relationship not supported.")
    }
  }

}
