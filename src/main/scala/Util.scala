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
    fw.close()
  }

}
