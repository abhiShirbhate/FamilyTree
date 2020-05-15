package com.sahaj

import java.io.FileNotFoundException

import com.sahaj.dictionary.PersonDictionary
import com.sahaj.domain.graph.Female
import org.scalatest.flatspec.AnyFlatSpec

class ApplicationTest extends AnyFlatSpec {

  "Main " should "initialise Kings tree" in {
    val value = Array("/Users/abhilashshirbathe/IdeaProjects/cc/test2/src/main/resources/input.txt")
    Application.main(value)
    assert(PersonDictionary.getPerson("Shan") != null)
    assert(PersonDictionary.getPerson("Lavnya").isInstanceOf[Female])
  }

  it should "fail for non-existing file" in {
    val value = Array("/input.txt")
    assertThrows[FileNotFoundException] {
      Application.main(value)
    }
  }

}
