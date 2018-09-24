 interface B
{
    int a = 0;
    void methodB();
}

interface BB
{
    static int a = 1;
    void methodBB();
}

class A
{
    int a = 2;

    void parentMethod()
    {
        System.out.println("parentMethod");
    }
}

class AA extends A implements B, BB
{
    int a = 3;

    void childMethod()
    {
        System.out.println("childMethod");
    }

    ;

    public void methodB()
    {
        System.out.println("In methodB");
    }

    public void methodBB()
    {
        System.out.println("In methodBB");
    }
}

public class Casting
{

    public static void main(String[] args)
    {
        // here we are creating an instance of class A
        A a = new A();

        // why is this the only method we can call?
        // a is an object of class A which has only one method i.e. parentMethod() and
        // A does not extend or implement any other class/interface.
        a.parentMethod();


        // why doesn't this work?
        // a is not static and therefore it can only be accessed through an object and
        // not directly through the class.
        //System.out.println(A.a);

        // here we are creating an instance of class AA
        AA aa = new AA();

        // class AA doesn't define a parentMethod, how can we call one?
        // class AA extends A which means class A is the super class of AA
        // class AA and A has a method called parentMethod.
        // A being the superclass is defined before AA.
        aa.parentMethod();

        // how could we override this method?
        // We can override this method if we make class Casting extend
        // class AA and then define a function childMethod in Casting.
        aa.childMethod();

        // Which class does this variable refer to?
        // Refers to class AA.
        System.out.println(aa.a);

        // Which class does this variable refer to?
        // The variable aa will refer to class AA.
        System.out.println(((A) aa).a);

        // What forces us to define these methods in the AA class?
        // class AA implements interfaces B and BB which makes it
        // a compulsion to define all the classes in AA that are
        // defined in them.
        aa.methodB();
        aa.methodBB();

        // here we are creating an instance of class AA but what is different about this reference?
        // we create a object of AA and typecast it to A
        a = new AA();

        // why do we need to cast this?
        // because a is of type A, which is pointing to object of AA,
        // to use methods of AA we need to cast it to AA again
        ((AA) a).childMethod();

        // Which class does this variable refer to?
        // this will refer to variable of class A, because we have type cast it to A, it will searchf for the var
        // in its local reference
        System.out.println(a.a);

        // Which class does this variable refer to?
        // this refers to class AA, because it is type casted to AA
        System.out.println(((AA)a).a);

        // call methodB and methodBB using the variable a
        ((AA)a).methodB();
        ((AA)a).methodBB();


        // how can we access these variables from the interfaces?
        // variables of interface are static by default

        System.out.println(B.a);
        System.out.println(BB.a);

    }
}