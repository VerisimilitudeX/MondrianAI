import java.awt.Color;
import java.util.*;

/**
 * This class contains the methods to draw a basic and complex Mondrian image.
 * 
 * @Author Piyush Acharya
 * @Date 07/07/2023
 * @Class CSE 123
 * @TA Hitesh Boinpally
 */
public class Mondrian {
    private int canvasHeight;
    private int canvasWidth;

    /**
     * Basic Mondrian Image.
     * 
     * @param pixels the 2D array of pixels
     * @param x0                 the x coordinate of the top left corner of the region
     * @param y0                 the y coordinate of the top left corner of the region
     * @param width  the width of the region
     * @param height the height of the region
     */
    public void paintBasicMondrian(Color[][] pixels) {
        this.canvasHeight = pixels.length;
        this.canvasWidth = pixels[0].length;

        drawBasic(pixels, 0, 0, canvasWidth, canvasHeight);
    }

    /**
     * Draws a basic Mondrian image on the canvas.
     * 
     * @param pixels the 2D array of pixels
     * @param x0                 the x coordinate of the top left corner of the region
     * @param y0                 the y coordinate of the top left corner of the region
     * @param width  the width of the region
     * @param height the height of the region
     */
    private void drawBasic(Color[][] pixels, int x0, int y0, int width, int height) {
        if (height < 10 || width < 10) {
            return;
        }

        if (height >= 0.25 * canvasHeight && width >= 0.25 * canvasWidth) {
            int heightSplit = (int) (Math.random() * (height - 10)) + 10;
            int widthSplit = (int) (Math.random() * (width - 10)) + 10;

            drawBasic(pixels, x0, y0, widthSplit, heightSplit);
            drawBasic(pixels, x0 + widthSplit, y0, width - widthSplit, heightSplit);
            drawBasic(pixels, x0, y0 + heightSplit, widthSplit, height - heightSplit);
            drawBasic(pixels, x0 + widthSplit, y0 + heightSplit, width - widthSplit, height - heightSplit);
        } else if (height >= 0.25 * canvasHeight) {
            int heightSplit = (int) (Math.random() * (height - 10)) + 10;

            drawBasic(pixels, x0, y0, width, heightSplit);
            drawBasic(pixels, x0, y0 + heightSplit, width, height - heightSplit);
        } else if (width >= 0.25 * canvasWidth) {
            int widthSplit = (int) (Math.random() * (width - 10)) + 10;

            drawBasic(pixels, x0, y0, widthSplit, height);
            drawBasic(pixels, x0 + widthSplit, y0, width - widthSplit, height);
        } else {
            Color[] colors = { Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE };
            Random random = new Random();
            Color randomColor = colors[random.nextInt(colors.length)];

            for (int i = y0 + 1; i < y0 + height - 1; i++) {
                for (int j = x0 + 1; j < x0 + width - 1; j++) {
                    pixels[i][j] = randomColor;
                }
            }
            // Draw border
            for (int i = y0; i < y0 + height; i++) {
                for (int j = x0; j < x0 + width; j++) {
                    if (i == y0 || j == x0 || i == y0 + height - 1 || j == x0 + width - 1) {
                        pixels[i][j] = Color.BLACK;
                    }
                }
            }
        }
    }

    /**
     * Color Related to Location in addition to simple draw method.
     * For each pixel, the color is determined by its location. The color is
     * determined by the ratio of the x and y coordinates to the width and height
     * of the canvas. The red component of the color is determined by the x
     * coordinate, the blue component by the y coordinate, and the green component
     * by the average of the x and y coordinates.
     * 
     * @param pixels the 2D array of pixels
     * @param x0                 the x coordinate of the top left corner of the region
     * @param y0                 the y coordinate of the top left corner of the region
     * @param width  the width of the region
     * @param height the height of the region
     */
    private void drawComplex(Color[][] pixels, int x0, int y0, int width, int height) {
        if (height < 10 || width < 10) {
            return;
        }

        if (height >= 0.25 * canvasHeight && width >= 0.25 * canvasWidth) {
            int heightSplit = (int) (Math.random() * (height - 10)) + 10;
            int widthSplit = (int) (Math.random() * (width - 10)) + 10;

            drawComplex(pixels, x0, y0, widthSplit, heightSplit);
            drawComplex(pixels, x0 + widthSplit, y0, width - widthSplit, heightSplit);
            drawComplex(pixels, x0, y0 + heightSplit, widthSplit, height - heightSplit);
            drawComplex(pixels, x0 + widthSplit, y0 + heightSplit, width - widthSplit, height - heightSplit);
        } else if (height >= 0.25 * canvasHeight) {
            int heightSplit = (int) (Math.random() * (height - 10)) + 10;

            drawComplex(pixels, x0, y0, width, heightSplit);
            drawComplex(pixels, x0, y0 + heightSplit, width, height - heightSplit);
        } else if (width >= 0.25 * canvasWidth) {
            int widthSplit = (int) (Math.random() * (width - 10)) + 10;

            drawComplex(pixels, x0, y0, widthSplit, height);
            drawComplex(pixels, x0 + widthSplit, y0, width - widthSplit, height);
        } else {
            Color color = getColorBasedOnLocation(x0, y0);

            for (int i = y0 + 1; i < y0 + height - 1; i++) {
                for (int j = x0 + 1; j < x0 + width - 1; j++) {
                    pixels[i][j] = color;
                }
            }

            // Draw border
            for (int i = y0; i < y0 + height; i++) {
                for (int j = x0; j < x0 + width; j++) {
                    if (i == y0 || j == x0 || i == y0 + height - 1 || j == x0 + width - 1) {
                        pixels[i][j] = Color.BLACK;
                    }
                }
            }
        }
    }

    /**
     * Returns a color that forms a gradient from red in the top left,
     * through various shades of purple and teal, to blue in the bottom right,
     * with a green diagonal.
     * 
     * @param x0 the x coordinate of the top left corner of the region
     * @param y0 the y coordinate of the top left corner of the region
     * @return the color of the pixel
     */
    private Color getColorBasedOnLocation(int x0, int y0) {
        float xRatio = (float) x0 / canvasWidth;
        float yRatio = (float) y0 / canvasHeight;

        int red = (int) (255 * xRatio);
        int blue = (int) (255 * yRatio);
        int green = (int) (255 * (1 - Math.abs(xRatio - yRatio)));

        return new Color(red, green, blue);
    }

    /**
     * Paints a complex Mondrian image on the canvas.
     * 
     * @param pixels the 2D array of pixels
     */
    public void paintComplexMondrian(Color[][] pixels) {
        this.canvasHeight = pixels.length;
        this.canvasWidth = pixels[0].length;

        drawComplex(pixels, 0, 0, canvasWidth, canvasHeight);
    }
}