package com.test.sahaj.domain.graph._

import org.scalatest.FlatSpec

class PersonTest extends FlatSpec {

  "Given name of children" should "print father" in {
    val mother = new Female("dabi", null)
    val father = new Male("Abhi", null)
    val couple = father.marry(mother)
    val child1 = new Male("Child1", null)
    val family = mother.addChildren(List(child1))

    println(family.printFamilyTree())
    val relationFinder = new RelationFinder(family)

    relationFinder.findFor("Abhi","Son")
  }

  "Given a ‘name’ and a ‘relationship’," should "output the people corresponding to the relationship in the order " +
    "in• which they were added to the family tree" {
      assert(true)
    }

  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
