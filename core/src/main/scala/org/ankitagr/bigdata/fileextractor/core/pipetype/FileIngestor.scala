package org.ankitagr.bigdata.peny.core.pipetype

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.model.{HierarchyInfographic, IngestorModel, configurationModel}

abstract class FileIngestor extends JobComponent {

  def readData(ingestModel:IngestorModel,
               spark:SparkSession,
               component:HierarchyInfographic):DataFrame

}
