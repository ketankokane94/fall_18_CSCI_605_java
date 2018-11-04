import java.util.*;

public class T_5 extends Thread    {
    /*
        Example case here :
        2 entered run
        made counter = 1
        2 slept
        1 in run
        1 slept
        2 woke up and did new Object()
        entered synchronized
        3 went directly to synchronized and has to wait for 2
        1 did new Object()
        entered synchronized
        2 slept in synchronized after 2 -->
        1 slept after 1 -->
        2 printed 2 <--
        3 entered syn
        slept after 3 -->
        1 <--
        3 <--
     */
    static Object o = new Object();
    static int    counter = 0;
    int id;

    public T_5(int id)	{
        this.id = id;
    }
    public void run () {
        if ( ++counter == 1 ) {
            // Makes the thread sleep after incrementing counter
            // which makes one thread create object and enter
            try {
                sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            o = new Object();
        }

        synchronized ( o ) {
            System.err.println(id + " --->" );
            try {
                sleep(1000);
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        new T_5(1).start();
        new T_5(2).start();
        new T_5(3).start();
    }
}

/*
public class T_5 extends Thread    {
    static Object o = new Object();
    static int    counter = 0;
    int id;

    public T_5(int id)	{
        this.id = id;
    }
    public void run () {
        if ( ++counter == 1 )
            o = new Object();

        synchronized ( o ) {
            System.err.println(id + " --->" );
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        // 1
        new T_5(2).start();
        new T_5(1).start();
        new T_5(3).start();

        // 2
        //new T_5(1).start();
        //new T_5(2).start();
        //new T_5(3).start();

        // 3
        //new T_5(3).start();
        //new T_5(2).start();
        //new T_5(1).start();

    }
}

O/Ps :
1 :

2 --->
2 <---
3 --->
3 <---
1 --->
1 <---

OR

2 --->
2 <---
1 --->
1 <---
3 --->
3 <---

OR

2 :

1 --->
1 <---
3 --->
3 <---
2 --->
2 <---

OR

1 --->
1 <---
2 --->
2 <---
3 --->
3 <---

OR

3 :

3 --->
3 <---
2 --->
2 <---
1 --->
1 <---

OR

3 --->
3 <---
1 --->
1 <---
2 --->
2 <---

 */


/*

public class T_5 extends Thread    {
    /*
        Example case here :
        2 entered run
        made counter = 1
        2 slept
        1 in run
        1 slept
        2 woke up and did new Object()
        entered synchronized
        3 went directly to synchronized and has to wait for 2
        1 did new Object()
        entered synchronized
        2 slept in synchronized after 2 -->
        1 slept after 1 -->
        2 printed 2 <--
        3 entered syn
        slept after 3 -->
        1 <--
        3 <--

static Object o = new Object();
    static int    counter = 0;
    int id;

    public T_5(int id)	{
        this.id = id;
    }
    public void run () {
        if ( ++counter == 1 ) {
            // Makes the thread sleep after incrementing counter
            // which makes one thread create object and enter
            try {
                sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            o = new Object();
        }

        synchronized ( o ) {
            System.err.println(id + " --->" );
            try {
                sleep(1000);
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        // 1 :
        new T_5(1).start();
        new T_5(2).start();
        new T_5(3).start();

        // 2 :
        new T_5(2).start();
        new T_5(1).start();
        new T_5(3).start();

        // 3 :
        new T_5(3).start();
        new T_5(2).start();
        new T_5(1).start();
    }
}

O/P :
1 :

2 --->
1 --->
2 <---
3 --->
1 <---
3 <---

OR
2 :

1 --->
2 --->
1 <---
3 --->
2 <---
3 <---

OR
3 :

2 --->
3 --->
2 <---
1 --->
3 <---
1 <---

 */