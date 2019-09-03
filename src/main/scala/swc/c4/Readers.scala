package com.flxkbr.swc.c4

import cats._
import cats.implicits._
import cats.data.Reader

case class Db(
    usernames: Map[Int, String],
    passwords: Map[String, String]
)

object Readers {
  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    // Reader(db => db.passwords.get(username)
    //   .flatMap(pw => pw.equals(password).some).getOrElse(false))
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    findUsername(userId)
      .flatMap(
        nameOpt =>
          nameOpt
            .map(username => checkPassword(username, password))
            .getOrElse(false.pure[DbReader])
      )

  def main(args: Array[String]) {
    val mydb = Db(
      Map(
        1 -> "Felix",
        2 -> "Tobias"
      ),
      Map(
        "Felix" -> "admin",
        "Tobias" -> "pw1"
      )
    )

    println(checkLogin(1, "admin").run(mydb))
    println(checkLogin(2, "wrong").run(mydb))
    println(checkLogin(3, "doesntexist").run(mydb))
  }
}
