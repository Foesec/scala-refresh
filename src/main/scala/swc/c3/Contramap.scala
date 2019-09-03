package com.flxkbr.swc.c3

trait Printable[A] {
  self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String =
        self.format(func(value))
    }
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)
}

final case class Box[A](value: A)

object PrintableContravariantFunctors {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    def format(value: String): String = "\"" + value + "\""
  }
  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    def format(value: Boolean): String = if (value) "TRUE" else "FALSE"
  }
  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](box => box.value)
}

object Contramap {
  def main(args: Array[String]) {
    import PrintableContravariantFunctors._
    println(Printable.format("helloou"))
    println(Printable.format(false))
  }
}