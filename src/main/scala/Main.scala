import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.StdIn

object Main extends App {

  println("How would you like to use the app?")
  println("Insert 1 to use the participants.txt file or 2 to enter participants manually")
  var santa: mutable.Map[String, List[String]] = scala.collection.mutable.Map[String, List[String]]()
  val userChoice = userInputType(StdIn.readLine("Enter 1 or 2 here: "))
  var numberOfPeople: Int = 0

  def restartApp():Unit = userInputType(StdIn.readLine("Enter 1 or 2 here: "))

  def userInputType[T](userChoice: T): Unit = {
    userChoice match {
      case "1" => userChoosesCsv()
      case "2" => userChoosesManualInput()
      case _ => println("That is an invalid input. Please type 1 for Uploading a CSV file and type 2 to enter participants manually")
        userInputType(StdIn.readLine("Enter 1 or 2 here: "))
    }
  }

  def userChoosesCsv(): Unit = {
    println("Please check that the following information is correct:")
    val fileSource = io.Source.fromFile("participants.txt")
    for (line <- fileSource.getLines.drop(1)) { //ignores the headers
      println(line)
      val Array(name, email, address) = line.split(",").map(_.trim) //splits each line using the comma as a separator and map removes any excess white space
      println(s"$name $email $address")
    }
    fileSource.close
    proceedWithCsv(StdIn.readLine("Do you wish to proceed with the above information? "))
  }

  def proceedWithCsv(proceedOption:String):Unit = {
  proceedOption.toLowerCase.headOption match {
    case Some('y') ⇒ println("YES")//randomise list
    case Some('n') ⇒ println("\nPlease revise the participants.txt file and try again\n")
    case _ ⇒ println("That is an invalid input. Please type 'YES' to continue or 'NO' to exit ")
      proceedWithCsv(StdIn.readLine("Do you wish to proceed with the above information? "))
  }}

  def userChoosesManualInput(): Unit = {
    println("How many participants are in your party?")
    numberOfPeople = Console.readInt()
    if (numberOfPeople < 5) {
      print("You need a minimum of 5 people to use this application \n")
      userChoosesManualInput()
    }
    else {
      userEntersParticipantsManually()
    }
  }

  def userEntersParticipantsManually(): Unit = {
    var n: Int = 0
    while (n < numberOfPeople) {
      println(s"Add participant ${n + 1}")
      enterParticipantDetails({n+1})
      n += 1
    }
    println(s"All $n participants have been added to the list")
  }

  def enterParticipantDetails(n: Int): Unit = {
    val name = validateText(StdIn.readLine("Enter a name: "), "Name")
    val email = validateText(StdIn.readLine("Enter an email: "), "Email address")
    val address = validateText(StdIn.readLine("Enter an address: "), "Address")
    val tempList = (new ListBuffer[String] += name += email += address).toList
    santa += (n.toString -> tempList)
    println(s"$name has been added to the list\n")
    for ((k, v) <- santa) printf("Key: %s, Details: %s\n", k, v) //debugging
  }

  def validateText(text: String, field: String): String = {
    if (text.isEmpty) {
      println(s"$field cannot be blank\n")
      validateText(StdIn.readLine(s"Enter a $field: "), field)
    } else text
  }
//integrating with email API

}

