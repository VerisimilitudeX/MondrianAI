import java.awt.Color;
import java.util.Random;

public class Mondrian {
    private int canvasHeight;
    private int canvasWidth;

    public Mondrian() {}

    public void paintBasicMondrian(Color[][] pixels) {
        this.canvasHeight = pixels.length;
        this.canvasWidth = pixels[0].length;
        
        draw(pixels, 0, 0, canvasWidth, canvasHeight);
    }

    private void draw(Color[][] pixels, int x0, int y0, int width, int height) {
        if (height < 10 || width < 10) {
            return;
        }

        if (height >= 0.25 * canvasHeight && width >= 0.25 * canvasWidth) {
            int heightSplit = (int) (Math.random() * (height - 10)) + 10;
            int widthSplit = (int) (Math.random() * (width - 10)) + 10;

            draw(pixels, x0, y0, widthSplit, heightSplit);
            draw(pixels, x0+widthSplit, y0, width-widthSplit, heightSplit);
            draw(pixels, x0, y0+heightSplit, widthSplit, height-heightSplit);
            draw(pixels, x0+widthSplit, y0+heightSplit, width-widthSplit, height-heightSplit);
        } else if (height >= 0.25 * canvasHeight) {
            int heightSplit = (int) (Math.random() * (height - 10)) + 10;

            draw(pixels, x0, y0, width, heightSplit);
            draw(pixels, x0, y0+heightSplit, width, height-heightSplit);
        } else if (width >= 0.25 * canvasWidth) {
            int widthSplit = (int) (Math.random() * (width - 10)) + 10;

            draw(pixels, x0, y0, widthSplit, height);
            draw(pixels, x0+widthSplit, y0, width-widthSplit, height);
        } else {
            Color[] colors = {Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE};
            Random random = new Random();
            Color randomColor = colors[random.nextInt(colors.length)];

            for (int i = y0+1; i < y0+height-1; i++) {
                for (int j = x0+1; j < x0+width-1; j++) {
                    pixels[i][j] = randomColor;
                }
            }
            // Draw border
            for (int i = y0; i < y0+height; i++) {
                for (int j = x0; j < x0+width; j++) {
                    if (i==y0 || j==x0 || i==y0+height-1 || j==x0+width-1) {
                        pixels[i][j] = Color.BLACK;
                    }
                }
            }
        }
    }

    public void paintComplexMondrian(Color[][] pixels) {
        
    }
}