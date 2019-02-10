package org.apache.spark.sql
object AccessShowString {
  def showString[T](df: Dataset[T],
                    _numRows: Int, truncate: Int = 10000, vertical: Boolean = false): String = {
    df.showString(_numRows, truncate)
  }
}