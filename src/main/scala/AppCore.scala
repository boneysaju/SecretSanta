import scala.util.Random
import Util._

import scala.annotation.tailrec

object AppCore {

  def assignParticipants[A](ppl: List[A]): Map[A, A] = {
    println("\nProceeding to pairing participants...")

    @tailrec
    def pairParticipants(gifters: List[A], map: Map[A, A], giftees: List[A]): Map[A, A] = gifters match {
      case x if x.nonEmpty ⇒
        // Getting the complete list, removing the person who we are allocating a giftee to, removing the giftees already used, then shuffling the list
        val r = Random.shuffle(removeParticipantFromList(gifters.head, ppl) diff giftees)
        pairParticipants(gifters.tail, map + (gifters.head → r.head), r.head :: giftees)
      case _        ⇒ map
    }
    pairParticipants(ppl, Map(), List()).foreach(println(_))
    pairParticipants(ppl, Map(), List())
  }

  def sendEmails(): Unit = println("\nEmail functionality is not implemented yet. Sorry mate!\nOff you go now!")

  /** Checking if the list is empty or has less than 5 participants and throws out the user if that's the case **/
  def validateList(list: List[Participant]): Unit = list match {
    case x if x.isEmpty ⇒
      println("Um, mate, I think your list is empty." +
        "\nHow about you follow the instructions again and do it right this time, ye?" +
        "\nLater!")
      System.exit(1)

    case x if x.length < 5 ⇒
      println("You need at least 5 participants in your list to be able to proceed." +
        s"\nYou only have ${list.length}. Add some more!")
      System.exit(1)

    case _ ⇒
  }

}
