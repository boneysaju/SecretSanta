import UserInteraction.{confirm, inputParticipants}

import scala.io.StdIn

object Main extends App {

  println("Welcome to the Secret Santa app!")
  start()

  /** The main function that runs the programme **/
  def start(): Unit = {
    println("Choose an option by entering the number:" +
      "\n1) Load participants from \"participants.txt\" in the same folder" +
      "\n2) Enter the participants manually")
    val userChoice = StdIn.readLine() match {
      case x if x == "1" | x == "2" ⇒ x

      case x ⇒ x match {
        case "add test data"          ⇒ Util.addTestData()

        case "clear file"             ⇒ Util.clearFile()

        case _                        ⇒ println("The instructions aren't that hard. Enter either 1 or 2. Again!")
        }
        start()
    }

    val ppl = userChoice match {
      case "1" =>
        confirm("Have you provided a file named participants.txt in the correct format:" +
          "\nname,email,house number/name,postcode (each on a new line)?")
        val ppl: List[Participant] = File.loadParticipants()
        AppCore.validateList(ppl)

        /** Checking with the user if the correct number of participants is found **/
        println(s"I managed to find ${ppl.length} participants with correctly formatted details.")
        confirm("Is that correct?")
        ppl

      case "2" =>
        val ppl = inputParticipants()
        Util.record(ppl)
        ppl
    }

    println("Next step is pairing gifters and giftees...")
    confirm("Do you want to proceed?")
    println("Proceeding to pairing participants...")
    AppCore.assignParticipants(ppl)



  }
}

