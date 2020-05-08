package com.sahaj

import com.sahaj.domain.graph.{ Family, Female, Male }

object Main {

  def main(args: Array[String]): Unit = {
    val king = new Male("KingShaha", null)
    val queen = new Female("QueenShaha",List(), null)
    val root = Family(king, queen)
    val children = List(Male("chit", root), Male("Ish",root), Male("vish", root))
    root.addChildren(children)

    root
  }
}
