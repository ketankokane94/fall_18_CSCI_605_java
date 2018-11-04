
import java.util.*;

public class T_7 extends Thread    {
    static Object o = new Object();
    static int    counter = 0;
    int id;

    public T_7(int id)	{
        this.id = id;
    }
    public void run () {
        if ( ++counter == 1 ) {
            try {
                sleep(100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            o = new Object();
        }
        else
            o = new Object();

        synchronized ( o ) {
            System.err.println(id + " --->" );
            try {
                sleep(100);
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        new T_7(1).start();
        try {
            sleep(100);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        new T_7(2).start();
        try {
            sleep(100);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        new T_7(3).start();
    }
}

/*

public class T_7 extends Thread    {
    static Object o = new Object();
    static int    counter = 0;
    int id;

    public T_7(int id)	{
        this.id = id;
    }
    public void run () {
        if ( ++counter == 1 )
            o = new Object();
        else
            o = new Object();

        try {
            sleep(500);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        synchronized ( o ) {
            System.err.println(id + " --->" );
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        new T_7(1).start();
        try {
            sleep(100);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        new T_7(2).start();
        try {
            sleep(100);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        new T_7(3).start();

        // Whichever order we start threads in, they will print in that order same format
        // of o/p below
    }
}

O/P :

1 --->
1 <---
2 --->
2 <---
3 --->
3 <---

 */