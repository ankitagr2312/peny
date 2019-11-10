package org.ankitagr.bigdata.peny.core.stage

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.FileReflection
import org.ankitagr.bigdata.peny.core.model.{HierarchyInfographic, configurationModel}
import org.ankitagr.bigdata.peny.core.pipetype.{FileOutPutWriter, JobComponent}


case class FileOutPutWriterStage() extends JobComponent {
  def apply(confModel: configurationModel,
            spark: SparkSession,
            component: HierarchyInfographic,
            pipeData: collection.mutable.Map[String, DataFrame]): Unit = {


    val writerModel = confModel.outputWriter
      .filter(writer => component.PipeName.equals(writer.name)).head

    val writerObject = initializeObject(writerModel.`type`)
    writerObject.apply(writerModel,spark,component,pipeData)
  }

  def initializeObject(className:String):FileOutPutWriter ={
    val classMap =  FileReflection.getClassesMap()
    val ingestorClass = Class.forName(classMap.get(className).get)
    ingestorClass.newInstance().asInstanceOf[FileOutPutWriter]
  }
}
