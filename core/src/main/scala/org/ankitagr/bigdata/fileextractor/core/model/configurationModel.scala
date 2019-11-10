package org.ankitagr.bigdata.peny.core.model


case class configurationModel ( appName: String,
                                sparkProperties : Map[String,String],
                                exatractorModel : Seq[ExtractorModel],
                                ingestor : Seq[IngestorModel],
                                outputWriter : Seq[WriterModel]
                              )

