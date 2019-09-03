package com.flxkbr.swc.c2

trait MySemigroup[A] {
  def combine(x: A, y: A): A
}

trait MyMonoid[A] extends MySemigroup[A] {
  def empty: A
}

object MyMonoid {
  def apply[A](implicit MyMonoid: MyMonoid[A]) = MyMonoid
}

object BooleanMyMonoids {
  implicit val andMyMonoid = new MyMonoid[Boolean] {
    def combine(x: Boolean, y: Boolean): Boolean = x && y
    def empty: Boolean = true
  }
  implicit val orMyMonoid = new MyMonoid[Boolean] {
    def combine(x: Boolean, y: Boolean): Boolean = x || y
    def empty: Boolean = false
  }
  implicit val xorMyMonoid = new MyMonoid[Boolean] {
    def combine(x: Boolean, y: Boolean): Boolean =
      (x && !y) || (!x && y)
    def empty: Boolean = false
  }
  implicit val xNorMyMonoid = new MyMonoid[Boolean] {
    def combine(x: Boolean, y: Boolean): Boolean = !xorMyMonoid.combine(x, y)
    def empty = true;
  }
}

object SetMyMonoids {
  implicit def setUnionMyMonoid[A]: MyMonoid[Set[A]] =
    new MyMonoid[Set[A]] {
      def combine(x: Set[A], y: Set[A]): Set[A] = x union y
      def empty = Set.empty[A]
    }
  implicit def setIntersectionSemigroup[A]: MySemigroup[Set[A]] =
    new MySemigroup[Set[A]] {
      def combine(x: Set[A], y: Set[A]) = x intersect y
    }
}
