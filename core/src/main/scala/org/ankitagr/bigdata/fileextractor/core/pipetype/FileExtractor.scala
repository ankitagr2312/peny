package org.ankitagr.bigdata.peny.core.pipetype

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.model.{ExtractorModel, HierarchyInfographic, IngestorModel}


/**
  * Scope Definition of things
  * 1.Plugable
  * 2.Take dataframe and giv dataframe
  * 3.Should be in core
  **/
abstract class peny {
  def transform(exatractorModel : ExtractorModel,
                spark: SparkSession,
                component: HierarchyInfographic,
                pipeData : collection.mutable.Map[String, DataFrame]): DataFrame

}
