import java.io.File;
import java.io.IOException;
import java.util.Random;

import jxl.Cell;
import jxl.CellType;
import jxl.Workbook;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class Algorithm {
	
	public static void main(String[] args) throws IOException, RowsExceededException, WriteException{
        String path = "dtda.xls";
        
        File inputWorkbook = new File(path);
	    WritableWorkbook writableWorkbook = Workbook.createWorkbook(inputWorkbook);
	    WritableSheet sheet = writableWorkbook.createSheet("Sheet1", 0);
        int rowc = 0;
        Node [][] grid = new Node[33][34];
        Roi grid1 = new Roi();
        grid1.xmin = 0; grid1.xmax = 3; grid1.ymin = 1; grid1.ymax = 4;
        Roi grid2 = new Roi();
        grid2.xmin = 4; grid2.xmax = 7; grid2.ymin = 1; grid2.ymax = 4;
        Roi grid3 = new Roi();
        grid3.xmin = 0; grid3.xmax = 3; grid3.ymin = 5; grid3.ymax = 8;
        Roi grid4 = new Roi();
        grid4.xmin = 4; grid4.xmax = 7; grid4.ymin = 5; grid4.ymax = 8;
        for(int i=0; i<9; i++)
        {
            for (int j = 0; j < 33; j++ ) {
                grid[i][j] = new Node();
                grid[i][j].coords.x = i;
                grid[i][j].coords.y = j;
                grid[i][j].residualPower = 100;
            }
        }
        while (grid1.check(grid) == true || grid2.check(grid) == true || grid3.check(grid) == true || grid4.check(grid) == true)
        {
      System.out.println("entered the loop");
           grid1.create(grid); 
           grid2.create(grid); 
           grid3.create(grid);
           grid4.create(grid);
            int time = 60;
            int x;
            int y;
            while (time > 0)
            {
                Random rand = new Random();
                //generate random numbers in all four grids, note them in tables, send it to root, from there send it to mobile sink
                //grid 1
                x = rand.nextInt(4);
                y = 0;
                while(y<1){
                	System.out.println(y);
                y = rand.nextInt(5);}
                addNumber(sheet,rowc,1,x);
                addNumber(sheet,rowc,2,y);
                addNumber(sheet,rowc,3,grid[x][y].residualPower);
                if (grid1.tree1.contains(grid[x][y])) { 
                    Node t = grid[x][y];
                    while(t.parent!=null){
                        t.residualPower -= 8 * t.residualPower / 100;
                        t.parent.residualPower -= 3 * t.residualPower / 100;
                        t = t.parent;
                    }
                    grid1.root1.residualPower -= 3 * t.residualPower / 100;
                    grid1.root1.residualPower -= 8 * t.residualPower / 100;
                }
                else {
                    Node t = grid[x][y];
                    while (t.parent != null)
                    {
                        t.residualPower -= 8 * t.residualPower / 100;
                        t.parent.residualPower -= 3 * t.residualPower / 100;
                        t = t.parent;
                    }
                    System.out.println(grid1);
                    System.out.println(grid1.root2);
                    grid1.root2.residualPower -= 3 * t.residualPower / 100;
                    grid1.root2.residualPower -= 8 * t.residualPower / 100;
                    
                }
                System.out.println("grid2");
                //grid 2
                x=3;
                while(x<4){
                x = rand.nextInt(8);}
                y=0;
                while(y<1){
                y = rand.nextInt(5);}
                rowc++;
                addNumber(sheet,rowc,1,x);
                addNumber(sheet,rowc,2,y);
                addNumber(sheet,rowc,3,grid[x][y].residualPower);
                
                if (grid1.tree1.contains(grid[x][y]))
                {
                    Node t = grid[x][y];
                    while (t.parent != null)
                    {
                        t.residualPower -= 8 * t.residualPower / 100;
                        t.parent.residualPower -= 3 * t.residualPower / 100;
                        t = t.parent;
                    }
                    grid1.root1.residualPower -= 3 * t.residualPower / 100;
                    grid1.root1.residualPower -= 8 * t.residualPower / 100;
                }
                else
                {
                    Node t = grid[x][y];
                    while (t.parent != null)
                    {
                        t.residualPower -= 8 * t.residualPower / 100;
                        t.parent.residualPower -= 3 * t.residualPower / 100;
                        t = t.parent;
                    }
                    grid1.root2.residualPower -= 3 * t.residualPower / 100;
                    grid1.root2.residualPower -= 8 * t.residualPower / 100;

                }

                System.out.println("grid3");
                
                x = rand.nextInt(4);
                y=4;
                while(y<5){
                y = rand.nextInt(9);}
                rowc++;
                addNumber(sheet,rowc,1,x);
                addNumber(sheet,rowc,2,y);
                addNumber(sheet,rowc,3,grid[x][y].residualPower);
                
                if (grid1.tree1.contains(grid[x][y]))
                {
                    Node t = grid[x][y];
                    while (t.parent != null)
                    {
                        t.residualPower -= 8 * t.residualPower / 100;
                        t.parent.residualPower -= 3 * t.residualPower / 100;
                        t = t.parent;
                    }
                    grid1.root1.residualPower -= 3 * t.residualPower / 100;
                    grid1.root1.residualPower -= 8 * t.residualPower / 100;
                }
                else
                {
                    Node t = grid[x][y];
                    while (t.parent != null)
                    {
                        t.residualPower -= 8 * t.residualPower / 100;
                        t.parent.residualPower -= 3 * t.residualPower / 100;
                        t = t.parent;
                    }
                    grid1.root2.residualPower -= 3 * t.residualPower / 100;
                    grid1.root2.residualPower -= 8 * t.residualPower / 100;

                }
                //grid 4
                x=3;
                while(x<4){
                x = rand.nextInt(8);}
                y=4;
                while(y<5){
                y = rand.nextInt(9);}
                
                rowc++;
                addNumber(sheet,rowc,1,x);
                addNumber(sheet,rowc,2,y);
                addNumber(sheet,rowc,3,grid[x][y].residualPower);
                
              
                if (grid1.tree1.contains(grid[x][y]))
                {
                    Node t = grid[x][y];
                    while (t.parent != null)
                    {
                        t.residualPower -= 8 * t.residualPower / 100;
                        t.parent.residualPower -= 3 * t.residualPower / 100;
                        t = t.parent;
                    }
                    grid1.root1.residualPower -= 3 * t.residualPower / 100;
                    grid1.root1.residualPower -= 8 * t.residualPower / 100;
                }
                else
                {
                    Node t = grid[x][y];
                    while (t.parent != null)
                    {
                        t.residualPower -= 8 * t.residualPower / 100;
                        t.parent.residualPower -= 3 * t.residualPower / 100;
                        t = t.parent;
                    }
                    grid1.root2.residualPower -= 3 * t.residualPower / 100;
                    grid1.root2.residualPower -= 8 * t.residualPower / 100;
                }
                time--;

            }

        }
        writableWorkbook.write();
        writableWorkbook.close();
        System.out.println("completed the algorithm");
	}
	

	  private static void addNumber(WritableSheet sheet, int column, int row,
	      Integer integer) throws WriteException, RowsExceededException {
	    jxl.write.Number number = null;
	    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
	    WritableCellFormat times = new WritableCellFormat(times10pt);
	    number = new jxl.write.Number(column, row, integer, times);
	    sheet.addCell(number);
	  }

}
