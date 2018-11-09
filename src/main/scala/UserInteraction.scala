import scala.io.StdIn

object UserInteraction {

  /** The main function that runs the programme **/
  def start(): Unit = {
    StdIn.readLine() match {
      case "1" =>
        confirm("Have you provided a file named participants.txt in the correct format:" +
          "\nname,email,house number/name,postcode (each on a new line)?")
        val ppl: List[Participant] = File.loadParticipants()

        /** Checking if the list is empty or has less than 5 participants and throws out the user if that's the case **/
        ppl match {

          case x if x.isEmpty ⇒
            println("Um, mate, I think your list is empty." +
              "\nHow about you follow the instructions again and do it right this time, ye?" +
              "\nLater!")
            System.exit(1)

          case x if x.length < 5 ⇒
            println("You need at least 5 participants in your list to be able to proceed." +
              s"\nYou only have ${ppl.length}. Add some more!")
            System.exit(1)

        }
        if(ppl.isEmpty) {
        }

        /** Checking with the user if the correct number of participants is found **/
        println(s"I managed to find ${ppl.length} participants with correctly formatted details.")
        confirm("Is that correct?")

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
        println("The instructions aren't that hard. Enter either 1 or 2. Again!")
        start()
    }
    println("Moving on to assigning gifters to giftees...")
    confirm("Do you want to proceed?")
    //randomiseParticipants

  }

  /** Asking for confirmation from the user on whether to proceed or restart **/
  def confirm(s: String): Unit = StdIn.readLine(s + "(Y/N) ").toLowerCase() match {
    case "y" ⇒ println("Awesome! Moving on...")

    case "n" ⇒
      println("Sorry to hear that, pal! Can't do much about it though :/")
      Thread.sleep(4000)
      println("Still here?")
      Thread.sleep(2000)
      println("Okay...")
      Thread.sleep(2000)
      println("Pfff... Let's go over it again, shall we?")
      Thread.sleep(2500)
      println("Actually, I just remembered that I need to feed my cat. Catch you in a bit, alright?")
      System.exit(1)

    case _ ⇒
      println("Sorry, I didn't quite get that...")
      confirm(s)
  }

  /** Asking the user how many participants they want to add, then asks them for the details **/
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
