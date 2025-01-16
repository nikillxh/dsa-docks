import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ASCIIArt {
    public String[] generateASCIIArt(String imagePath) throws IOException{
        BufferedImage image = ImageIO.read(new File(imagePath));

        int resWidth = 100;
        System.out.println("G");
        int width = image.getWidth();
        int height = image.getHeight();
        int resHeight = (height * resWidth) / width;
        
        BufferedImage resImage = resizeImage(image, resWidth, resHeight);

        return convertASCII(resImage);
    }

    public BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_REPLICATE);
        BufferedImage resImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resImage;
    }

    public String[] convertASCII(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        String pixel_ascii_map = "‘^ ;Ii~+ -\\?\\]\\[1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao∗#MW&8%\\$";

        String[] asciiArt = new String[height];

        for (int y = 0; y < height; y++) {
            StringBuilder row = new StringBuilder();

            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int avgBrightness = (r + g + b) / 3;

                int charIndex = (avgBrightness * (pixel_ascii_map.length() - 1)) / 255;
                row.append(pixel_ascii_map.charAt(charIndex));
            }
            asciiArt[y] = row.toString();
        }

        return asciiArt;
    }

    public static void main(String[] args) {
        ASCIIArt asciiObject = new ASCIIArt();

        try {
            String imagePath = args[0];
            String[] asciiArt = asciiObject.generateASCIIArt(imagePath);
            
            for (String lineRender: asciiArt) {
                System.out.println(lineRender);
            }
        } catch (IOException e) {
            System.err.println("Dog water image: " + e.getMessage());
        }
    }
}