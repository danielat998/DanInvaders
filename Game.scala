//at ~p17 in book
object Main{
  def main(args: Array[String]){
		val game:Game = new Game()
  }
}
class Game{

	object alienEnum extends Enumeration{
		type alienEnum = Value
		val ALIEN1, ALIEN2, ALIEN3, NO_ALIEN = Value
	}
	import alienEnum._

	val gui:GUI = new GUI(this,400,200)
	var playerX = gui.GUIWidth / 2
	var mothershipX = -1 //-1 will represent off the screen
	val ALIEN_WIDTH = 30
	val ALIEN_HORIZ_SPACING = 20
	val ALIEN_VERT_SPACING = 10
	val ALIEN_HEIGHT = 10
	val noAliensX = 6
	val noAliensY = 4
	var aliensTopX = 30
	var aliensTopY = 30
	var alienArr: Array[Array[alienEnum.Value]] =
	Array(Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
				Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
 			  Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
			  Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
			  Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
			  Array(ALIEN2, ALIEN2, ALIEN2, ALIEN2, ALIEN2, ALIEN2))
}
