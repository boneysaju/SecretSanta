import Main.{numberOfPeople, santa, validateText}

import scala.collection.mutable.ListBuffer
import scala.io.StdIn

object manualMethods extends App {

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
      enterParticipantDetails({
        n + 1
      })
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
}
