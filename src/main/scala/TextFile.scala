import java.io.{File, FileWriter}

object TextFile {

  /** Loading data from participants.txt **/
  def loadParticipants(): List[Participant] = {
    println("\nGetting information from participants.txt...")
    val fileSource = io.Source.fromFile("participants.txt")
    val test = fileSource.getLines().toList
    test.flatMap{ x ⇒
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
