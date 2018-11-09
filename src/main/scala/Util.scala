object Util {

  def removeParticipantFromList(p: Participant, list: List[Participant]): List[Participant] = list diff List(p)

}
