package com.sahaj

import com.sahaj.input.RequestParser

import scala.io.Source

object Main {
  val requestParser = new RequestParser()
  def main(args: Array[String]): Unit = {
    Source.fromFile("src/resources/Input.txt").getLines.foreach { x =>
      val output = requestParser.parse(x).execute()
      println(output)
    }
  }
}
