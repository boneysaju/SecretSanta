import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.StdIn

object ManualInput {

  var numberOfPeople: Int = 0
  var santaList: List[(String, String, String)] = List()

  def userChoosesManualInput(): Unit = {
    println("How many participants are in your party?")
    try numberOfPeople = Console.readInt()
    catch {
      case _: Throwable ⇒ {
        println("Please enter a number")
        userChoosesManualInput()
      }
    }
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

    Main.setDate()
  }

  def enterParticipantDetails(n: Int): Unit = {
    var correct: Boolean = false
    var name = ""
    while (!correct) {
      name = validateText(StdIn.readLine("Enter a name: "), "Name")
      val email = validateText(StdIn.readLine("Enter an email: "), "Email address")
      val address = validateText(StdIn.readLine("Enter an address, with new lines separated by commas (,): "), "Address").replace(",", "\n")
      confirmDetails(StdIn.readLine("Are the above details correct? (Y/N): "))

      def confirmDetails[T](selection: T): Unit = selection.asInstanceOf[String].toLowerCase.substring(0, 1) match {
        case "y" => {
          correct = true
          santaList = santaList :+ ((name, email, address))
          santaList.foreach({
            println //debugging line
          })
        }
        case "n" => println(s"Please re-enter details for participant $n\n")
        case _ => confirmDetails(StdIn.readLine("Are the above details correct? (Y/N): "))
      }
    }
    println(s"\n$name added to list\n")
  }

  def validateText(text: String, field: String): String = {
    if (text.isEmpty) {
      println(s"$field cannot be blank\n")
      validateText(StdIn.readLine(s"Enter a $field: "), field)
    } else text
  }
}
