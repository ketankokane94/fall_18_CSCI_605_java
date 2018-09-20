class StringThing {
    public static void method(String id, String literal, String aNewString)    {
        System.out.println(id + ".    " + (literal == aNewString ));// Always paranthesis first
    }
    public static void main( String args[] ) {
        String aString = "123";
        String bString = "123";
        String cString = "1" + "23";
        
        int number = 3;
        System.out.println("a.    " +     "123" == aString   );
        //Performs concat first and hence checks "a.  123" == aString which is false.
        /* 2 new string are created in above line
        a.  = x1
        x1 + false = x2
        */
        System.out.println("b.    " +   ( "12" + number == aString ) );
        //Performs "12" + 3 first = "123" then paranthesis so compares but concat so res false
        System.out.println("c.    " +   aString  + 1 * 23 );
        //Multiplies first then concats so "c.  12323"
         /* 3 new strings are created in the above line
          c.  == x1
          x1 + astring == x2
          x2 + 23 == x3
          x1, x2 and x3 will have no references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("d.    " +   123 + number + aString  );
        // Concats from left to right so 1233123
         /** 4 new string are  created in the above line
         * d.   == x1
         * x1 + 123 == x2
         * x2 + 3 == x3
         * x2 + astring == x4
         * x1, x2 x3 and x4 will have no references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("e.    " +   ( 123 + number ) + aString   );
        // Paranthesis performed first so 123 + 3 = 126 and then concat so e. 126123
         /** 3 new strings are created in the above line
         * e.  == x1
         * 126 + aString == x2
         * x1 + x2 == x3
         * x1, x2 x3  will no have references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("f.    " +   ( 123 - 2 + "" +  number + aString )  );
        // Paranthesis first then subtraction inside paran.(123 - 2 = 121) and then concat so f. 1213123
         /** 5 new strings are created in the above line
         * f.  == x1
         * "" == X2
         * 121 + x2 == x3
         * x3 + 3 == x4
         * x4 + aString = X5
         * x1, x2 x3 x4 and x5 will have no references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("g.    " +   123 * number + aString  );
        // Multiplcation first i.e. 123 * 3 = 369 then concat = g. 369123
        /** 3 new strings are created in the above line
         * g.  == x1
         * 369 + x1 == x2
         * x2 + aString == x3
         * x1, x2 x3  will no have references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("h.    " +   123 / number + aString  );
        // Division first => 123 / 3 = 41 then concat => h. 41123
        /** 3 new string are created in the above line
         * h.  == x1
         * 41 + x1 == x2
         * aString + x2 == x3
         * x1, x2 x3  will no have references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("i.    " +   ( 123 - number )  + aString  );
        // Paranthesis first => 123 - 3 = 120 then concat => i. 120123
           /** 3 new strings are created in the above string
         * i.  == x1
         * 120 + aString == x2
         * x1 + x2 == x3
         * x1, x2 x3  will no have references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("j.    " +     ( "123" == aString )   );
        // Compares memory location of "123" and mem loc in aString which is mem loc of "123"=> true
         /** 2 new strings are created in the above string 
         * j.  == x1
         * x1 + false == x2
         * x1, x2 will no have references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("g.    " +     ( "a" + "a" == "aa"  )   );
        // First concat => "a" + "a" = "aa" then compare mem loc of "aa" with "aa" => true
         /** 4 new strings are created in the above line
         * g.  == x1
         * a   == x2
         * aa  == x3
         * x2 + x2 == x3
         * x1 + true == x4
         * x1, x2 x3 and x4  will no have references pointing to them, and will be picked up by garbage collector
         */
        System.out.println("h.    " +     ( "123" == cString )   );
        // Same as j. so true only cString is formed as "1" + "23" but that is same as "123"
        System.out.println("1." + "x" == "x");
        // First concat => "1." + "x" = "1.x" and then compare its mem loc with mem loc of "x" => false
        
        method("1", "xyz", "x" + "yz");
        //"1" + ". " + ("xyz" == "x" + "yz") => Compares mem loc first => true and then concat => "1. true"
        /**
         * 1 == x1
         * . == x2
         * x == x3
         * yz== x4
         * x1 + x2 + false == x5
         * x1, x2 x3 x4 x5  will no have references pointing to them, and will be picked up by garbage collector
         */
        method("2", "xyz", new String("x") + "yz" );
        //"2" + ". " + ("xyz" == new String("x") + "yz") => First concat new String("x") with "y" which will create string "xyz" at new mem loc => comparison returns false
        /**
         * 2 == x1
         * x == x2
         * new string == x3
         * yz == x4
         * x3 + x4 == x5
         * . == x6 
         * x1, x2 x3 x4 x5 and x6 will no have references pointing to them, and will be picked up by garbage collector
         */
        method("3", "xyz", "x" + "y" +"z");
        //"3" + ". " + ("xyz" == "x" + "y" + "z") => Concatenate "x", "y" and "z" and compare its mem loc with mem loc of "xyz" which is the same => true
        /**
         *  3 == x1
         * . == x2
         * x == x3
         * y == x4
         * z == x5
         * x1 + x2 + bool == x6
         * x1, x2 x3 x4 x5 and x6  will no have references pointing to them, and will be picked up by garbage collector
         */
        method("4", "1" + "2" + "3", "1" + 2 * 1 + 3);
        //"4" + ". " + ("1" + "2" + "3" == "1" + 2*1 + 3) => "1" + 2 + 3 = "123" => "123" == "123" => true
        /**
         * 7 new stringa are created 
         * all the above created strings are eligible for garbage collection
         */
        method("5", "1" + "2" + "3", "1" + 2 + 3);
        //"5" + ". " + ("1" + "2" + "3" == "1" + 2 + 3) => "1" + 2 + 3 = "123" => "123" == "123" => true
        /**
         * 7 new strings are created
         * all the above created strings are eligible for garbage collection
         */
        method("6", "1" + "2" + "3", "1" + (3 - 1)  + 3);
        //"6" + ". " + ("1" + "2" + "3" == "1" + (3 - 1) + 3) => 3 - 1 = 2 => "1" + 2 + 3 = "123" => "123" == "123" => true
        /**
         * 7 new strings are created
         * all the above created strings are eligible for garbage collection
         */
    }
}
