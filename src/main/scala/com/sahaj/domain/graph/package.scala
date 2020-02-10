package com.sahaj.domain

import com.sahaj.domain.graph.{Female, Male, Node}

object RelationLookup {

  def getLookUp(relation:String) = {
    relation match {
      case "Son" => (node:Node) => node.getChildren().filter{case _:Male=> true}
      case "Daughter" => (node:Node) => node.getChildren().filter{case _:Female=> true}
      case "Paternal-Uncle" => (node:Node) => node.getChildren()
      case "Maternal-Uncle" => (node:Node) => node.getChildren()
      case "Paternal-Aunt" => (node:Node) => node.getChildren()
      case "Maternal-Aunt" => (node:Node) => node.getChildren()
      case "Sister-In-Law" => (node:Node) => node.getChildren()
      case "Brother-In-Law" => (node:Node) => node.getChildren()
      case "Siblings" => (node:Node) => node.getChildren()
    }
  }

}
