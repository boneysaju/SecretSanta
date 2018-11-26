import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail, EmailAttachment}
object Mail {
  def sendMail(aList: List[(String, String, String)]): Unit = {
    val gifterName: String = aList.head._1
    val gifterEmail: String = aList.head._2
    val gifteeName: String = aList.tail.head._1
    val gifteeAddress: String = aList.tail.head._3
    try {
      val email = new SimpleEmail
      email.setHostName("smtp.googlemail.com")
      email.setSmtpPort(587)
      email.setAuthenticator(new DefaultAuthenticator("ten10secretsanta@gmail.com", "jkvryxsvsnrkioww"))
      email.setSSLOnConnect(true)
      email.setFrom("ten10secretsanta@gmail.com")
      email.setSubject("You're a secret santa!")
      email.setMsg(s"Greetings $gifterName,\n\nYou are $gifteeName's secret santa! This is their address: \n$gifteeAddress \n\nPlease have it arrive by ${Main.exchangeDate}, thanks! :)")
      email.addTo(s"$gifterEmail")
      email.send()
    }
    catch {
      case _: Throwable => {
        println(s"Unable to send email to $gifterName. Reason: ${System.err}")
      }
    }
  }
}
