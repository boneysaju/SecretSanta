import scala.util.Random
import Util._

object AppCore {

  def assignParticipants(ppl: List[Participant]): Map[Participant, Participant] = {
    println("\nProceeding to pairing participants...")

    //  TODO Works, but it can allocate a giftee more than once. Fix/ask Luke. Will clean up function
    /** Creating a list of maps of gifter -> giftee
      * For every Participant in the list, it creates a map with:
      * - that participant as a key
      * - the value being the head of the list after that participant is removed from it(to avoid allocating people to themselves)
      * and it is shuffled(to randomise the allocated giftees)
      *
      * The current problem is that while the gifters(keys) are unique, the giftees(values) are allocated more than once
      **/
//    val lm = for(p ← ppl) yield {
//      Map(p → Random.shuffle(removeParticipantFromList(p, ppl)).head)
//    }
//    /** Concatenating the above maps to create a full map of gifters and giftees that is passed through **/
//    lm.fold(Map[Participant, Participant]()) { (a, b) ⇒
//      println(s"Matching ${b.keys.head.name} with ${b.values.head.name}") //TODO To remove after code is complete to avoid violating one of the acceptance criteria
//      a ++ b
//    }
//    println("Participants successfully paired!")

    /** This works, returning a Map[Participant, Participant] when run. Somehow, is seems that
        * it is possible for ppl.fold(){...} to returns type Equals so the compiler doesn't like it
        *
      . * Why does the fold return Equals?! And why does nobody else on StackOverflow have the same issue?! **/
//    val t =
//      ppl.fold(Map[Participant, Participant]()){
//
//        case (a: Map[Participant, Participant], p: Participant) ⇒
//
//        println(s"Original list: $ppl")
//         Filters out the giftees that have already been chosen
//        val nppl: List[Participant] = ppl.filter(!a.values.toList.contains(_))
//
//        println(s"List after filtering: $nppl")
//        a.foreach(println(_))
//        a ++ Map(p → Random.shuffle(removeParticipantFromList(p, nppl)).head)
//
//        case _ ⇒ throw new Exception("Shit went south")
//    }

    def recurse(gifters: List[Participant], map: Map[Participant, Participant], giftees: List[Participant]): Map[Participant, Participant] =
      if(gifters.nonEmpty) {
        val r = Random.shuffle(removeParticipantFromList(gifters.head, ppl) diff giftees)
        recurse(gifters.tail, map + (gifters.head → r.head), r.head :: giftees)
      }
      else map

    recurse(ppl, Map(), List()).foreach(x ⇒ println(x._1.name + " will be sending a gift to " + x._2.name))
    recurse(ppl, Map(), List())
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
