object File {

  def loadParticipants(): List[Participant] = {
    println("Getting information from participants.txt...")
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

}
