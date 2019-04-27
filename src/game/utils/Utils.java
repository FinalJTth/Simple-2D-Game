package game.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.Entity;
import game.entity.creature.Player;

public class Utils {

	public static ArrayList<Shape> shapeToRender = new ArrayList<Shape>();
	public static void addToRenderShape(Shape s) {
		shapeToRender.add(s);
	}
	
	public static ArrayList<BufferedImage> imageToRender = new ArrayList<BufferedImage>();
	public static void addToRenderImg(BufferedImage img) {
		imageToRender.add(img);
	}
	
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder(); // help adding character to string

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt (String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static BufferedImage rotateImage(BufferedImage img, double theta) {
		AffineTransform tx= new AffineTransform();
		tx.rotate(theta, img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		img = op.filter(img, null);
		return img;
	}
	
	public static BufferedImage resize(BufferedImage img, float scale) { 
		int newW = (int) (img.getWidth() * scale), newH = (int) (img.getHeight() * scale);
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	// debugging stuff
	public static void printAllEntities(GameThread gameThread) {
		for(Entity e : gameThread.getWorld().getEntityManager().getEntities()) {
			System.out.println(e);
		}
	}
	
	public static void printPlayerXYCoords(GameThread gameThread) {
		Player player = gameThread.getWorld().getEntityManager().getPlayer();
		System.out.println("X : " + player.getxPos() + ", Y : " + player.getyPos());
	}
	

}
