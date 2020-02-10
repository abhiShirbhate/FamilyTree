package com.sahaj.domain.graph

abstract class Relation(node:Node){
  def getNode = node
}

class Parent(node:Node) extends Relation(node:Node)

class Married(node:Node) extends Relation(node:Node)

class Children(node:Node) extends Relation(node:Node)
