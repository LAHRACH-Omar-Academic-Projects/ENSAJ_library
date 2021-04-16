package gestionBibliotheque.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.border.AbstractBorder;

public class CustomeBorder extends AbstractBorder{
	private static final long serialVersionUID = 1L;
	private int thickness;
	private Color color;
	private Point radius;
	
	public CustomeBorder(int thickness, Color color, Point radius) {
		this.thickness = thickness;
		this.color = color;
		this.radius = radius;
	}
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,
            int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(color);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius.x, radius.y);
    }   
}
