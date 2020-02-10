package com.test.sahaj.domain.graph

import com.sahaj.domain.graph.{Female, Male, RelationFinder}
import org.scalatest.FlatSpec

class PersonTest extends FlatSpec {

  "Given name of children" should "print father" in {
    val mother = new Female("dabi")
    val father = new Male("Abhi")
    val couple = father.marry(mother)
    val child1 = new Male("Child1")
    mother.addChildren(List(child1))
    val relationFinder = new RelationFinder(couple)

    val result = relationFinder.findFor("Abhi", "Son")
    println(result)
    assert(result.equals(List(child1)))
  }

//  "Given a ‘name’ and a ‘relationship’," should "output the people corresponding to the relationship in the order " +
//    "in• which they were added to the family tree" {
//      assert(true)
//    }
//
//  it should "produce NoSuchElementException when head is invoked" in {
//    assertThrows[NoSuchElementException] {
//      Set.empty.head
//    }
//  }
}
