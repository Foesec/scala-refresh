package com.flxkbr.swc

import cats.Id

object MonadId {

  def pure[A](value: A): Id[A] = value
  def map[A, B](initial: Id[A])(func: A => B): Id[B] = func(initial)
  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] = func(initial)
}
