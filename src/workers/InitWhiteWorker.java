package workers;

import javax.swing.SwingWorker;

import controller.ChessController;

public class InitWhiteWorker extends SwingWorker<Void, Void> {
	
	private ChessController chessController;
	
	public InitWhiteWorker(ChessController chessController) {
		// TODO Auto-generated constructor stub
		this.chessController = chessController;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		chessController.initBlanche();
		return null;
	}

}
