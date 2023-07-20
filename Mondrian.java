import java.awt.Color;
import java.util.Random;

public class Mondrian {
    private int canvasHeight;
    private int canvasWidth;
    private boolean dimensionsSet;

    public Mondrian() {
        dimensionsSet = false;
    }

    public void paintBasicMondrian(Color[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;

        if (!dimensionsSet) {
            canvasHeight = height;
            canvasWidth = width;
            dimensionsSet = true;
        }

        if (height < 10 || width < 10) {
            return;
        }

        if (height >= 0.25 * canvasHeight && width >= 0.25 * canvasWidth) {
            int heightRange = height - 10;
            int heightSplit = (int) (Math.random() * heightRange) + 10;

            int widthRange = width - 10;
            int widthSplit = (int) (Math.random() * widthRange) + 10;

            Color[][] region1 = new Color[heightSplit][widthSplit];
            paintBasicMondrian(region1);

            Color[][] region2 = new Color[heightSplit][width - widthSplit];
            paintBasicMondrian(region2);

            Color[][] region3 = new Color[height - heightSplit][widthSplit];
            paintBasicMondrian(region3);

            Color[][] region4 = new Color[height - heightSplit][width - widthSplit];
            paintBasicMondrian(region4);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i < heightSplit && j < widthSplit) {
                        pixels[i][j] = region1[i][j];
                    } else if (i < heightSplit && j >= widthSplit) {
                        pixels[i][j] = region2[i][j - widthSplit];
                    } else if (i >= heightSplit && j < widthSplit) {
                        pixels[i][j] = region3[i - heightSplit][j];
                    } else {
                        pixels[i][j] = region4[i - heightSplit][j - widthSplit];
                    }
                }
            }
        } else if (height >= 0.25 * canvasHeight) {
            int heightRange = height - 10;
            int heightSplit = (int) (Math.random() * heightRange) + 10;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i == heightSplit) {
                        pixels[i][j] = Color.BLACK;
                    } else if (i > heightSplit) {
                        pixels[i][j] = Color.WHITE;
                    } else {
                        pixels[i][j] = Color.RED;
                    }
                }
            }
        } else if (width >= 0.25 * canvasWidth) {
            int widthRange = width - 10;
            int widthSplit = (int) (Math.random() * widthRange) + 10;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (j == widthSplit) {
                        pixels[i][j] = Color.BLACK;
                    } else if (j > widthSplit) {
                        pixels[i][j] = Color.WHITE;
                    } else {
                        pixels[i][j] = Color.YELLOW;
                    }
                }
            }
        } else {
            Color[] colors = {Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE};
            Random random = new Random();
            Color randomColor = colors[random.nextInt(colors.length)];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                        pixels[i][j] = Color.BLACK;
                    } else {
                        pixels[i][j] = randomColor;
                    }
                }
            }
        }
    }

    public void paintComplexMondrian(Color[][] pixels) {
        // TODO: Implement the complex Mondrian art generator
    }
}