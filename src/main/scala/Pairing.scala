object Pairing {

  def pairPeopleUp(n: Int, santaList: List[(String, String, String)]): Unit = {
    val shuffleList = scala.util.Random.shuffle(santaList)
    var x: Int = 0
    while (x < n) {
    try Mail.sendMail(List(shuffleList(x), shuffleList({x + 1})))
      catch {
        case _: Throwable => {
          Mail.sendMail(List(shuffleList(x), shuffleList({(x + 1) - n})))
        }
      }
      x += 1
    }
  }
}
