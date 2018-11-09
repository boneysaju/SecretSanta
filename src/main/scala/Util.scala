import java.io._

object Util {

  def record(ppl: List[Participant]): Unit = {
    val fw = new FileWriter(new File("participants.txt"), true)
    println("Recording details of participants in participants.txt...")
    ppl.foreach{p ⇒
      fw.append(p.name + ", " + p.email + ", " + p.house + ", " + p.postcode + "\n")
    }
    println("Recording complete!")
    fw.close()
  }

  def clearFile(): Unit = {
    val fw = new FileWriter(new File("participants.txt"))
    fw.write("")
    fw.close()
  }

}
