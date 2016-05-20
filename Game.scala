//at ~p17 in book
object Main{
  def main(args: Array[String]){
		val game:Game = new Game()
		game.mainLoop
  }
}
class Game{
	val VERSION = 0.01
	var ammoX = 0
	var ammoY = -1
	var AMMO_STEP = 10
	var AMMO_WIDTH = 5
	var AMMO_HEIGHT = 20
	var alienDirRight = true //false means left
	val ALIEN_HORIZ_STEP = 10
	val ALIEN_VERT_STEP = 10
	val gui:GUI = new GUI(this,400,200)
	var playerX = gui.GUIWidth / 2
	var playerWidth = 10//not used in plotting code just yet
	var mothershipX = -1 //-1 will represent off the screen
	val ALIEN_WIDTH = 30
	val ALIEN_HORIZ_SPACING = 20
	val ALIEN_VERT_SPACING = 10
	val ALIEN_HEIGHT = 10
	val noAliensX = 6
	val noAliensY = 4
	var aliensTopX = 30
	var aliensTopY = 30
	object alienEnum extends Enumeration{
		type alienEnum = Value
		val ALIEN1, ALIEN2, ALIEN3, NO_ALIEN = Value
	}
	import alienEnum._
	var alienArr: Array[Array[alienEnum.Value]] =
	Array(Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
				Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
 			  Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
			  Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
			  Array(ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1, ALIEN1),
			  Array(ALIEN2, ALIEN2, ALIEN2, ALIEN2, ALIEN2, ALIEN2))

	var lives = 3
	var interval = 500
	var gameOver = false
	def mainLoop{
		while (true){
			try {Thread.sleep(interval)}
			catch {case e:Throwable => e.printStackTrace}
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
				moveAmmo
				checkAndKillAliens
			}
			gui.canvas.repaint()
		}
	}

	def checkAndKillAliens{
		//foreach alien
    for(i <- 0 to (alienArr.length - 1)){
      for(j <- 0 to (alienArr(0).length -1)){
				//check for intersection
				//define these for logic readability (leftmost POINT of current alien et. c.)
				var leftmostAlien = aliensTopX + i * (ALIEN_HORIZ_SPACING + ALIEN_WIDTH)
				var rightmostAlien = aliensTopX + i * (ALIEN_HORIZ_SPACING + ALIEN_WIDTH) + ALIEN_WIDTH
				var leftmostAmmo = ammoX
				var rightmostAmmo = ammoX + AMMO_WIDTH
				var topmostAlien = aliensTopY + j * (ALIEN_VERT_SPACING + ALIEN_HEIGHT)
				var lowermostAlien = aliensTopY + j * (ALIEN_VERT_SPACING + ALIEN_HEIGHT) + ALIEN_HEIGHT
				var topMostAmmo = ammoY
				var lowermostAmmo = ammoY + AMMO_HEIGHT
				//if they intersect vertically AND intersect horizontally
				if ( (! ((leftmostAlien > rightmostAmmo) || (rightmostAlien < leftmostAmmo)))
								&& (! ((topmostAlien  > lowermostAmmo) || (lowermostAlien < topMostAmmo )))   ){
					alienArr(i)(j) = alienEnum.NO_ALIEN
				}
			}
		}
	}

	def moveAmmo{
		if (ammoY != -1)
			ammoY -= AMMO_STEP//move it upwards steadily
		if (ammoY <=0)
			ammoY = -1
	}

	def shoot{
		if (ammoY == -1){//cannot shoot if a bullet is already moving
			ammoX = playerX + (playerWidth / 2)
			ammoY = gui.GUIHeight
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
				if (aliensTopX - ALIEN_HORIZ_STEP >= 0){
					aliensTopX -= ALIEN_HORIZ_STEP
				}
				else{//maybe error handling should go here...
					aliensTopY += ALIEN_VERT_STEP
					alienDirRight = true
				}
		}
		
	}

	def movePlayerX(dx:Int){//note dx could be +ve or -ve
		if (playerX + dx >=0 && playerX + dx +playerWidth < gui.GUIWidth)
			playerX += dx
		gui.canvas.repaint()
	}
}
