package com.test.sahaj.domain.graph

import com.sahaj.FamilyBuilder
import com.sahaj.dictionary.PersonDictionary
import com.sahaj.domain.graph.{ Family, Female, Male, RelationLookup }
import org.scalatest.{ BeforeAndAfter, FlatSpec }

class PersonTest extends FlatSpec with BeforeAndAfter {

  after {
    PersonDictionary.clear()
  }

  "Given name of queen" should "print son" in {
    val father = FamilyBuilder.createMale("King", null)
    val mother = FamilyBuilder.createFemale("Queen", null)
    val family = Family(father, mother)
    val child1 = FamilyBuilder.createMale("Child1", family)
    mother.addChildren(List(child1))

    //find son of queen
    val queen = PersonDictionary.getPerson("Queen")
    val relationFinder = RelationLookup.getLookUp("Son")

    val result = relationFinder(queen)
    println(result)
    assert(result.equals(List(child1)))
  }

  "Given name of king" should "print daughter" in {
    val father = FamilyBuilder.createMale("King", null)
    val mother = FamilyBuilder.createFemale("Queen", null)
    val family = Family(father, mother)
    val child1 = FamilyBuilder.createFemale("Child1", family)
    mother.addChildren(List(child1))

    //find son of queen
    val king = PersonDictionary.getPerson("King")
    val relationFinder = RelationLookup.getLookUp("daughter")

    val result = relationFinder(king)
    println(result)
    assert(result.equals(List(child1)))
  }

  it should "paternal-uncle" in {
    val rootfather = FamilyBuilder.createMale("King", null)
    val rootmother = FamilyBuilder.createFemale("Queen", null)
    val family = Family(rootfather, rootmother)
    val child1 = FamilyBuilder.createMale("Prince", family)
    val child2 = FamilyBuilder.createMale("Prince's-brother", family)
    rootmother.addChildren(List(child1, child2))

    val princeWife = FamilyBuilder.createFemale("PrincesWife", null)
    val princesFamily = Family(child1, princeWife)
    val princeChild = FamilyBuilder.createMale("PrinceChild", princesFamily)
    princeWife.addChildren(List(princeChild))
    //find son of queen
    val princeChildFound = PersonDictionary.getPerson("PrinceChild")
    val relationFinder = RelationLookup.getLookUp("paternal-uncle")

    val result = relationFinder(princeChildFound)
    println(result)
    assert(result.equals(List(child2)))
  }

  it should "paternal-aunt" in {
    val rootfather = FamilyBuilder.createMale("King", null)
    val rootmother = FamilyBuilder.createFemale("Queen", null)
    val family = Family(rootfather, rootmother)
    val child1 = FamilyBuilder.createMale("Prince", family)
    val child2 = FamilyBuilder.createMale("Prince's-brother", family)
    val child3 = FamilyBuilder.createFemale("Prince's-sister", family)
    rootmother.addChildren(List(child1, child2, child3))

    val princeWife = FamilyBuilder.createFemale("PrincesWife", null)
    val princesFamily = Family(child1, princeWife)
    val princeChild = FamilyBuilder.createMale("PrinceChild", princesFamily)
    princeWife.addChildren(List(princeChild))
    //find son of queen
    val princeChildFound = PersonDictionary.getPerson("PrinceChild")
    val relationFinder = RelationLookup.getLookUp("paternal-aunt")

    val result = relationFinder(princeChildFound)
    println(result)
    assert(result.equals(List(child3)))
  }

  it should "maternal-uncle" in {
    val rootfather = FamilyBuilder.createMale("King", null)
    val rootmother = FamilyBuilder.createFemale("Queen", null)
    val family = Family(rootfather, rootmother)
    val child1 = FamilyBuilder.createFemale("Princes", family)
    val child2 = FamilyBuilder.createMale("Prince's-brother", family)
    rootmother.addChildren(List(child1, child2))

    val princeHusband = FamilyBuilder.createMale("PrincesHusband", null)
    val princesFamily = Family(princeHusband, child1)
    val princeChild = FamilyBuilder.createMale("PrinceChild", princesFamily)
    child1.addChildren(List(princeChild))
    //find son of queen
    val princeChildFound = PersonDictionary.getPerson("PrinceChild")
    val relationFinder = RelationLookup.getLookUp("maternal-uncle")

    val result = relationFinder(princeChildFound)
    println(result)
    assert(result.equals(List(child2)))
  }

  it should "maternal-aunt" in {
    val rootfather = FamilyBuilder.createMale("King", null)
    val rootmother = FamilyBuilder.createFemale("Queen", null)
    val family = Family(rootfather, rootmother)
    val child1 = FamilyBuilder.createFemale("Princes", family)
    val child2 = FamilyBuilder.createMale("Prince's-brother", family)
    val child3 = FamilyBuilder.createFemale("Prince's-Sister", family)
    rootmother.addChildren(List(child1, child2, child3))

    val princeHusband = FamilyBuilder.createMale("PrincesHusband", null)
    val princesFamily = Family(princeHusband, child1)
    val princeChild = FamilyBuilder.createMale("PrinceChild", princesFamily)
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
      RelationLookup.getLookUp("grand-parents")(_)
    }
  }
}
