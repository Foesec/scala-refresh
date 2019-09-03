package com.flxkbr.swc.c4

import cats.Monad
import cats.Id
import cats.instances.option._
import cats.instances.list._
import cats.syntax.functor._
import cats.syntax.flatMap._

object MonadsInCats {

  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    a.flatMap(x => b.map(y => x*x + y*y))

  def pure[A](value: A): Id[A] = value
  def map[A, B](initial: Id[A])(func: A => B): Id[B] = func(initial)
  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] = func(initial)
}