//at ~p17 in book
object Main{
  def main(args: Array[String]){
		val game:Game = new Game()
		game.mainLoop
  }
}
class Game{
	var lives = 3
	var interval = 500
	var gameOver = false
	def mainLoop{
		while (true){
			try {Thread.sleep(interval)}
			catch {case e:Throwable => e.printStackTrace}
			println("a")
			if (gameOver)
				println("Game Over")
			else{
				moveAliens
				if(aliensReachedBottom)
					gameOver = true
				if (intersectWithExcrement)
					lives -= 1
				if (lives == 0)
					gameOver = true
				gui.canvas.repaint()
			}
		}
	}

	def aliensReachedBottom = false
	def intersectWithExcrement = false
	def moveAliens{
		val alienRightmostPoint = aliensTopX +
		              (ALIEN_WIDTH + ALIEN_HORIZ_SPACING) * alienArr(0).length
		if (alienDirRight){
			if (alienRightmostPoint + ALIEN_HORIZ_STEP <= gui.GUIWidth){
				aliensTopX += ALIEN_HORIZ_STEP
			}
			else{//maybe error handling should go here...
				aliensTopY += ALIEN_VERT_STEP
				alienDirRight = false
			}
		} else {//if moving to the left
				if (alienRightmostPoint - ALIEN_HORIZ_STEP >= 0){
					aliensTopX -= ALIEN_HORIZ_STEP
				}
				else{//maybe error handling should go here...
					aliensTopY += ALIEN_VERT_STEP
					alienDirRight = true
				}
		}
		
	}

	object alienEnum extends Enumeration{
		type alienEnum = Value
		val ALIEN1, ALIEN2, ALIEN3, NO_ALIEN = Value
	}
	import alienEnum._
	var alienDirRight = true //false means left
	val ALIEN_HORIZ_STEP = 10
	val ALIEN_VERT_STEP = 10
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
