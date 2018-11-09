import java.io._

object Util {

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
