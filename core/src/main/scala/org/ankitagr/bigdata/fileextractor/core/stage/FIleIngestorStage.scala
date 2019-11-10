package org.ankitagr.bigdata.peny.core.stage

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.FileReflection
import org.ankitagr.bigdata.peny.core.pipetype.{FileIngestor, JobComponent}
import org.ankitagr.bigdata.peny.core.model.{HierarchyInfographic, configurationModel}


case class FileIngestorStage() extends JobComponent{
  def apply(confModel: configurationModel,
                        spark: SparkSession,
                        component: HierarchyInfographic): DataFrame = {

  val ingestModel = confModel.ingestor
    .filter( model => model.name.equals(component.PipeName)).head
  val ingestorObject = initializeObject(ingestModel.`type`)
   ingestorObject.readData(ingestModel,spark,component)
  }

   def initializeObject(className:String):FileIngestor ={
     val classMap =  FileReflection.getClassesMap()
     val ingestorClass = Class.forName(classMap.get(className).get)
     ingestorClass.newInstance().asInstanceOf[FileIngestor]
   }

}

