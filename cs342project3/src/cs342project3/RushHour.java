package cs342project3;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class RushHour extends JFrame{
	private Canvas canvas = new Canvas();
	private JMenuBar menuBar;
	private JMenu game, help;
	private JMenuItem newGame, exit, topTen, helpMenu, about, resetScores;
	
	public static void main(String[] args) {
		/*For Mac Users, sets the menu bar on the top*/
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
		/*Creates the window*/
		RushHour rh = new RushHour();
		rh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public RushHour(){
		MainWindowListener mwl = new MainWindowListener();
		
		menuBar = new JMenuBar();
		/*Top Level Menu-Game options, Help Options*/
		game = new JMenu("Game");
		help = new JMenu("Help");
		help.setMnemonic('H');
		game.setMnemonic('G');
		menuBar.add(game);
		menuBar.add(help);
		
		/*The Game Menu*/
		newGame = new JMenuItem("New Game");
		newGame.setMnemonic('R');
		helpMenu = new JMenuItem("Rush Hour Help");
		helpMenu.setMnemonic('S');
		about= new JMenuItem("About");
		about.setMnemonic('A');
		exit = new JMenuItem("Exit");
		exit.setMnemonic('X');
		
		/*Listeners for all the menus*/
		newGame.addActionListener(mwl);
		exit.addActionListener(mwl);
		helpMenu.addActionListener(mwl);
		about.addActionListener(mwl);

		/*Adding them to the game menu*/
		game.add(newGame);
		game.add(exit);
		
		/*Adding them to the help menu*/
		help.add(helpMenu);
		help.add(about);

		setJMenuBar(menuBar);
		add(canvas);
		/*end setup of the menubar*/
		pack();
		setVisible(true);
	}
	private class MainWindowListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(exit == e.getSource()){
				System.exit(0);
			}
			/*Display the rules of the game*/
			if(helpMenu == e.getSource())
			{
				JOptionPane.showMessageDialog(null, "Good Luck!");
			}
		}
	}
}
