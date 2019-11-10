package org.ankitagr.bigdata.peny.library.writer

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.model.{HierarchyInfographic, WriterModel}
import org.ankitagr.bigdata.peny.core.pipetype.FileOutPutWriter

import scala.collection.mutable

class TextWriter extends FileOutPutWriter {

  val format = "json"
  val outputDir =  "pathSavingDir"
  override def apply(writerModel: WriterModel,
                     spark: SparkSession,
                     component: HierarchyInfographic,
                     pipeData: mutable.Map[String, DataFrame]): Unit = {

    val confMap = writerModel.configuration match {
      case Some(confMapVal) => {
        confMapVal
      }
    }

    val savingDir = confMap.get(outputDir).get
    val df = pipeData.get(component.ParentName).get
    df.write.format(format).save(savingDir)
  }
}
