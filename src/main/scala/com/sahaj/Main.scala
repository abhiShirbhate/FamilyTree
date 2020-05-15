package com.sahaj

import com.sahaj.input.RequestParser
import com.typesafe.config.ConfigFactory

import scala.io.Source

object Main {

  val requestParser = new RequestParser()
  val conf = ConfigFactory.load

  def main(args: Array[String]): Unit = {
    val initFile = conf.getString("init")
    Source.fromFile(initFile).getLines.foreach { x =>
      requestParser.parse(x).execute()
    }
    val inputFile = args(0)
    executeCommand(inputFile)
  }

  private def executeCommand(initFile: String) = {
    Source.fromFile(initFile).getLines.foreach { x =>
      val output = requestParser.parse(x).execute()
      println(output)
    }
  }
}
