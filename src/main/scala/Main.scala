import scala.io.StdIn

object Main extends App {

  println("How would you like to use the app?")
  println("Insert 1 to use the participants.txt file or 2 to enter participants manually")
  val userChoice = StdIn.readLine("Enter 1 or 2 here: ").toInt
  var numberOfPeople: Int = 0

  userChoice match {
    case 1 => userChoosesCsv()
    case 2 => userChoosesManualInput()
    case _ => println("That is an invalid input. Please type 1 for Uploading a CSV file and type 2 to enter participants manually")
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
    println("Do you wish to proceed with the above information?")
    //Y or N. If N, println("Please revise the participants.txt file and try again")
    //If Y, continue
  }

  def userChoosesManualInput(): Unit = {
    println("How many participants are in your party?")
    numberOfPeople = Console.readInt()
    if (numberOfPeople < 5) {
      print("You need a minimum of 5 people to use this application")
      userChoosesManualInput()
    }
    else {
      userEntersParticipantsManually()
    }
  }

  def userEntersParticipantsManually(): Unit = {
    var n: Int = 0
    while (n < numberOfPeople)
    //add validation for fields
      // field cannot be empty
    { println(s"Add participant ${n+1}")
      print("Enter a name: ")
      Console.readLine()
      print("Enter an email: ")
      Console.readLine()
      print("Enter an address: ")
      Console.readLine()
      n = n + 1
      println(s"Participant $n added")
    }
  }
  //after n users are added,  println(s"All participants have been added to the list")
  // how to store fields


}

