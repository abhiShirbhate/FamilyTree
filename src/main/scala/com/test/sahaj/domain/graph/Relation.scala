package com.test.sahaj.domain.graph

import com.test.sahaj.domain.graph._

class Relation(node:Node) {
}

class Parent(node:Node) extends Relation(node:Node) {
  def getParents() = {

  }
}

class Married(node:Node) extends Relation(node:Node) {

  def getBetterHalf() = {

  }
}

class Children(node:Node) extends Relation(node:Node) {

}
