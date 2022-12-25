package controller;

import java.util.EventListener;

public interface ChessEventListener extends EventListener {
	public void blancheListener(ChessEvent evt);
	public void noirListener(ChessEvent evt);
}