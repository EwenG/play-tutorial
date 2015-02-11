package controllers

import play.api.data.{Forms, Form}
import play.api.libs.json.Json
import play.api.mvc._
import projects.Project

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready.")).as(HTML)
  }

  def projects = Action {
    val json = Json.toJson(List(Project("a"), Project("b")))
    Ok(json)
  }

  def projects_template = Action {
    Ok(views.html.projects(Form(Forms.mapping("name" -> Forms.text)
      (Project.apply)
      (Project.unapply))))
  }

  def add = Action { implicit request =>
    val projectForm = Form(Forms.mapping("name" -> Forms.text)
      (Project.apply)
      (Project.unapply))
    Ok(views.html.projects(projectForm))
  }

}