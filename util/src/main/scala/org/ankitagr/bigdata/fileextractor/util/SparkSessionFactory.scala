package org.ankitagr.bigdata.peny.util

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.ankitagr.bigdata.peny.core.model.configurationModel


object SparkSessionFactory {
  def getSparkSession(confModel: configurationModel): SparkSession = {
    val conf = new SparkConf()
    confModel.saprkProperties.get.foreach(prop => conf.set(prop._1, prop._2))

    SparkSession.builder().config(conf)
      .appName("SparkXML")
      .getOrCreate()
  }

}
