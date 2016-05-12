//at ~p17 in book
object Main{
  def main(args: Array[String]){
		val game:Game = new Game()
  }
}
class Game{
	val gui:GUI = new GUI(this)
}
