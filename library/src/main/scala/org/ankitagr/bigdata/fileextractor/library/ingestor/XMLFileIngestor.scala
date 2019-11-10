package org.ankitagr.bigdata.peny.library.ingestor

import javax.xml.XMLConstants
import javax.xml.validation.SchemaFactory
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.ankitagr.bigdata.peny.core.pipetype.FileIngestor
import org.ankitagr.bigdata.peny.core.model.{HierarchyInfographic, IngestorModel, configurationModel}

class XMLFileIngestor extends FileIngestor {

  val format = "com.databricks.spark.xml"

  def readData(confModel: IngestorModel,
               spark: SparkSession,
               component: HierarchyInfographic): DataFrame = {

    val confMap = confModel.configuration match {
      case Some(confMapVal) => {
        confMapVal
      }
    }
    val schemaPath = confMap.get("schemaFilePath").get
    val dataDirPath = confMap.get("dataDirPath").get
    val rowTag = confMap.get("rowTag").get

    val dfForSchema = spark.read.
      format(format)
      .option("rowTag", rowTag)
      .load(schemaPath)

    val factory: SchemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)

    println(dfForSchema.printSchema())

    spark.read.
      format(format)
      .option("rowTag", rowTag)
      .schema(dfForSchema.schema)
      .load(dataDirPath)

  }

}
