object Pairing {

  object Pairing {

    def pairPeopleUp(n: Int, aList: List[(String, String, String)]): Unit = {
      var x: Int = 0
      while (x < n) {
        try Mail.sendMail(List(aList(x), aList({x + 1})))
        catch {
          case _: Throwable => {
            Mail.sendMail(List(aList(x), aList({x - 1})))
          }
        }
        x += 1
      }
    }
  }

}
