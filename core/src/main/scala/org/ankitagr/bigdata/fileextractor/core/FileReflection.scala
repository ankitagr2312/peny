package org.ankitagr.bigdata.peny.core

import org.ankitagr.bigdata.peny.core.pipetype._
import org.reflections.Reflections

import scala.collection.JavaConversions._
import scala.collection.mutable.{Map => MutableMap}

object FileReflection {

  def getClassesMap(): MutableMap[String, String] = {

    val classNameCollection: MutableMap[String, String] = MutableMap.empty

    val projectArtifact = "org.ankitagr.bigdata"

    val reflections = new Reflections(projectArtifact)
    val ingestor = reflections.getSubTypesOf(classOf[FileIngestor]).toList
    val outPutWriter = reflections.getSubTypesOf(classOf[FileOutPutWriter])

    val classAll = ingestor ++ outPutWriter
    classAll.foreach(x => classNameCollection.putIfAbsent(x.getSimpleName, x.getCanonicalName))
    classNameCollection
  }
}





















