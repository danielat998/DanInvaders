import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt._
import javax.swing.JFrame
import javax.swing.JPanel
class GUI(game:Game) extends JFrame with ActionListener{
	this.initUI
	var canvas:GameCanvas = new GameCanvas(400,200)
	def actionPerformed(e:ActionEvent){}
 	def initUI{
		canvas = new GameCanvas(400,200)//don't ask why this is being done twice it just keeps the compiler happy
		this.add(canvas)
		setTitle("DanTetris version " /*+ Globals.version*/)
		setSize(400,200)
		//setDefaultCloseOperation(EXIT_ON_CLOSE)
		pack
		this.setVisible(true)
	}
}

class GameCanvas(x:Int,y:Int) extends JPanel{
	setPreferredSize(new Dimension(x,y))
	setBackground(Color.BLACK)

	override def paintComponent(g:Graphics){
		clearCanvas(g)
		plotAliens(g)
		plotMothership(g)
		plotPlayer(g)
	}

	def clearCanvas(g:Graphics){
	}

	def plotAliens(g:Graphics){
		//loop through some sort of array in Game
	}

	def plotMothership(g:Graphics){
	}
	def plotPlayer(g:Graphics){
	}
}
