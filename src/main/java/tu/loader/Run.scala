package tu.loader

object Run {
  def main(args: Array[String]): Unit = {
    new App(args(0)).load()
  }
}
