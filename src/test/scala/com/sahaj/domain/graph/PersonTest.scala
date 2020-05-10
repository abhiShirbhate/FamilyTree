package scala.com.sahaj.domain.graph

import com.sahaj.dictionary.PersonDictionary
import com.sahaj.domain.graph.{ Family, Female, Male, RelationLookup }
import org.scalatest.{ BeforeAndAfter, FlatSpec }

class PersonTest extends FlatSpec with BeforeAndAfter {

  after {
    PersonDictionary.clear()
  }

  "Given name of queen" should "print son" in {
    val father = new Male("King", null)
    val mother = new Female("Queen", null)
    val family = Family(father, mother)
    val child1 = new Male("Child1", family)
    mother.addChildren(List(child1))

    //find son of queen
    val queen = PersonDictionary.getPerson("Queen")
    val relationFinder = RelationLookup.getLookUp("Son")

    val result = relationFinder(queen)
    println(result)
    assert(result.equals(List(child1)))
  }

  it should "print brother-in-law" in {
    val father = new Male("King", null)
    val mother = new Female("Queen", null)
    val family = Family(father, mother)
    val child1 = new Male("Child1", family)
    val child2 = new Male("Child2", family)
    mother.addChildren(List(child1, child2))

    val princeWife = new Female("PrincesWife", null)
    val princesFamily = Family(child1, princeWife)
    //find son of queen
    val queen = PersonDictionary.getPerson("PrincesWife")
    val relationFinder = RelationLookup.getLookUp("brother-in-law")

    val result = relationFinder(queen)
    println(result)
    assert(result.equals(List(child2)))
  }

  it should "print sister-in-law" in {
    val father = new Male("King", null)
    val mother = new Female("Queen", null)
    val family = Family(father, mother)
    val child1 = new Male("Child1", family)
    val child2 = new Female("Child2", family)
    mother.addChildren(List(child1, child2))
    val princeWife = new Female("PrincesWife", null)
    val princesFamily = Family(child1, princeWife)

    //find son of queen
    val princesWife = PersonDictionary.getPerson("PrincesWife")
    val relationFinder = RelationLookup.getLookUp("sister-in-law")

    val result = relationFinder(princesWife)
    println(result)
    assert(result.equals(List(child2)))
  }

  "Given name of king" should "print daughter" in {
    val father = new Male("King", null)
    val mother = new Female("Queen", null)
    val family = Family(father, mother)
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
    val rootfather = new Male("King", null)
    val rootmother = new Female("Queen", null)
    val family = Family(rootfather, rootmother)
    val child1 = new Male("Prince", family)
    val child2 = new Male("Prince's-brother", family)
    rootmother.addChildren(List(child1, child2))

    val princeWife = new Female("PrincesWife", null)
    val princesFamily = Family(child1, princeWife)
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
    val rootfather = new Male("King", null)
    val rootmother = new Female("Queen", null)
    val family = rootfather.marry(rootmother)
    val child1 = new Male("Prince", family)
    val child2 = new Male("Prince's-brother", family)
    val child3 = new Female("Prince's-sister", family)
    rootmother.addChildren(List(child1, child2, child3))

    val princeWife = new Female("PrincesWife", null)
    val princesFamily = Family(child1, princeWife)
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
    val rootfather = new Male("King", null)
    val rootmother = new Female("Queen", null)
    val family = rootfather.marry(rootmother)
    val child1 = new Female("Princes", family)
    val child2 = new Male("Prince's-brother", family)
    rootmother.addChildren(List(child1, child2))

    val princeHusband = new Male("PrincesHusband", null)
    val princesFamily = Family(princeHusband, child1)
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
    val rootfather = new Male("King", null)
    val rootmother = new Female("Queen", null)
    val family = Family(rootfather, rootmother)
    val child1 = new Female("Princes", family)
    val child2 = new Male("Prince's-brother", family)
    val child3 = new Female("Prince's-Sister", family)
    rootmother.addChildren(List(child1, child2, child3))

    val princeHusband = new Male("PrincesHusband", null)
    val princesFamily = Family(princeHusband, child1)
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
      RelationLookup.getLookUp("grand-parents")(new Female("Test", null))
    }
  }
}
