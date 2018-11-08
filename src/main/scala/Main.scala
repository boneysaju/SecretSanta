import scala.io.StdIn

object Main extends App {

  println("How would you like to use the app?")
  println("Insert 1  for Uploading a CSV file and insert 2 for Entering participants manually")
  val userChoice = StdIn.readLine().toInt

  userChoice match {
    case 1 => loadParticipants()
    case 2 => promptManualInput()
    case _ => println("That is an invalid input. Please type 1  for Uploading a CSV file and type 2 for Entering participants manually")
  }

  def loadParticipants(): List[Participant] = {
    println("Getting information from participants.txt...")
    val fileSource = io.Source.fromFile("participants.txt")
    val test = fileSource.getLines().toList
//    for (line <- fileSource.getLines.drop(1)) { //ignores the headers
//      println(line)
//      val Array(name, email, address) = line.split(",").map(_.trim) //splits each line using the comma as a separator and map removes any excess white space
//      println(s"$name $email $address")
//    }
//    fileSource.close
    test.flatMap{ x ⇒
      val l = x.split(",").map(_.trim)
      if(l.length == 4) {
        println("Getting participant with name " + l(0) + " and email " + l(1) + "...")
        Some(Participant(l(0), l(1), l(2), l(3)))
      }
      else None
    }
  }

  def promptManualInput(): Unit = {
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

