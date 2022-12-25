package workers;

import javax.swing.SwingWorker;

import controller.ChessController;

public class InitBlackWorker extends SwingWorker<Void, Void> {
	
	private ChessController chessController;
	
	public InitBlackWorker(ChessController chessController) {
		// TODO Auto-generated constructor stub
		this.chessController = chessController;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		chessController.initNoir();
		return null;
	}

}
