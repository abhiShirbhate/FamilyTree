package com.sahaj.domain.graph

import com.sahaj.dictionary.PersonDictionary
import org.scalatest.{ BeforeAndAfter, FlatSpec }

class PersonTest extends FlatSpec with BeforeAndAfter {

  after {
    PersonDictionary.clear()
  }

  "Given name of queen" should "print son" in {
    PersonDictionary.clear()
    val father = new Male("King", None)
    val mother = new Female("Queen", None)
    Family(father, mother)
    val child1 = mother.addChild("Child1", "Male")

    //find son of queen
    val queen = PersonDictionary.getPerson("Queen")
    val relationFinder = RelationLookup.getLookUp("Son")

    val result = relationFinder(queen)
    assert(result == List(child1))
  }

  it should "not fail for not specified family" in {
    val father = new Male("King", None)
    val mother = new Female("Queen", None)
    Family(father, mother)
    mother.addChild("Child1", "Male")

    //find son of queen
    val queen = PersonDictionary.getPerson("Queen")
    val relationFinder = RelationLookup.getLookUp("sister-in-law")

    val result = relationFinder(queen)
    assert(result == List())
  }

  it should "print brother-in-law with husband of siblings" in {
    val father = new Male("King", None)
    val mother = new Female("Queen", None)
    val family = Some(Family(father, mother))
    val child1 = new Male("Child1", family)
    val child2 = new Male("Child2", family)
    mother.addChildren(List(child1, child2))

    //princes wifes family
    val father1 = new Male("jack", None)
    val mother1 = new Female("jake", None)
    val family2 = Some(Family(father1, mother1))
    val princeWife = new Female("PrincesWife", family2)
    val princeWifeSis = new Female("PrincesWifeSister", family2)
    val princeWifeSisHub = new Male("KingJohn", None)
    mother1.addChildren(List(princeWife, princeWifeSis))
    princeWifeSisHub.marry(princeWifeSis)

    val princesFamily = Family(child1, princeWife)
    //find son of queen
    val queen = PersonDictionary.getPerson("PrincesWife")
    val relationFinder = RelationLookup.getLookUp("brother-in-law")

    val result = relationFinder(queen)
    println(result)
    assert(result.equals(List(child2, princeWifeSisHub)))
  }

  it should "print sister-in-law with siblings wives" in {
    val father = new Male("King", None)
    val mother = new Female("Queen", None)
    val family = Some(Family(father, mother))
    val child1 = new Male("Child1", family)
    val child2 = new Female("Child2", family)
    mother.addChildren(List(child1, child2))

    //princes wifes family
    val father1 = new Male("jack", None)
    val mother1 = new Female("jake", None)
    val family1: Family = father1.marry(mother1)
    val princeWife = new Female("PrincesWife", Some(family1))
    val princeWifeBro = new Male("PrincesWifeBro", Some(family1))
    val SomeWitch = new Female("SomeWitch", None)
    mother1.addChildren(List(princeWife, princeWifeBro))
    princeWifeBro.marry(SomeWitch)


    child1.marry(princeWife)
    //find son of queen
    val princesWife = PersonDictionary.getPerson("PrincesWife")
    val relationFinder = RelationLookup.getLookUp("sister-in-law")

    val result = relationFinder(princesWife)
    assert(result.equals(List(child2, SomeWitch)))
  }

  "Given name of king" should "print daughter" in {
    val father = new Male("King", None)
    val mother = new Female("Queen", None)
    val family = Some(Family(father, mother))
    val child1 = new Female("Child1", family)
    mother.addChildren(List(child1))

    //find son of queen
    val king = PersonDictionary.getPerson("King")
    val relationFinder = RelationLookup.getLookUp("daughter")

    val result = relationFinder(king)
    println(result)
    assert(result.equals(List(child1)))
  }

  it should "paternal-uncle" in {
    val rootfather = new Male("King", None)
    val rootmother = new Female("Queen", None)
    val family = Some(Family(rootfather, rootmother))
    val child1 = new Male("Prince", family)
    val child2 = new Male("Prince's-brother", family)
    rootmother.addChildren(List(child1, child2))

    val princeWife = new Female("PrincesWife", None)
    val princesFamily = Some(Family(child1, princeWife))
    val princeChild = new Male("PrinceChild", princesFamily)
    princeWife.addChildren(List(princeChild))
    //find son of queen
    val princeChildFound = PersonDictionary.getPerson("PrinceChild")
    val relationFinder = RelationLookup.getLookUp("paternal-uncle")

    val result = relationFinder(princeChildFound)
    println(result)
    assert(result.equals(List(child2)))
  }

  it should "paternal-aunt" in {
    val rootfather = new Male("King", None)
    val rootmother = new Female("Queen", None)
    val family = Some(rootfather.marry(rootmother))
    val child1 = new Male("Prince", family)
    val child2 = new Male("Prince's-brother", family)
    val child3 = new Female("Prince's-sister", family)
    rootmother.addChildren(List(child1, child2, child3))

    val princeWife = new Female("PrincesWife", None)
    val princesFamily = Some(Family(child1, princeWife))
    val princeChild = new Male("PrinceChild", princesFamily)
    princeWife.addChildren(List(princeChild))
    //find son of queen
    val princeChildFound = PersonDictionary.getPerson("PrinceChild")
    val relationFinder = RelationLookup.getLookUp("paternal-aunt")

    val result = relationFinder(princeChildFound)
    println(result)
    assert(result.equals(List(child3)))
  }

  it should "maternal-uncle" in {
    val rootfather = new Male("King", None)
    val rootmother = new Female("Queen", None)
    val family = Some(rootfather.marry(rootmother))
    val child1 = new Female("Princes", family)
    val child2 = new Male("Prince's-brother", family)
    rootmother.addChildren(List(child1, child2))

    val princeHusband = new Male("PrincesHusband", None)
    val princesFamily = Some(Family(princeHusband, child1))
    val princeChild = new Male("PrinceChild", princesFamily)
    child1.addChildren(List(princeChild))
    //find son of queen
    val princeChildFound = PersonDictionary.getPerson("PrinceChild")
    val relationFinder = RelationLookup.getLookUp("maternal-uncle")

    val result = relationFinder(princeChildFound)
    println(result)
    assert(result.equals(List(child2)))
  }

  it should "maternal-aunt" in {
    val rootfather = new Male("King", None)
    val rootmother = new Female("Queen", None)
    val family = Some(Family(rootfather, rootmother))
    val child1 = new Female("Princes", family)
    val child2 = new Male("Prince's-brother", family)
    val child3 = new Female("Prince's-Sister", family)
    rootmother.addChildren(List(child1, child2, child3))

    val princeHusband = new Male("PrincesHusband", None)
    val princesFamily = Some(Family(princeHusband, child1))
    val princeChild = new Male("PrinceChild", princesFamily)
    child1.addChildren(List(princeChild))
    //find son of queen
    val princeChildFound = PersonDictionary.getPerson("PrinceChild")
    val relationFinder = RelationLookup.getLookUp("maternal-aunt")

    val result = relationFinder(princeChildFound)
    println(result)
    assert(result.equals(List(child3)))
  }

  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      RelationLookup.getLookUp("grand-parents")(new Female("Test", None))
    }
  }
}
