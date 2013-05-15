package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
      import language.postfixOps

  import io.{Source,BufferedSource}

  val file = Source.fromFile("zeit.xml").getLines.toList

  def contd(s:String):Boolean = s.contains("<description>")
  def contl(s:String):Boolean = s.contains("<link>")
  def contt(s:String):Boolean = s.contains("<title>")
  import util.matching.Regex

  val description = new Regex(""".*/&gt;&lt;/a&gt;(.*)</description>""","content")
  val description2 = new Regex(""".*<description>(.*)</description>""","content")
  val title = new Regex(""".*<title>(.*)</title>.*""","titel")
  val link = new Regex(""".*<link>(.*)</link>.*""","url")
  val msc = new Regex("""(.*)""","ms")
  val contitledesct:List[String] = for (f <- ( file.filter(x => contt(x))tail )) yield { f match {
    case title(titel) => titel
    case _ => ""
  }}
  val conlinkdescl:List[String] = for (f <- ( file.filter(x => contl(x))tail)) yield { f match {
    case description(content) => content
    case description2(content) => content
    case link(url) => url
    case _ => ""
  }} 
  val conlinkdescd:List[String] = for (f <- ( file.filter(x => contd(x)) )) yield { f match {
    case description(content) => content
    case description2(content) => content
    case link(url) => url
    case _ => ""
  }}

  val feedTuple = ((contitledesct tail) zip (conlinkdescl tail)) zip conlinkdescd
  
	def index = Action {
		Redirect(routes.Application.feeds)
	}
	
	import models.Feed
	
	def home = Action {
		Ok(views.html.Hauptseite())
	}
	def start = Action {
		Ok(views.html.Startseite())
	}
	def out = Action {
		Ok(views.html.log_out())
	}
	def about = Action {
		Ok(views.html.about_us())
	}
	def create = Action {
		Ok(views.html.create_an_account())
	}
	
	def feeds =  Action {
		Ok(views.html.userprofile(Feed.all(), feedForm))
	}
  
	def newFeed = Action { implicit request =>
		feedForm.bindFromRequest.fold(
			errors => BadRequest(views.html.userprofile(Feed.all(), errors)),
			URL => {
				Feed.create(URL)
				Redirect(routes.Application.feeds)
			}
		)
	}
	
	def deleteFeed(id: Long) = Action {
		Feed.delete(id)
		Redirect(routes.Application.feeds)
	}

        def submit() = TODO

        def showFeeds() = Action {
          Ok(views.html.showFeeds(feedTuple))
        }
  
	import play.api.data._
	import play.api.data.Forms._

	val feedForm = Form(
		"URL" -> nonEmptyText
	)
  
}
