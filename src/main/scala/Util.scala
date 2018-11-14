object Util {

  def removeParticipantFromList[A](p: A, list: List[A]): List[A] = list diff List(p)

}
