package controller;

import java.util.EventObject;

public class ChessEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6091212841464841468L;
	private Object donnee;

	public ChessEvent(Object source, Object donnee) {
		super(source);
		this.donnee = donnee;
	}

	public Object getDonnee() {
		return this.donnee;
	}
}