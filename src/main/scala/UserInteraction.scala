import scala.io.StdIn

object UserInteraction {

  def start(choice: String): Unit = choice match {
    case "1" =>
      val ppl = File.loadParticipants()
      println(s"I managed to find ${ppl.length} participants")
      confirm()

    case "2" =>
      val ppl = inputParticipants()
      Util.record(ppl)

//    Below two are for testing only
    case "add test data" ⇒
      Util.record(List(
        Participant("Gary", "gary@email.com", "25", "BN12 4NS"),
        Participant("Tony", "tony@email.com", "25", "BN12 4NS"),
        Participant("John", "john@email.com", "25", "BN12 4NS"),
        Participant("Paul", "paul@email.com", "25", "BN12 4NS"),
        Participant("Fred", "fred@email.com", "25", "BN12 4NS"),
        Participant("Anna", "anna@email.com", "25", "BN12 4NS"))
      )

    case "clear file" ⇒
      Util.clearFile()

    case _   =>
      println("That is an invalid input. Please type 1  for Uploading a CSV file and type 2 for Entering participants manually")
      start(choice)
  }

  def confirm(): Unit = StdIn.readLine("Is that correct? (Y/N) ").toLowerCase() match {
    case "y" ⇒ println("Awesome! Moving on...")
    case "n" ⇒ println("Sorry to hear that, pal! Can't do much about it though :/")
    case _ ⇒
      println("Sorry, I didn't quite get that...")
      confirm()
  }

  def inputParticipants(): List[Participant] = {
    val numberOfPeople = StdIn.readLine("How many people are participating? (input numbers only) ").toInt
    numberOfPeople match {

      case x if x < 5 ⇒
        println("You need at least 5 participants")
        inputParticipants()

      case x if x >= 5 ⇒
        (for(i ← 1 to numberOfPeople) yield {
          println(s"\nEnter information for Participant #$i")
          val name = StdIn.readLine("Enter a name: ")
          val email = StdIn.readLine("Enter an email: ")
          val house = StdIn.readLine("Enter a house: ")
          val postcode = StdIn.readLine("Enter a postcode: ")
          Participant(name, email, house, postcode)
        }).toList
    }
  }

}
