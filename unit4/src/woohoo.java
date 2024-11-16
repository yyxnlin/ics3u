
public class woohoo {

	public static void main(String[] args) {
		int[][] board = {{1, 2, 3}, 
						 {4, 5, 6}, 
						 {-7, 8, 9},
						 {10, -1, 22}};
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}

	}

}
