import scala.io.StdIn

object UserInteraction {

  /** Asking for confirmation from the user on whether to proceed or restart **/
  def confirm(s: String, f: ⇒ Unit = System.exit(1)): Unit = StdIn.readLine(s + "(Y/N) ").toLowerCase() match {
    case "y" ⇒ println("Awesome! Moving on...")

    case "n" ⇒
      println("Tough luck, pal, sorry to hear that!")
      Thread.sleep(4000)
      println("Still here?")
      Thread.sleep(2000)
      println("Okay...")
      Thread.sleep(2000)
      println("Pfff... Let's go over it again, shall we?")
      Thread.sleep(2500)
      println("Actually, I just remembered that I need to feed my cat. Catch you in a bit, alright?")
      f

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
