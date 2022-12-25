package controller;

import javax.swing.event.EventListenerList;

public class ChessEventNotifieur {
	private EventListenerList listenerListBlanche = new EventListenerList();
	private EventListenerList listenerListNoir = new EventListenerList();
	
	public void addChessBlancheEventListener(ChessEventListener listener) {
		listenerListBlanche.add(ChessEventListener.class, listener);
	}

	public void removeBlancheChessEventListener(ChessEventListener listener) {
		listenerListBlanche.remove(ChessEventListener.class, listener);
	}

	public void diffuserBlancheChessEvent(ChessEvent evt) {
		Object[] listeners = listenerListBlanche.getListenerList();
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == ChessEventListener.class) {
				((ChessEventListener) listeners[i + 1]).blancheListener(evt);
			}
		}
	}
	
	public void addChessNoirEventListener(ChessEventListener listener) {
		listenerListNoir.add(ChessEventListener.class, listener);
	}

	public void removeNoirChessEventListener(ChessEventListener listener) {
		listenerListNoir.remove(ChessEventListener.class, listener);
	}

	public void diffuserNoirChessEvent(ChessEvent evt) {
		Object[] listeners = listenerListNoir.getListenerList();
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == ChessEventListener.class) {
				((ChessEventListener) listeners[i + 1]).noirListener(evt);
			}
		}
	}
}