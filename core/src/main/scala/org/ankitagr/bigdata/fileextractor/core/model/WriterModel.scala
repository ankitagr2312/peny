package org.ankitagr.bigdata.peny.core.model

case class WriterModel(name: String,
                       `type`: String,
                       configuration: Option[Map[String, String]],
                       parentNode: String)
