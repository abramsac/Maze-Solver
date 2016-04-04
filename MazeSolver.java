import java.util.LinkedList;

import javax.swing.JFrame;


public class MazeSolver {

	public class Location {

		public int row;
		public int col;

		public Location(int row, int col){
			this.row = row;
			this.col = col;
		}

		public int getRow(){
			return row;
		}

		public int getCol(){
			return col;
		}

		public void setCol(int newCol){
			col = newCol;
		}

		public void setRow(int newRow){
			row = newRow;
		}

		public String toString(int row, int col){
			return "(" + row + "," + col + ")";
		}

	}

	public static final int WALL = 1, OPEN = 0, GOAL = 2, START = 3;
	public static final int ACTIVE = 4, SOLUTION = 5;

	public LinkedList<Location> locations = new LinkedList<Location>();

	private MazeViewer view = new MazeViewer(this);

	private int[][] maze = {

{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
{1, 0, 2, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
{1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1},
{1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
{1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
{1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
{1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
{1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
{1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1},
{1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
{1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
{1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
{1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
{1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
{1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};

	public MazeSolver() {
		JFrame maze = new JFrame();
		maze.add(view);
		maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		maze.pack();
		maze.setLocation(20,50); // pixels, relative to upper left of screen
		maze.setVisible(true);
	}

	public int[][] getMaze() {
		
		return maze;
	}

	public void solve() {

		Location predecessor[][] = new Location [getMaze().length][getMaze()[0].length];

		for (int row = 0; row < getMaze().length; row++)
			for (int col = 0; col < getMaze()[0].length; col++)
				if (getMaze()[row][col] == 3) {
					locations.add(new Location(row, col));
				}

		for (int row = 0; row < getMaze().length; row++)
			for (int col = 0; col < getMaze()[0].length; col++)
				predecessor[row][col] = null;

		Location loc = null;
		while (locations.isEmpty() != true) {
			loc = locations.remove();
			if (getMaze()[(loc.getRow())][(loc.getCol())] == GOAL){
				break;
			}
			else if (getMaze()[(loc.getRow())][(loc.getCol())] == WALL){
				continue;
			}
			else if (getMaze()[(loc.getRow())][(loc.getCol())] == ACTIVE){
				continue;
			}
			else{
				getMaze()[loc.getRow()][(loc.getCol())] = ACTIVE;
				view.repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int i = -1;
				while (i < 2) {
					if (getMaze()[(loc.getRow())+ i][(loc.getCol())] == OPEN || getMaze()[(loc.getRow())+ i][(loc.getCol())] == GOAL) {
						locations.add(new Location((loc.getRow()) + i, (loc.getCol())));
						predecessor[(loc.getRow()) + i][(loc.getCol())] = loc;
					}
					if (getMaze()[(loc.getRow())][(loc.getCol()) + i] == OPEN || getMaze()[(loc.getRow())][(loc.getCol()) + i] == GOAL) {
						locations.add(new Location((loc.getRow()), (loc.getCol()) + i));
						predecessor[(loc.getRow())][(loc.getCol()) + i] = loc;
					}
					i += 2;
				}

			}
		}
			if (locations.isEmpty()) {
				System.out.println("Unsolvable Maze");
			}
			else {
				getMaze()[(loc.getRow())][(loc.getCol())] = SOLUTION;
				view.repaint();
				while (predecessor[(loc.getRow())][(loc.getCol())] != null) {
					Location news = predecessor[(loc.getRow())][(loc.getCol())];
					getMaze()[news.getRow()][news.getCol()] = SOLUTION;
					view.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					loc = news;
				}
			}
		
	}
	public static void main(String[] args){
		MazeSolver newmaze = new MazeSolver();
		newmaze.solve();
	}
}
	    		



