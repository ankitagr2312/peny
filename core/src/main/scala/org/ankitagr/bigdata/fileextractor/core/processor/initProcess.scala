package org.ankitagr.bigdata.peny.core.processor

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.model.{HierarchyInfographic, configurationModel}
import org.ankitagr.bigdata.peny.core.stage.{FileIngestorStage, FileOutPutWriterStage}
import org.ankitagr.bigdata.peny.core.util.{FileReaderAndMapper, SparkSessionFactory}

import scala.collection.mutable.ListBuffer

object initProcess {

  var pipeInfo = new ListBuffer[HierarchyInfographic]()
  var pipeData = collection.mutable.Map[String, DataFrame]()

  def main(args: Array[String]): Unit = {

    val confModel = FileReaderAndMapper.getJobConfigurationModel(args(0))

    val spark = SparkSessionFactory.getSparkSession(confModel)
    hierarchyBuilder(confModel)
    pipeInfo.foreach(comp => doProcess(comp, confModel, spark))
  }

  def doProcess(component: HierarchyInfographic, confModel: configurationModel,
                session: SparkSession) = {
    component.PipeType match {
      case "Ingetsor" =>
        val ingestorDF = FileIngestorStage().apply(confModel, session, component)
        pipeData += (component.PipeName -> ingestorDF)

      case "Writer" =>

        FileOutPutWriterStage().apply(confModel, session, component,pipeData)
    }
  }

  def hierarchyBuilder(confModel: configurationModel): Unit = {
    confModel.ingestor.foreach(ing => pipeInfo += HierarchyInfographic
      .apply(ing.name, null, "Ingetsor"))
    confModel.outputWriter.foreach(ing => pipeInfo += HierarchyInfographic
      .apply(ing.name, ing.parentNode, "Writer"))
  }
}
