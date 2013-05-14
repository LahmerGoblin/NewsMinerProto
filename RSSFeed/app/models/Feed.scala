package models

case class Feed(id: Long, URL: String)

object Feed {
  
	import play.api.db._
	import play.api.Play.current
	import anorm._
	import anorm.SqlParser._
	
	val feed = {
		get[Long]("id") ~ 
		get[String]("URL") map {
			case id~url => Feed(id, url)
		}
	}
	def all(): List[Feed] = DB.withConnection { implicit c =>
		SQL("select * from feeds").as(feed *)
	}
	
	def create(URL: String) {
		DB.withConnection { implicit c =>
			SQL("insert into feeds(URL) values ({URL})").on(
			'URL -> URL ).executeUpdate()
			}
	}

	def delete(id: Long) {
		DB.withConnection { implicit c =>
			SQL("delete from feeds where id = {id}").on(
			'id -> id
			).executeUpdate()
		}
	}

	
}