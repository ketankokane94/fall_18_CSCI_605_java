public class test {

    public static void main(String args[] )      {
        SetI<String> aSet = new MyHashSet<String>();
        SetI<String> bSet = new MyHashSet<String>();

        String[] aStrings = { "a", "b", "c" };
        String[] bStrings = { "A", "B", "C" };
        aSet.add(aStrings[0]); aSet.add(aStrings[1]);           // setup a, b
        bSet.add(bStrings[0]); bSet.add(bStrings[1]);           // setup A, B

        System.out.println("aSet = " + aSet );                  // --> a, b

        for (int index = 0; index < aStrings.length; index ++ ) {       // contans a and b, not c
            System.out.println("does " +
                    ( aSet.contains(aStrings[index]) ? "" : " not " ) + "contain: " +
                    aStrings[index] );
        }
        System.out.println("aSet = " + aSet );                  // --> a, b

        System.out.println("aSet.remove(aStrings[0]); = " + aSet.remove(aStrings[0]) ); // contains b
        System.out.println("aSet.remove(aStrings[2]); = " + aSet.remove(aStrings[2]) ); // can not remove x
        System.out.println("aSet = " + aSet );

        aSet.addAll(bSet);                                      // --> b, A, B
        System.out.println("aSet = " + aSet );


        aSet.add(null);                                         // --> b, A, B, null
        System.out.println("aSet = " + aSet );
        System.out.println("aSet.remove(null); = " + aSet.remove(null) );       // can remove null
    }
}
