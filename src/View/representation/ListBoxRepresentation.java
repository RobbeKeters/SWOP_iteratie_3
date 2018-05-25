package View.representation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Model.Canvas;
import Model.Label;
import Model.ListBox;

/**
 * A visual representation of a list box.
 */
public class ListBoxRepresentation {

	private String text;
	private ArrayList<Label> arguments = new ArrayList<Label>();
	private int x, y, width, height;
	private boolean selected;
	
	/**
	 * Constructor.
	 * @param lb	The list box to represent.
	 */
	public ListBoxRepresentation(ListBox lb) {
//		super(x, y, width, height);
		this.width = lb.getWidth();
		this.height = lb.getHeight();
		this.x = lb.getOriginX();
		this.y = lb.getOriginY();
		this.selected =lb.getSelected();
//		this.text = text;
//		for(int i = 0; i < 10; i++){
//			Label label = new Label("");
//			label.setSelected(true);
//			arguments.add(label);
//			
//		}
	}
	
	/**
	 * Draws the list box's representation.
	 * @param c		The canvas to which the actor belongs to.
	 * @param g		The graphics element used to draw.
	 */
	public void draw(Canvas c, Graphics g){
//		super.draw(c, g);
//		drawText(c, g);
		if(selected)
			g.setColor(Color.BLUE);
		else
			g.setColor(Color.BLACK);
		drawRectangle(c, g);
//		for(int i = 0; i < arguments.size(); i++){
////			if((g.getFontMetrics().getHeight() + 2)*(i+1) <= height){
//				if(arguments.get(i).getSelected())
//					g.setColor(Color.BLUE);
//				drawRectangle(c, g);	
//				g.setColor(Color.black);
////			}
//			
//		}
	}
	
//	private void drawRectangle(Canvas c, Graphics g){
//		g.drawRect(x + c.getOrigineX(), y + c.getOrigineY(), width, height);
//	}
	
	private void drawRectangle(Canvas c, Graphics g){
		g.drawRect(x - 5, y - g.getFontMetrics().getHeight(), width, height);
	}
	
	/**
	 * Sets this representation's arguments to the given arguments.
	 * @param args		The given arguments.
	 */
	public void setArguments(ArrayList<Label> args){
		this.arguments = args;
	}
	
	public void setSelected(boolean s){
		this.selected = s;
	}
}
