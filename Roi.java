import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Roi {

    public int xmin;
    public int xmax;
    public int ymin;
    public int ymax;
    public Node root1 = new Node();
    public Node root2 = new Node();
    public LinkedList<Node> tree1 = new LinkedList<Node>();
    public LinkedList<Node> tree2 = new LinkedList<Node>();

    public boolean check(Node[][] n)
    {

        for (int i = this.xmin; i <= this.xmax; i++)
        {
            for (int j = this.ymin; j <= this.ymax; j++)
            {
                if (n[i][j].residualPower <= 0)
                    return false;
            }
        }
        return true;
    }

    public void create(Node[][] n)
    {
    	System.out.println("root is selected");
        int i, j;
        for (i = this.xmin; i <= this.xmax; i++)
        {
            for (j = this.ymin; j <= this.ymax; j++)
            {
                if (n[i][j].residualPower != 100)
                    return;
            }
        }
        System.out.println("initializing");
        Node root1 = new Node();
        Node root2 = new Node();
        for (i = this.xmin; i <= this.xmax; i++)
        {
            for (j = this.ymin; j <= this.ymax; j++)
            {
                n[i][j].residualPower -= 15 * 8 * n[i][j].residualPower / 100;
                n[i][j].residualPower -= 8*3*n[i][j].residualPower/100;
                System.out.println("find root");
                int row = xmin + 1, k;
                int max = 0;
                for (k = ymin; k <= ymax; k++)
                {
                    if (n[row][k].residualPower > max)
                    {
                        max = n[row][k].residualPower;
                        root1.coords.x = row;
                        root1.coords.y = k;
                    }
                }
                row = xmax - 1;
                for (k = ymin; k <= ymax; k++)
                {
                    if (n[row][k].residualPower > max)
                    {
                        max = n[row][k].residualPower;
                        root1.coords.x = row;
                        root1.coords.y = k;
                    }
                }
                System.out.println("root2");
                max = 0;
                int col = ymin + 1;
                for (k = xmin; k <= xmax; k++)
                {
                    if (n[k][col].residualPower > max)
                    {
                        max = n[k][col].residualPower;
                        root2.coords.x = k;
                        root2.coords.y = col;
                    }
                }
                col = ymax - 1;
                for (k = xmin; k <= xmax; k++)
                {
                    if (n[k][col].residualPower > max)
                    {
                        max = n[k][col].residualPower;
                        root2.coords.x = k;
                        root2.coords.y = col;
                    }
                }

            }
        } 
        System.out.println("form trees");

        System.out.println("neighbors");
        Map<Node, Integer> d = new HashMap<Node, Integer>();
        for (i = xmin; i <= xmax; i++)
        {
            for (j = ymin; j <= ymax; j++)
            {
            	
                if ((i == root1.coords.x && j == root1.coords.y) || (i == root2.coords.x && j == root2.coords.y))
                {
                    d.put(n[i][j], 0);
                    continue;
                }
                else
                {
                    if (neigh(n[i][j], root1, xmin, ymin, xmax, ymax))
                    {
                        d.put(n[i][j], 1);

                        if (neigh(n[i][j], root2, xmin, ymin, xmax, ymax))
                        {
                            if (d.containsKey(n[i][j]))
                                d.put(n[i][j], 3);
                            else
                                d.put(n[i][j], 2);
                            continue;
                        }
                        //for all the other nodes
                        d.put(n[i][j], 4);

                    }
                }
              

            }
        }
        System.out.println("4");
        //traverse the dictionary, assign parent and add them to appropriate linked list
        for (java.util.Map.Entry<Node, Integer> kvp :d.entrySet())
        {
            if (d.get(kvp.getKey()) == 0)
                continue;
            if (d.get(kvp.getKey()) == 1)
            {
                kvp.getKey().parent = root1;
                tree1.push(kvp.getKey());
                continue;
            }
            if (d.get(kvp.getKey()) == 2)
            {
                kvp.getKey().parent.equals(root2);
                tree2.push(kvp.getKey());
                continue;
            }
            if (d.get(kvp.getKey()) == 3)
            {
                kvp.getKey().parent = maxneigh(n, kvp.getKey(), xmin, ymin, xmax, ymax);
                continue;
            }
            
            if (d.get(kvp.getKey()) == 4)
            {
                if (tree1.size() >= tree2.size())
                {
                    kvp.getKey().parent = root1;
                    tree1.push(kvp.getKey());
                }
                else
                {
                    kvp.getKey().parent = root2;
                    tree2.push(kvp.getKey());
                }
                continue;
            }
            

        }
        //put the nodes with no connection with root in one of the trees
        for (java.util.Map.Entry<Node, Integer> kvp :d.entrySet())
        {
            if (kvp.getValue() == 3)
            {
                if (rootval(n, kvp.getKey(), root1, root2).equals(root1))
                    tree1.push(kvp.getKey());
                else if (rootval(n, kvp.getKey(), root1, root2).equals(root2))
                    tree2.push(kvp.getKey());
            }
        }
    }


    public boolean neigh(Node n, Node root, int minx, int miny, int maxx, int maxy)
    {
        int i, j, k, l, m;
        if (root.coords.x + 1 <= maxx)
            i = root.coords.x + 1;
        else
            i = root.coords.x;
        if (root.coords.y + 1 <= maxy)
            j = root.coords.y + 1;
        else
            j = root.coords.y;
        if (root.coords.x - 1 >= minx)
            k = root.coords.x - 1;
        else
            k = root.coords.x;
        if (root.coords.y - 1 >= miny)
            l = root.coords.y - 1;
        else
            l = root.coords.y;
        
        for (m = k; m <= i; m++)
        {
        	
            if (n.coords.x == m && n.coords.y == l)
                return true;
        }
        for (m = k; m <= i; m++)
        {
            if (n.coords.x == m && n.coords.y == root.coords.y)
                return true;
        }
        for (m = k; m <= i; m++)
        {
            if (n.coords.x == m && n.coords.y == j)
                return true;
        }

        return false;
    }
    public Node maxneigh(Node[][] node, Node root, int minx, int miny, int maxx, int maxy)
    {
        int i, j, k, l, m, max = -1;
        Node nmax = new Node();
        // nmax.residualPower = -1;
        if (root.coords.x + 1 <= maxx)
            i = root.coords.x + 1;
        else
            i = root.coords.x;
        if (root.coords.y + 1 <= maxy)
            j = root.coords.y + 1;
        else
            j = root.coords.y;
        if (root.coords.x - 1 >= minx)
            k = root.coords.x - 1;
        else
            k = root.coords.x;
        if (root.coords.y - 1 >= miny)
            l = root.coords.y - 1;
        else
            l = root.coords.y;

        for (m = k; m <= i; m++)
        {
            //m and l
            if (node[m][l].residualPower > max)
            {
                nmax = node[m][l];
                max = nmax.residualPower;
            }
        }
        for (m = k; m <= i; m++)
        {
            //m and root.coords.y
            if (node[m][root.coords.y].residualPower > max)
            {
                nmax = node[m][root.coords.y];
                max = nmax.residualPower;
            }

        }
        for (m = k; m <= i; m++)
        {
            //m and j
            if (node[m][j].residualPower > max)
            {
                nmax = node[m][j];
                max = nmax.residualPower;
            }
        }

        return nmax;
    }
     public Node rootval(Node [][] node, Node child, Node root1, Node root2) 
     {
    	 if(child.parent==null){
    		 return child;
    	 }
         if (child.parent != null)
         {
               //check if parent is either root1 or root2
             if (child.parent.equals(root1))
                 return root1;
             if (child.parent.equals(root2))
                 return root2;
         }
         return rootval(node, child.parent, root1, root2);
    }
}
