import scala.io.StdIn

object Main extends App {

  println("How would you like to use the app?")
  println("Insert 1 to use the participants.txt file or 2 to enter participants manually")
  val userChoice = userInputType(StdIn.readLine("Enter 1 or 2 here: "))
  var exchangeDate = ""

  def restartApp(): Unit = userInputType(StdIn.readLine("Enter 1 or 2 here: "))

  def userInputType[T](userChoice: T): Unit = {
    userChoice match {
      case "1" => CSV.userChoosesCsv()
      case "2" => ManualInput.userChoosesManualInput()
      case _ => println("That is an invalid input. Please type 1 for Uploading a CSV file and type 2 to enter participants manually")
        userInputType(StdIn.readLine("Enter 1 or 2 here: "))
    }
  }

  def pairAndEmail(numberOfPeople: Int, santaList: List[(String, String, String)]): Unit = {
    setDate()
    println("Ready to proceed with pairing and emailing, press enter to continue")
    println(StdIn.readLine(""))

    Pairing.pairPeopleUp(numberOfPeople, santaList)
  }

  def setDate(): Unit = {
    exchangeDate = StdIn.readLine("When would you like to hold the gift exchange? Please specify a date: ")
    if (exchangeDate.isEmpty) {
      println("Please enter a date for the gift exchange to take place")
      setDate()
    }
  }
}

