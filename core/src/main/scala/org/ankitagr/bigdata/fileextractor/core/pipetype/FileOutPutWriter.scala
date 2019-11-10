package org.ankitagr.bigdata.peny.core.pipetype

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.model.{HierarchyInfographic, WriterModel, configurationModel}

abstract class FileOutPutWriter extends  {

  def apply(writerModel: WriterModel,
            spark: SparkSession,
            component: HierarchyInfographic,
            pipeData:collection.mutable.Map[String, DataFrame])

}
