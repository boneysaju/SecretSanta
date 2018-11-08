import scala.io.StdIn

object Main extends App {

  println("How would you like to use the app?")
  println("Insert 1  for Uploading a CSV file and insert 2 for Entering participants manually")
  val userChoice = StdIn.readLine("Enter text here: ").toInt

  userChoice match {
    case 1 => userChoosesCsv()
    case 2 => userChoosesManualInput()
    case _ => println("That is an invalid input. Please type 1  for Uploading a CSV file and type 2 for Entering participants manually")
  }

  def userChoosesCsv(): Unit = {
    println("Please add all information to the CSV file")
    val fileSource = io.Source.fromFile("participants.txt")
    for (line <- fileSource.getLines.drop(1)) { //ignores the headers
      println(line)
      val Array(name, email, address) = line.split(",").map(_.trim) //splits each line using the comma as a separator and map removes any excess white space
      println(s"$name $email $address")
    }
    fileSource.close
  }

  def userChoosesManualInput(): Unit = {
    println("How many participants are in your party?")
    val numberOfPeople = Console.readInt()

    if (numberOfPeople < 5) {
      print("You need a minimum of 5 people to use this application")
    }
    else {
      // put in a for loop which does this n number of times
      print("Enter a name: ")
      print("Enter an email: ")
      print("Enter an address: ")
    }
  }

}

