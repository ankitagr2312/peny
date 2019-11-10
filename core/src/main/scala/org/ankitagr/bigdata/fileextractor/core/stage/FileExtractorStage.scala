package org.ankitagr.bigdata.peny.core.stage

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.FileReflection
import org.ankitagr.bigdata.peny.core.model.{HierarchyInfographic, configurationModel}
import org.ankitagr.bigdata.peny.core.pipetype.{peny, JobComponent}

case class penyStage() extends JobComponent {

  def apply(confModel: configurationModel,
            spark: SparkSession,
            component: HierarchyInfographic,
            pipeData: collection.mutable.Map[String, DataFrame]): DataFrame = {

    val extractorModel = confModel.exatractorModel
      .filter(model => model.name.equals(component.PipeName)).head

    val extractorObject = initializeObject(extractorModel.`type`)
    extractorObject.transform(extractorModel, spark, component, pipeData)
  }

  def initializeObject(className: String): peny = {
    val classMap = FileReflection.getClassesMap()
    val ingestorClass = Class.forName(classMap.get(className).get)
    ingestorClass.newInstance().asInstanceOf[peny]
  }


}
