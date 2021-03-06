import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MandelbrotFX extends Application {

    WritableImage [] mandelBrotSetImage;
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

        /*
          Get commandline argument using get parameters and pass to constructor
          Default if no cmd line arguments n = number of cores - 1 since one core is for the main thread
         */
        int n = Runtime.getRuntime().availableProcessors() - 1;

        if (getParameters().getRaw().size() == 1) {
            n = Integer.parseInt(getParameters().getRaw().get(0));
        }

        MandelbrotSet aMandelbrotSet = new MandelbrotSet(IMG_WIDTH, IMG_HEIGHT, n);

        init();
        mandelBrotSetImage = aMandelbrotSet.createImage();
        end("Multiple Thread MandelbrotSet Test");


        ImageView aImage [] = new ImageView[n];
        for (int imageIndex = 0; imageIndex < n; imageIndex++) {
            aImage[imageIndex] = new ImageView();
            aImage[imageIndex].setImage(mandelBrotSetImage[imageIndex]);
        }

        StackPane root = new StackPane();
        for (int imageIndex = 0; imageIndex < n; imageIndex++) {
            root.getChildren().add(aImage[imageIndex]);
        }

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

        int startX, startY, endX, endY;
        // This is one position after end of previous thread
        startX = (pixelPositionCounter - 1) * (width / num_threads);
        startY = (pixelPositionCounter - 1) * (height / num_threads);
        if (pixelPositionCounter == num_threads) {
            endX = width;
            endY = height;
        }
        else {
            endX = startX + width / num_threads + 1;
            endY = startY + height /  num_threads + 1;
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
        }
        //System.out.println("Thread " + pixelPositionCounter + " : Count = " + count);
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
    public WritableImage [] createImage()	{

        mandelBrotSetImage = new WritableImage(width, height);
        aPixelWriter = mandelBrotSetImage.getPixelWriter();

        WritableImage [] mandelBrotSetImageArray = new WritableImage[num_threads];

        Thread threadArray [] = new Thread[num_threads];
        for (int threadIndex = 0; threadIndex < num_threads; threadIndex++) {
            mandelBrotSetImageArray[threadIndex] = new WritableImage(width, height);
            PixelWriter pixelWriter = mandelBrotSetImageArray[threadIndex].getPixelWriter();
            allThreads[threadIndex] = new MandelbrotSet(width, height, num_threads, threadIndex + 1, pixelWriter);
            allThreads[threadIndex].start();

        }

        for (int threadIndex = 0; threadIndex < num_threads; threadIndex++) {
            try {
                allThreads[threadIndex].join();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        return mandelBrotSetImageArray;
        //return mandelBrotSetImage;
    }
}

/*
 Best possible n = number of cores - 1

 For n = number of cores - 1,
 We use all the available cores and hence we get the maximum possible speed up.
 We distribute the work into n parts with all n threads running parallely to complete
 the task.
 */

/*
 ALTERNATE METHOD
 */

/*
         This thread gets color for pixel at positions that have pixelPositionCounter
         as remainder on dividing by number of threads
          */
        /*for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int position = (x * width) + y;
                if (position % num_threads == pixelPositionCounter) {
                    aPixelWriter.setColor(x, y, determineColor(x, y));
                    count++;
                }
            }
        }*/
