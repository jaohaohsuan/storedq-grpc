package com.grandsys.inu.algorithm.DAG

trait EdgeCreator[T] {
  def create(value: T): Edge
}
