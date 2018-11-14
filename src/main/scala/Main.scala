import UserInteraction.{confirm, inputParticipants}

import scala.io.StdIn

object Main extends App {

  println("Welcome to the Secret Santa app!")
  start()

  /** The main function that runs the programme **/
  def start(): Unit = {
    println("Choose an option by entering the number:" +
      "\n1) Load participants from a .txt file in the same folder" +
      "\n2) Enter the participants manually")
    val userChoice = StdIn.readLine() match {
      case x if x == "1" | x == "2" ⇒ x

      case x ⇒ x match {
        case "add test data"          ⇒ TextFile.addTestData()
        case "clear file"             ⇒ TextFile.clearFile()
        case _                        ⇒ println("The instructions aren't that hard. Enter either 1 or 2. Again!\n")
        }
        start()
    }

    val ppl = userChoice match {
      case "1" =>
        val ppl: List[Participant] = TextFile.loadParticipants()
        AppCore.validateList(ppl)

        /** Checking with the user if the correct number of participants is found **/
        println(s"\nI managed to find ${ppl.length} participants with correctly formatted details.")
        confirm("Is that correct?")
        ppl

      case "2" =>
        val ppl = inputParticipants()
        TextFile.record(ppl)
        ppl
    }

    AppCore.assignParticipants(ppl)

    AppCore.sendEmails()
    println("\nHope you enjoyed the app! Feel free to provide any feedback!")

  }
}

