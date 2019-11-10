package org.ankitagr.bigdata.peny.core.model


case class ExtractorModel(name: String,
                          `type`: String,
                          configuration: Option[Map[String, String]])
