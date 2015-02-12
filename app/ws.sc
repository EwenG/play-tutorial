import play.api.{Application, GlobalSettings}
import play.api.db.DB
import play.api.libs.json.{JsPath, JsValue, Json, JsNull}
import projects.Project
import anorm.{ParameterValue, NamedParameter, SQL}
import play.api.test.Helpers.inMemoryDatabase
import anorm.SqlParser.str
implicit val fakeApplicationWithGlobal = play.api.test.FakeApplication(
  additionalConfiguration = Map(
    ("db.default.driver") -> "org.h2.Driver",
    ("db.default.url") -> ("jdbc:h2:db1;AUTO_SERVER=TRUE")
  ))
DB.withConnection{implicit conn =>
  SQL(
    """
      |CREATE TABLE IF NOT EXISTS Project
      |(
      |  id int NOT NULL AUTO_INCREMENT,
      |  name VARCHAR(255),
      |  PRIMARY KEY (id)
      |)
    """.stripMargin).execute()
  /*val res1 = SQL(
    """
      |INSERT INTO PROJECT (name)
      |    VALUES ({name})
    """
      .stripMargin)
    .on('name -> "a")
    .executeInsert()
  println(res1)*/
  val res2 = SQL(
    """
      |SELECT Project.id FROM Project
      |WHERE Project.name={name}
    """.stripMargin)
  .on('name -> "a")
  .apply()
  .head[Long]("id")
  println(res2)
}