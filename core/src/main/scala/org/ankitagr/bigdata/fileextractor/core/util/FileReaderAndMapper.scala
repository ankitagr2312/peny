package org.ankitagr.bigdata.peny.core.util

import org.ankitagr.bigdata.peny.core.model.configurationModel
import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.io.Source

object FileReaderAndMapper extends java.io.Serializable {

  def getJobConfigurationModel(xmlPath: String): configurationModel = {

    implicit val formats = DefaultFormats
    val jsonConfFile = Source.fromFile(xmlPath).getLines.mkString
    parse(jsonConfFile).extract[configurationModel]

  }
}