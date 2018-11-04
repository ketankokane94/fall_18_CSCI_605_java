import java.util.*;

public class T_6 extends Thread    {
    String o = " ";
    int id;

    public T_6(int id)	{
        this.id = id;
    }
    public void run () {
        try {
            sleep(100);
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
        new T_6(1).start();
        try {
            sleep(100);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        new T_6(2).start();
        try {
            sleep(100);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        new T_6(3).start();
    }
}

/*
o  =  " " hence, only one object and key and hence sync behavior
    Without sleep :

O/P :

1 --->
1 <---
3 --->
3 <---
2 --->
2 <---

 */

/*

public class T_6 extends Thread    {
    String o = " ";
    int id;

    public T_6(int id)	{
        this.id = id;
    }
    public void run () {
        synchronized ( o ) {
            System.err.println(id + " --->" );
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        new T_6(1).start();
        try {
            sleep(100);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        new T_6(2).start();
        try {
            sleep(100);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        new T_6(3).start();

        // Whatever order of 1,2,3 is used to create and run thread here will be the order in o/p
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