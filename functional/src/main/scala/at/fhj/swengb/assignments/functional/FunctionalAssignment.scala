package at.fhj.swengb.assignments.functional

/**
  * In this assignment you have the chance to demonstrate basic understanding of
  * functions like map/filter/foldleft a.s.o.
  **/
object FunctionalAssignment {

  /**
    * A function which returns its parameters in a changed order. Look at the type signature.
    */
  def flip[A, B](t: (A, B)): (B, A) = t.swap



  /**
    * given a Seq[A] and a function f : A => B, return a Seq[B]
    */
  def unknown[A, B](as: Seq[A], fn: A => B): Seq[B] = as.map(fn)

  /**
    * Returns the absolute value of the parameter i.
    *
    * @param i a value, either with a positive or a negative sign.
    * @return
    */
  def abs(i: Int): Int = if (i.>=(0)) (i)
  else  i * (-1)


  // Describe with your own words what this function does.
  // in the comment below, add a description what this function does - in your own words - and give
  // the parameters more descriptive names.
  //
  // Afterwards, compare your new naming scheme with the original one.
  // What did you gain with your new names? What did you loose?
  //
  /**
    *
    * @param as is a Sequence
    * @param b is accumulator
    * @param fn is a function
    * @tparam A value of Seq
    * @tparam B result of fn
    * @return
    */
  def op[A, B](as: Seq[A], b: B)(fn: (B, A) => B): B = as.foldLeft(b)(fn)

  /**
    * implement the summation of the given numbers parameter.
    * Use the function 'op' defined above.
    *
    * @param numbers
    * @return
    */
  def sum(numbers: Seq[Int]): Int = {
      op(numbers, 0)(_ + _)
  }


  /**
    * calculate the factorial number of the given parameter.
    *
    * for example, if we pass '5' as parameter, the function should do the following:
    *
    * 5! = 5 * 4 * 3 * 2 * 1
    *
    * @param i parameter for which the factorial must be calculated
    * @return i!
    */
  def fact(i: Int): Int =  if (i == 0)
      return 1
    else
      return i * fact(i-1)

  /**
    * compute the n'th fibonacci number
    *
    * hint: use a internal loop function which should be written in a way that it is tail
    * recursive.
    *
    * https://en.wikipedia.org/wiki/Fibonacci_number
    */
  def fib(n: Int): Int = {
    def fib_tail( n: Int, a:Int, b:Int): Int = n match {
      case 0 => a
      case _ => fib_tail( n-1, b, a+b )
    }
    return fib_tail( n, 0, 1)
  }

  /**
    * Implement a isSorted which checks whether an Array[A] is sorted according to a
    * given comparison function.
    *
    * Implementation hint: you always have to compare two consecutive elements of the array.
    * Elements which are equal are considered to be ordered.
    */
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
      for (i <- 1 until as.length if !gt(as(i - 1), as(i))) {
        return false
      }
      return true
  }


  /**
    * Takes both lists and combines them, element per element.
    *
    * If one sequence is shorter than the other one, the function stops at the last element
    * of the shorter sequence.
    */
  def genPairs[A, B](as: Seq[A], bs: Seq[B]): Seq[(A, B)] = {
      as.zip(bs)
  }

  // a simple definition of a linked list, we define our own list data structure
  sealed trait MyList[+A]

  case object MyNil extends MyList[Nothing]

  case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

  // the companion object contains handy methods for our data structure.
  // it also provides a convenience constructor in order to instantiate a MyList without hassle
  object MyList {


    def sum(list: MyList[Int]): Int = list match {
      case MyNil => 0
      case Cons(head,tail) => head + sum(tail)
    }

    def product(list: MyList[Int]): Int = list match {
      case MyNil => 1
      case Cons(head,tail) => head * product(tail)

    }



    def apply[A](as: A*): MyList[A] = {
      if (as.isEmpty) MyNil
      else Cons(as.head, apply(as.tail: _*))
    }

  }

}

