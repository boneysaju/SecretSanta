import scala.io.StdIn

object CSV {
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
      case Some('y') ⇒ println("YES") //randomise list
      case Some('n') ⇒ println("\nPlease revise the participants.txt file and try again\n")

      case _ ⇒ println("That is an invalid input. Please type 'YES' to continue or 'NO' to exit ")
        proceedWithCsv(StdIn.readLine("Do you wish to proceed with the above information? "))
    }}
}
