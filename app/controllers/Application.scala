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

  def buildProjectForm = {
    Form(Forms.mapping("projectName" -> Forms.text)
      (Project.apply)
      (Project.unapply))
  }

  def projects_template = Action {
    Ok(views.html.projects(buildProjectForm))
  }

  def add = Action { implicit request =>
    val projectForm = buildProjectForm
    val project = projectForm.bindFromRequest.get
    Ok(views.html.projects(projectForm))
  }

}