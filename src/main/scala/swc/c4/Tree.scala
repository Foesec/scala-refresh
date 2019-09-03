package com.flxkbr.swc

import cats.Monad

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)

  def leaf[A](value: A): Tree[A] =
    Leaf(value)

  implicit val treeMonad: Monad[Tree] = new Monad[Tree] {
    def flatMap[A, B](tr: Tree[A])(fn: A => Tree[B]): Tree[B] = tr match {
      case Branch(left, right) => Branch(flatMap(left)(fn), flatMap(right)(fn))
      case Leaf(value)         => fn(value)
    }

    def pure[A](value: A): Tree[A] = leaf(value)

    def tailRecM[A, B](a: A)(fn: A => Tree[Either[A, B]]): Tree[B] =
      flatMap(fn(a)) {
        case Left(value)  => tailRecM(value)(fn)
        case Right(value) => Leaf(value)
      }
  }
}
