package com.flxkbr.swc.c3

import cats._
import cats.syntax.functor._
import cats.instances.string._
import cats.syntax.invariant._
import cats.syntax.semigroup._

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  implicit val treeFunctor = new Functor[Tree] {
    def map[A, B](tree: Tree[A])(func: A => B): Tree[B] = tree match {
      case Branch(left, right) => Branch(map(left)(func), map(right)(func))
      case Leaf(value) => Leaf(func(value))
    }
  }
  // smart constructors that return the Tree subclasses as Tree
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)
}

object FunctorsPlayground {

  implicit val symbolMonoid: Monoid[Symbol] =
    Monoid[String].imap(Symbol.apply)(_.name)

  def main(args: Array[String]) {
    val tree: Tree[Int] = Tree.branch(
      Tree.branch(
        Tree.leaf(1), Tree.leaf(2)),
      Tree.leaf(3))
    println(tree.map(_ * 2))

    println(doMath(tree))
  }

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => (n + 1) * 2)
}
