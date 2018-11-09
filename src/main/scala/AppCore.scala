object AppCore {

  def assignParticipants(list: List[Participant]): Map[Participant, Participant] = ???

  /** Checking if the list is empty or has less than 5 participants and throws out the user if that's the case **/
  def validateList(list: List[Participant]): Unit = list match {
    case x if x.isEmpty ⇒
      println("Um, mate, I think your list is empty." +
        "\nHow about you follow the instructions again and do it right this time, ye?" +
        "\nLater!")
      System.exit(1)

    case x if x.length < 5 ⇒
      println("You need at least 5 participants in your list to be able to proceed." +
        s"\nYou only have ${list.length}. Add some more!")
      System.exit(1)

    case _ ⇒
  }

}
