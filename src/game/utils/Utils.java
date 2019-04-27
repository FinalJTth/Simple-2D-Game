package game.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

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

}
