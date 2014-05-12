import java.util.*;
public class BST{

  public static void main(String[] args)
  {
    MyTree database = new MyTree();
    int kill = 0;
    String command = null;
    int date = -1;
    String location = null;
    String[] split = null;
    Scanner sc = new Scanner(System.in);
    boolean quit = false;
    while(kill < 10000 && !quit)
    {
      command = sc.nextLine();
      String firstCommand = command.substring(0,1);
      int whatItWants = Integer.parseInt(firstCommand);
      if(whatItWants == 1)
      {
        split = command.split(" ");
        date = Integer.parseInt(split[1]);
        location = split[2];
        MyNode adding = new MyNode(date,location);
        database.add(adding);
      }
      else if(whatItWants == 2)
      {
        split = command.split(" ");
        date = Integer.parseInt(split[1]);
        database.search(database.root, date);
      }     
      else if(whatItWants == 3)
      {
        database.findSuccessor(database.root,database.current,null);
      }
      else if(whatItWants == 4)
      {
        quit = true;
      }
      kill++;
    }
  }
}

class MyNode 
{
  MyNode left;
  MyNode right;
  int date;
  String location;
  MyNode(int d, String s)
  {
    this.date = d;
    this.location = s;
    this.left = null;
    this.right = null;
  }
  public String toString()
  {
    return date + " " + location;
  }
  public void setLeft(MyNode node)
  {
   left = node;
  }
  public void setRight(MyNode node)
  {
   right = node;
  }
}

class MyTree
{
  MyNode root;
  MyNode current;
  MyTree()
  {
    root = null;
  }
  
  public void add(MyNode node)
  {
    if(root == null) 
    {
      root = node;
    }
    else
    {
      addNode(node,root);
    }
  }
  public void addNode(MyNode node,MyNode root)
  {
    if(node.date > root.date && root.right==null)
    {
      root.right = node;
    }
    else if(node.date < root.date && root.left==null)
    {
      root.left = node;
    }
    else if(node.date > root.date && root.right != null)
    {
      addNode(node,root.right);
    }
    else if(node.date < root.date && root.left != null)
    {
      addNode(node,root.left);
    }
  }
  
  public void search(MyNode node,int date)
  {
    current = null;
    if(node.date == date)
    {
      current = node;
      System.out.println(node.toString());
    }
    else if(date > node.date && node.right != null)
    {
      search(node.right,date);
    }
    else if(date < node.date && node.left != null)
    {
      search(node.left,date);
    }
    else
    {
      System.out.println(date + " NO GIG");
    }
  }
  
  public void findSuccessor(MyNode root, MyNode current,MyNode min)
  {
    if(current.right != null)
    {
      minimum(current.right);
    }
    else
    {
      if(current.date > root.date && root.right != null)
      {
        findSuccessor(root.right,current,min);
      }
      else if(current.date < root.date && root.left != null)
      {
        min = root;
        findSuccessor(root.left,current,min);
      }
      else if(current == root)
      {
        if(min == null)
        {
          System.out.println("No Next Gig");
        }
        else
        {
          System.out.println(min.toString());
        }
      }
    }
  }
  public void minimum(MyNode n)
  {
    if(n.left == null)
    {
      System.out.println(n.toString());
    }
    else
    {
      minimum(n.left);
    }
  }
}
