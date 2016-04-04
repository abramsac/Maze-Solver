import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class MazeViewer extends JComponent {

	private static final long serialVersionUID = 1L;
	private  final MazeSolver parent;
	
	public MazeViewer(MazeSolver parent) {
		this.parent = parent;
		setPreferredSize(new Dimension(500,500));
	}

    @Override
    public void paintComponent(Graphics graphics) {
    	int[][] maze = parent.getMaze();
    	int cellSize = Math.min(getWidth()/maze[0].length, getHeight()/maze.length);
    	graphics.setColor(Color.GRAY);
    	graphics.fillRect(0, 0, getWidth(), getHeight());
    	
        for (int row = 0; row < maze.length; row++)
            for (int col = 0; col < maze[0].length; col++)
                drawCell(graphics, maze[row][col], row, col, cellSize);
    }
    
    private void drawCell(Graphics graphics, int mazeCellValue, int row, int col, int cellSize) {
        switch (mazeCellValue) {
        case MazeSolver.WALL:
            graphics.setColor(Color.BLACK);
            break;
        case MazeSolver.OPEN:
            graphics.setColor(Color.WHITE);
            break;
        case MazeSolver.GOAL:
            graphics.setColor(Color.RED);
            break;
        case MazeSolver.START:
            graphics.setColor(Color.GREEN);
            break;
        case MazeSolver.ACTIVE:
            graphics.setColor(Color.CYAN);
            break;
        case MazeSolver.SOLUTION:
            graphics.setColor(Color.GREEN);
            break;
        }
        graphics.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
        graphics.setColor(Color.GRAY); // border
        graphics.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
    }
}
