package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Button extends JButton {
	
	public Button(String label, String icon, Color bgColor) {
		// TODO Auto-generated constructor stub
		setText(label);
		Image iconButton = new ImageIcon("images/" + icon).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(iconButton));
		
        setIcon(new ImageIcon(iconButton));
        setHorizontalAlignment(SwingConstants.LEFT);
		
		setMargin(new Insets(10, 10, 10, 10));
		setFocusPainted(false);
        setFont(new Font("Glue Gun", Font.PLAIN, 16));
        setBackground(bgColor);
        setForeground(Color.WHITE);
	}
}
