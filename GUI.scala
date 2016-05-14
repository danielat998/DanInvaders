import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt._//not sure why this doesn't import the above two things
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.Action
import javax.swing.AbstractAction
import javax.swing.KeyStroke
import collection.mutable.HashMap
class GUI(game:Game,givenWidth:Int,givenHeight:Int) extends JFrame with ActionListener{
	var GUIWidth = givenWidth
	var GUIHeight = givenHeight
	this.initUI
	this.initKeyboard
	var canvas:GameCanvas = new GameCanvas(GUIWidth,GUIHeight,game)
	def actionPerformed(e:ActionEvent){}
 	def initUI{
		canvas = new GameCanvas(GUIWidth,GUIHeight,game)//don't ask why this is being done twice it just keeps the compiler happy
		this.add(canvas)
		setTitle("DanTetris version " /*+ Globals.version*/)
		setSize(GUIWidth,GUIHeight)
		//setDefaultCloseOperation(EXIT_ON_CLOSE)
		pack
		this.setVisible(true)
	}
	private val actionMap = new HashMap[KeyStroke,Action]
	def initKeyboard{
	/*	val left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0)
		actionMap.put(left, new AbstractAction("Move Left"){
			override def actionPerformed(e:ActionEvent){
				println("hello")
			}
		})//actionMap.put
	*/
	}
}

class GameCanvas(x:Int,y:Int,game:Game) extends JPanel{
	setPreferredSize(new Dimension(x,y))
	setBackground(Color.BLACK)

	//before you say anything, yes replotting every time is horribly inefficient, but it
	//is how I have chosen to implement things for the time being
	override def paintComponent(g:Graphics){
		clearCanvas(g)
		plotAliens(g)
		plotMothership(g)
		plotPlayer(g)
		plotExcrement(g)
		plotBullet(g)
	}

	def plotExcrement(g:Graphics){}//this will probably be an ArrayList or similar
	def plotBullet(g:Graphics){}//have vars in Game for position of this as ther will only be one

	def clearCanvas(g:Graphics){
		val oldColour:Color = g.getColor
		g.setColor(Color.black)
		g.fillRect(0,0,x,y)
		g.setColor(oldColour)
	}

	def plotAliens(g:Graphics){
		//loop through some sort of array in Game
		val oldColour:Color = g.getColor
		g.setColor(Color.white)
		var i =0
		var j = 0
		for(i <- 0 to (game.alienArr.length - 1))
			for(j <- 0 to (game.alienArr(0).length -1))
				g.fillRect(game.aliensTopX + i*game.ALIEN_WIDTH + i * game.ALIEN_HORIZ_SPACING,
									 game.aliensTopY + j*game.ALIEN_HEIGHT + j * game.ALIEN_VERT_SPACING,
									 game.ALIEN_WIDTH,
									 game.ALIEN_HEIGHT
									 )
		g.setColor(oldColour)
	}

	def plotMothership(g:Graphics){
	}
	def plotPlayer(g:Graphics){
		//just a block for now
		val oldColour:Color = g.getColor
		g.setColor(Color.white)
		g.fillRect(game.playerX,y-30,30,30)
		g.setColor(oldColour)
	}

}
