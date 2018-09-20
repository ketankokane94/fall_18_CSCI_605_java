class Parent
{
    public int id;
    public Parent(int id)
    {
        System.out.println(getClass() + "Parent(" + id + ")");
        this.id = id;
    }

    public int getId()
    {
        System.out.println(getClass() + " getId");
        return id;
    }
}

class Child extends Parent
{
    int id;
    public Child(int id)
    {
        super(id);
        System.out.println(getClass() + "Child(" + id + ")");
        this.id = id;
    }

    public int getId()
    {
        System.out.println(getClass() + " getId");
        return id;
    }
}

public class RitBank
{
    public static void main(String args[])
    {
        Child child = new Child(1);
        Parent parent = (Parent) child;
        System.out.println("Id is : " + child.getId());
    }
}

