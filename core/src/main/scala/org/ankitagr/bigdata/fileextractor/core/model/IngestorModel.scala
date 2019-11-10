package org.ankitagr.bigdata.peny.core.model

case class IngestorModel (name: String,
                          `type` : String,
                         configuration : Option[Map[String,String]]
                         )
