import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MandelbrotFX extends Application {

    WritableImage mandelBrotSetImage;
    final int IMG_WIDTH 	= 800;
    final int IMG_HEIGHT 	= 800;
    long milliSeconds;

    public void init()  {
        milliSeconds = System.currentTimeMillis();
    }
    public void end(String s)   {
        System.err.println(s + ":       " + ( System.currentTimeMillis() - milliSeconds) + "ms" );
        System.err.println(" # of cores" +   ":       " +
                Runtime.getRuntime().availableProcessors() );
    }

    public void start(Stage theStage) {
        // Get commandline argument using get parameters and pass to constructor
        // Default if no cmd line arguments n = 3
        int n = 3;
        if (getParameters().getRaw().size() == 1) {
            n = Integer.parseInt(getParameters().getRaw().get(0));
        }

        MandelbrotSet aMandelbrotSet = new MandelbrotSet(IMG_WIDTH, IMG_HEIGHT, n);

        init();
        mandelBrotSetImage = aMandelbrotSet.createImage();
        end("Multiple Thread MandelbrotSet Test");


        ImageView aImage = new ImageView();
        aImage.setImage(mandelBrotSetImage);

        StackPane root = new StackPane();
        root.getChildren().add(aImage);

        Scene scene = new Scene(root, IMG_WIDTH, IMG_HEIGHT);

        theStage.setTitle("Mandelbrot Set");
        theStage.setResizable(false);
        theStage.setScene(scene);
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


class MandelbrotSet extends Thread {

    private static final int    MAX_COLORS 	= 256;
    private static final double BOUNDERY = 1000;
    private static int    width;
    private static int    height;
    private static WritableImage mandelBrotSetImage;
    private static PixelWriter aPixelWriter;
    private static final Color[] colors = new Color[MAX_COLORS];
    private static double minR  = -2.4;
    private static double maxR  = 0.9;
    private static double minI  = -1.3;
    private static double maxI  = 1.28;
    private static MandelbrotSet[] allThreads;
    private static int num_threads;
    private static int pixelPositionCounter;

    static {
        for (int index = 0; index < colors.length; index++) {
            colors[index] = Color.RED.interpolate(Color.BLUE, (( 1.0 / colors.length) * index) );
        }
    }

    // This constructor is needed for call when we want to use this class for threads
    // and hence avoid some things done in the other constructor.
    public MandelbrotSet(int width, int height, int num_threads, int pixelPositionCounter, PixelWriter aPixelWriter) {
        this.width = width;
        this.height = height;
        this.num_threads = num_threads;
        this.pixelPositionCounter = pixelPositionCounter;
        this.aPixelWriter = aPixelWriter;
    }
    public MandelbrotSet(int width,int height, int num_threads) {
        this.width = width;
        this.height = height;
        this.num_threads = num_threads;
        mandelBrotSetImage = new WritableImage(width, height);
        if ( allThreads == null )
            allThreads = new MandelbrotSet[width * height ];
    }

    @Override
    public void run() {
        int count = 0;

        /*
         This thread gets color for pixel at positions that have pixelPositionCounter
         as remainder on dividing by number of threads
          */
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int position = (x * width) + y;
                if (position % num_threads == pixelPositionCounter) {
                    aPixelWriter.setColor(x, y, determineColor(x, y));
                    count++;
                }
            }
        }
        /*int startX, startY, endX, endY;
        // This is one position after end of previous thread
        startX = (pixelPositionCounter - 1) * (width / num_threads);
        startY = (pixelPositionCounter - 1) * (height / num_threads);
        if (pixelPositionCounter == num_threads) {
            endX = width;
            endY = height;
        }
        else {
            endX = startX + width / num_threads;
            endY = startY + height /  num_threads;
        }
        for (int x = startX; x < endX; x++)
        {
            int yLower = 0;
            if (x == startX) {
                yLower = startY;
            }
            int yUpper = width;
            // Only for last row we will go till endY else till height
            if (x == endX - 1) {
                yUpper = endY;
            }
            for (int y = yLower; y < yUpper; y++) {
                //int position = (x * width) + y;
                //if (position % num_threads == pixelPositionCounter) {
                aPixelWriter.setColor(x, y, determineColor(x, y));
                count++;
                //}
            }
        }*/
        System.out.println("Thread " + pixelPositionCounter + " : Count = " + count);
    }

    private Color getColor(int count) {
        return count >= colors.length ?  Color.BLACK : colors[count];
    }
    private int calc(double re, double img ) {
        int    counter = 0;
        double length;
        double aComplexNumberRe = 0;
        double aComplexNumberImg = 0;
        double real = 0;
        double imaginary = 0;

        do {
            real       =  aComplexNumberRe * aComplexNumberRe -
                    aComplexNumberImg * aComplexNumberImg;
            imaginary  = aComplexNumberRe *  aComplexNumberImg +
                    aComplexNumberImg *  aComplexNumberRe;
            aComplexNumberRe   = real;
            aComplexNumberImg  = imaginary;
            aComplexNumberRe   += re;
            aComplexNumberImg  += img;
            length = aComplexNumberImg * aComplexNumberImg +
                    aComplexNumberRe * aComplexNumberRe;
            counter++;
        } while (counter < MAX_COLORS && ( length < BOUNDERY ) );
        return counter;
    }
    public Color determineColor(int x, int y)	{
        double re = (minR * (width - x) + x * maxR) / width;
        double img = (minI * (height - y) + y * maxI) / height;
        return getColor(calc(re, img));
    }
    public WritableImage createImage()	{
        mandelBrotSetImage = new WritableImage(width, height);
        aPixelWriter = mandelBrotSetImage.getPixelWriter();

        Thread threadArray [] = new Thread[num_threads];
        for (int threadIndex = 0; threadIndex < num_threads; threadIndex++) {
            allThreads[threadIndex] = new MandelbrotSet(width, height, num_threads, threadIndex, aPixelWriter);
            allThreads[threadIndex].start();

            // We need this thread to complete to get updated Pixel Writer for next thread.
            try {
                allThreads[threadIndex].join();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        /*for (int threadIndex = 0; threadIndex < num_threads; threadIndex++) {
            allThreads[threadIndex].start();
            try {
                allThreads[threadIndex].join();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }*/

        /*for(int ctr = 1; ctr <= num_threads; ctr++) {
            int startX, startY, endX, endY;
            // This is one position after end of previous thread
            startX = (ctr - 1) * (width / num_threads);
            startY = (ctr - 1) * (height / num_threads);
            if (ctr == num_threads) {
                endX = width;
                endY = height;
            }
            else {
                endX = width / num_threads;
                endY = height /  num_threads;
            }
            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    aPixelWriter.setColor(x, y, determineColor(x, y));
                }
            }
        }*/

        /*for (int threadIndex = 0; threadIndex < num_threads; threadIndex++) {
            try {
                allThreads[threadIndex].join();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }*/

        /*try {
            allThreads[0].join();
            allThreads[1].join();
            allThreads[2].join();
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }*/

        return mandelBrotSetImage;
    }
}
