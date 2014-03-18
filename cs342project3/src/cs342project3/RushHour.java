package cs342project3;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.io.File;

public class RushHour extends JFrame{
	private Canvas canvas = new Canvas();
	private JMenuBar menuBar;
	private JMenu game, help,levels;
	private JMenuItem load, reset, hint, exit, helpMenu, about;
	private JMenuItem levelItems[]= new JMenuItem[10];
	private JFileChooser fc;
	public static void main(String[] args) {
		/*For Mac Users, sets the menu bar on the top*/
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
		/*Creates the window*/
		RushHour rh = new RushHour();
		rh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Constructor for the main window
	 */
	public RushHour(){
		MainWindowListener mwl = new MainWindowListener();
		fc = new JFileChooser();
		menuBar = new JMenuBar();
		/*Top Level Menu-Game options, Help Options*/
		game = new JMenu("Game");
		help = new JMenu("Help");
		levels = new JMenu("Levels");
		hint = new JMenu("Hint");
		
		help.setMnemonic('H');
		game.setMnemonic('G');
		menuBar.add(game);
		menuBar.add(levels);
		menuBar.add(help);
		
		/*The Game Menu*/
		reset = new JMenuItem("Reset");
		reset.setMnemonic('R');
		hint = new JMenuItem("Hint");
		helpMenu = new JMenuItem("Rush Hour Help");
		helpMenu.setMnemonic('S');
		about= new JMenuItem("About");
		about.setMnemonic('A');
		exit = new JMenuItem("Exit");
		exit.setMnemonic('X');
		for(int i=0; i<9; i++){
			levelItems[i]= new JMenuItem("Level "+i);
		}
		load = new JMenuItem("Open File...");

		/*Listeners for all the menus*/
		for(int i=0; i<9; i++){
			levelItems[i].addActionListener(mwl);
		}
		load.addActionListener(mwl);
		hint.addActionListener(mwl);
		reset.addActionListener(mwl);
		exit.addActionListener(mwl);
		helpMenu.addActionListener(mwl);
		about.addActionListener(mwl);

		/*Adding them to the game menu*/
		game.add(load);
		game.add(reset);
		game.add(hint);
		game.add(exit);
		for(int i=0; i<9; i++)
			levels.add(levelItems[i]);
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
		/**
		 * Main Window Listener for the main window.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0; i<9; i++){
				if(levelItems[i]==e.getSource())
				{
					canvas.load(i);
					break;
				}
			}
			if(reset == e.getSource())
			{
				canvas.reset();
				
			}
			if(load == e.getSource())
			{
				int returnVal = fc.showOpenDialog(null);
				 
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                //This is where a real application would open the file.
	                canvas.load(file);
	            } else {
	            	System.out.println("They canceled");
	            }
			}
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
