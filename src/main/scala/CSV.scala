import scala.io.StdIn

object CSV {
  var numberOfPeople: Int = 0
  var santaList: List[(String, String, String)] = List()

  def userChoosesCsv(): Unit = {
    santaList = List()
    numberOfPeople = 0
    println("Please check that the following information is correct:")
    val fileSource = io.Source.fromFile("participants.txt")
    for (line <- fileSource.getLines.drop(1)) { //ignores the headers
      numberOfPeople += 1
      val Array(name, email, address) = line.split("/").map(_.trim) //splits each line using the comma as a separator and map removes any excess white space
      santaList = santaList :+ ((name, email, address.replace(",", "\n")))
    }
    fileSource.close
    santaList.foreach({println})
    proceedWithCsv(StdIn.readLine("Do you wish to proceed with the above information? (Y/N): "))
  }

  def checkNumberOfPeople(): Unit = {
    if (numberOfPeople < 5) {
      println("The number of participants must be at least 5")
      StdIn.readLine("Please modify the file, then press enter to continue:")
      System.exit(1)
    }
  }

  def proceedWithCsv(proceedOption: String): Unit = {
    checkNumberOfPeople()
    proceedOption.toLowerCase.headOption match {
      case Some('y') ⇒ Main.pairAndEmail(numberOfPeople, santaList)
      case Some('n') ⇒ Main.restartApp()
      case _ ⇒ println("That is an invalid input. Please type 'YES' to continue or 'NO' to exit ")
        proceedWithCsv(StdIn.readLine("Do you wish to proceed with the above information? (Y/N): "))
    }
  }
}
