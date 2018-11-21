object Pairing {

  def pairPeopleUp(n: Int, santaList: List[(String, String, String)]): Unit = {
    val shuffleList = scala.util.Random.shuffle(santaList)
    var x: Int = 0
    while (x < n) {
    try{ val alist = List(shuffleList(x), shuffleList({x + 1}))
      println(alist) //debugging
      Mail.sendMail(alist)}
      catch {
        case _: Throwable => {
          val blist = List(shuffleList(x), shuffleList({(x + 1) - n}))
          println(blist)
          Mail.sendMail(blist)
        }
      }
      x += 1
    }
  }
}
