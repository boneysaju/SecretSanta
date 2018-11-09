import scala.io.StdIn

object Main extends App {

  println("How would you like to use the app?")
  println("Insert 1  for Uploading a CSV file and insert 2 for Entering participants manually")
//  val userChoice = StdIn.readLine()

  UserInteraction.start(StdIn.readLine())

}

