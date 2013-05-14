package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
    
  // RSS-Dateien einlesen
  import io.{ Source, BufferedSource}
  // RSS-Feed holen
  val xmlinput = Source.fromFile("zeit.xml").getLines().toList.view
  // nur <link> und <description> Zeilen behalten
  def contLinkOrDesc(s:String):Boolean = {
    s.contains("<link>") || s.contains("<description")
  }
  val xmlldwithfirstlink = xmlinput.filter(x => contLinkOrDesc(x)).view
  // ersten <link> rausschmeißen
  val xmlld = xmlinput tail
  
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
  
	import play.api.data._
	import play.api.data.Forms._

	val feedForm = Form(
		"URL" -> nonEmptyText
	)
  
}
