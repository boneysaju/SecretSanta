import java.io.{File, FileWriter}
import java.nio.file.{Files, Paths}

import scala.io.StdIn

object TextFile {

  /** Loading data from desired text file **/
  def loadParticipants(): List[Participant] = {
    val fileName = StdIn.readLine("How have you named the file(including the extension, e.g. participants.txt)? ")

    println("Trying to locate the file...")
    val path = Paths.get(System.getProperty("user.dir") + "/" + fileName)
    println(s"with path $path")
    Files.exists(path) match {
      case true  ⇒ println("File located!")
      case false ⇒
        println("File does not exist. Try again!")
        loadParticipants()
    }

    println(s"\nGetting information from $fileName")
    io.Source.fromFile(fileName).getLines().toList.flatMap{ x ⇒
      val l = x.split(",").map(_.trim)
      if(l.length == 4) {
        println("Getting participant with name " + l(0) + " and email " + l(1) + "...")
        Some(Participant(l(0), l(1), l(2), l(3)))
      }
      else None
    }
  }

  /** Recording the details of the given list of participants to participants.txt **/
  def record(ppl: List[Participant]): Unit = {
    val fw = new FileWriter(new File("participants.txt"), true)
    println("Recording details of participants in participants.txt...")
    ppl.foreach{p ⇒ fw.append(p.name + "," + p.email + "," + p.house + "," + p.postcode + ",\n")}
    println("Recording complete!")
    fw.close()
  }

  /** Deleting the text in the file **/
  def clearFile(): Unit = {
    val fw = new FileWriter(new File("participants.txt"))
    fw.write("")
    println("File cleared!")
    fw.close()
  }

  def addTestData(): Unit = {
    clearFile()
    record(List(
      Participant("Gary", "gary@email.com", "25", "BN12 4NS"),
      Participant("Tony", "tony@email.com", "25", "BN12 4NS"),
      Participant("John", "john@email.com", "25", "BN12 4NS"),
      Participant("Paul", "paul@email.com", "25", "BN12 4NS"),
      Participant("Fred", "fred@email.com", "25", "BN12 4NS"),
      Participant("Anna", "anna@email.com", "25", "BN12 4NS")))
    println("Test data created!")
  }

}
