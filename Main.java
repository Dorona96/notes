package Q2;
import java.awt.*;
import java.io.*;
import java.util.HashMap; 
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.*;


/*
 * BY: Doron Sharaby
 * ID: 204862197
 * DATE: 22/5/2021
 * 
 * An application that allows the user to write reminders and associate them with equal dates.
 *The user will be able to retrieve and update previously written reminders and will be able to add new reminders.
 * Each date can be assigned one reminder consisting of free text.
 * */


public class Main {

	private static NotePanel Note;
	public static void main(String[]args) {	

		//read data from file option.
		if (JOptionPane.showConfirmDialog(null, "Would you like to load data from file?","Open File",JOptionPane.YES_NO_OPTION) == 0)
		{
			try{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int res;
				do{
					res = fileChooser.showDialog(null,"Please select a file");
				}while (res == JFileChooser.CANCEL_OPTION
						|| fileChooser.getSelectedFile().toPath() ==  null
						|| !Files.exists(fileChooser.getSelectedFile().toPath()));
				Path path = fileChooser.getSelectedFile().toPath();
				ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path));
				HashMap<DateF,String> notes = (HashMap<DateF,String>)input.readObject();
				Note = new NotePanel(notes);

			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Couldn't read from file","invalid Input!",JOptionPane.ERROR_MESSAGE);
				Note = new NotePanel();
			}
		}
		else { //open new blank note.
			Note = new NotePanel();
		}
		final JFrame window = new JFrame();
		window.add(Note);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.pack();
		window.setMinimumSize(new Dimension(window.getWidth(),window.getHeight()));
		window.setVisible(true);
		window.addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				int answer = JOptionPane.showConfirmDialog(window,
						"Would you like to save the notes?", "Close Window?",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (answer != JOptionPane.CLOSED_OPTION)
				{
					if (answer == JOptionPane.YES_OPTION)
					{
						JFileChooser fileChooser = new JFileChooser();
						int userCommand = fileChooser.showSaveDialog(null);
						if (userCommand == JFileChooser.APPROVE_OPTION) {
							try
							{
								File file = fileChooser.getSelectedFile();
								ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));

								outputStream.writeObject(Note.note);
								outputStream.close();

							}
							catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Couldn't save the file","invalid input!",JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				}
				System.exit(0);
			}
		});// end window listener.
	}
}
