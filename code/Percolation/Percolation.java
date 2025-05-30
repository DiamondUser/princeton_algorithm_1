//import libs from algs4.jar
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF UF;
    private final WeightedQuickUnionUF antiWashBackUF;
    private final int n;
    private int nums_of_opened;
    private boolean[] isOpen;
    private final int virtualtop,virtualbottom;
    // creates n-by-n grid, with all sites initially blocked

    public Percolation(int n)
    {
        if(n <= 0)
        {
            throw new IllegalArgumentException("Expect the n > 0.");
        }
        this.n = n;
        nums_of_opened = 0;
        UF = new WeightedQuickUnionUF(n*n + 2);
        antiWashBackUF = new WeightedQuickUnionUF(n*n + 1); //without virtualbottom
        isOpen = new boolean[n*n + 2];      //add virtualtop and virtualbottom
        this.virtualtop = 0;
        this.virtualbottom = n*n + 1;
        isOpen[this.virtualbottom] = true;
        isOpen[this.virtualtop] = true;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if( row < 1 || row > n)
        {
            throw new IllegalArgumentException("row is out of bounds.");
        }
        if( col < 1 || col > n)
        {
            throw new IllegalArgumentException("col is out of bounds.");
        }
        int LinearID = getLinearID(row,col);
        isOpen[LinearID] = true;
        nums_of_opened++;
        if(row==1)
        {
            UF.union(LinearID,virtualtop);
            antiWashBackUF.union(LinearID,virtualtop);
        }
        if(row>1 && isOpen(row-1,col))               //union to the site above (row,col) iff opened
        {
            UF.union(LinearID,getLinearID(row-1,col));
            antiWashBackUF.union(LinearID,getLinearID(row-1,col));
        }
        if(row<this.n && isOpen(row+1,col))          //union to the site below (row,col) iff opened
        {
            UF.union(LinearID,getLinearID(row+1,col));
            antiWashBackUF.union(LinearID,getLinearID(row+1,col));
        }
        if(col>1 && isOpen(row,col-1))               //union to the site at left of (row,col) iff opened
        {
            UF.union(LinearID,getLinearID(row,col-1));
            antiWashBackUF.union(LinearID,getLinearID(row,col-1));
        }
        if(col<this.n && isOpen(row,col+1))          //union to the site at right of (row,col) iff opened
        {
            UF.union(LinearID,getLinearID(row,col+1));
            antiWashBackUF.union(LinearID,getLinearID(row,col+1));
        }
        if(row==n)
        {
            UF.union(LinearID,virtualbottom);
            //antiWashBackUF doesn't have virtualbottom site
        }

    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if( row < 1 || row > n)
        {
            throw new IllegalArgumentException("row is out of bounds. row = " + row);
        }
        if( col < 1 || col > n)
        {
            throw new IllegalArgumentException("col is out of bounds. col = " + col);
        }
        return isOpen[getLinearID(row,col)];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if( row < 1 || row > n)
        {
            throw new IllegalArgumentException("row is out of bounds.");
        }
        if( col < 1 || col > n)
        {
            throw new IllegalArgumentException("col is out of bounds.");
        }
        return antiWashBackUF.find(getLinearID(row,col)) == virtualtop;
    }
    /**
     * @return number of open sites
     **/
    public int numberOfOpenSites()
    {
        return nums_of_opened;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return UF.find(virtualbottom) == UF.find(virtualtop);
    }
    private int getLinearID(int row, int col)
    {
        return (row - 1) * this.n + col;
    }
    // test client (optional)
    public static void main(String[] args)
    {

    }
}
