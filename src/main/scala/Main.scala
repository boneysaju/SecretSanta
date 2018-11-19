import scala.collection.mutable
import scala.io.StdIn

object Main extends App {


  println("How would you like to use the app?")
  println("Insert 1 to use the participants.txt file or 2 to enter participants manually")
  var santa: mutable.Map[String, List[String]] = scala.collection.mutable.Map[String, List[String]]()
  val userChoice = userInputType(StdIn.readLine("Enter 1 or 2 here: "))
  var numberOfPeople: Int = 0

  def restartApp(): Unit = userInputType(StdIn.readLine("Enter 1 or 2 here: "))

  def userInputType[T](userChoice: T): Unit = {
    userChoice match {
      case "1" => csvMethods.userChoosesCsv()
      case "2" => manualMethods.userChoosesManualInput()
      case _ => println("That is an invalid input. Please type 1 for Uploading a CSV file and type 2 to enter participants manually")
        userInputType(StdIn.readLine("Enter 1 or 2 here: "))
    }
  }

  def validateText(text: String, field: String): String = {
    if (text.isEmpty) {
      println(s"$field cannot be blank\n")
      validateText(StdIn.readLine(s"Enter a $field: "), field)
    } else text
  }
}