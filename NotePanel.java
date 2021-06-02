package Q2;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
/*
 * BY: Doron Sharaby
 * ID: 204862197
 * DATE: 22/5/2021
 * 
 * */
public class NotePanel  extends JPanel{

	public HashMap<DateF, String> note;

	private DateF curDate; 
	private DateF newDate; 
	private JTextArea text; 

	public NotePanel(){ 
		this(new HashMap<DateF,String>());
	}	 

	public NotePanel(HashMap<DateF, String> Note) {
		note =Note;
		curDate = new DateF();
		newDate = new DateF(curDate);
	

		setLayout(new BorderLayout());
		JPanel header = new JPanel();

		header.setLayout(new FlowLayout());
		header.setBackground(Color.YELLOW);
		
		header.add(new JLabel("Date: "));

		//  set Day
		JComboBox<Item> day = new JComboBox<Item>();

		for (int i = 1; i<=31; i++)
			day.addItem(new Item(i, i+""));

		day.setSelectedIndex(curDate.getDay() - 1);
		day.setName("Day");
		day.setForeground(Color.BLUE);
		day.addActionListener(new ComboBoxHandler());
		header.add(day);
		header.add(new JLabel("."));

		//set Month
		
		JComboBox<Item> month = new JComboBox<Item>();
		
		for (Integer ID : curDate.MonthsName.keySet())
			month.addItem(new Item(ID,curDate.MonthsName.get(ID)));

		month.setSelectedIndex(curDate.getMonth());
		month.setName("Month");
		month.setForeground(Color.BLUE);
		month.addActionListener(new ComboBoxHandler());
		header.add(month);
		header.add(new JLabel("."));

		// set Year
		JComboBox<Item> year = new JComboBox<Item>();
		
		for (int i = curDate.getYear() -30; i<=curDate.getYear(); i++)
			year.addItem(new Item(i, i+""));

		year.setSelectedIndex(30);
		year.setName("Year");
		year.setForeground(Color.BLUE);
		
		year.addActionListener(new ComboBoxHandler());
		header.add(year);

		//set Show
		JButton show = new JButton("Show");
		
		
		show.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str1 =text.getText();
				String str2 = (note.get(curDate) == null)? "" : note.get(curDate);
				if(!str1.equals(str2))
				{
					int ans = JOptionPane.showConfirmDialog(null, "Would you like to save the note before continue?",
							"The Note is not saved: "+curDate.toString(),JOptionPane.YES_NO_OPTION);
					
					if (ans == 0){
						note.put(curDate,text.getText());
						curDate = new DateF(newDate);
						noteByDate();
					}
					
					if (ans == 1){
						curDate = new DateF(newDate);
						noteByDate();
					}
					
	
				}
				else {
					curDate = new DateF(newDate);
					noteByDate();
				}
			}
		});
		header.add(show);
		add(header,BorderLayout.NORTH);
		
		text = new JTextArea(15,15);// where the data is placed.
		text.setForeground(Color.BLUE);
		text.setBackground(Color.yellow);
		noteByDate();
		add(text,BorderLayout.CENTER);
		
		
		//saving note.
		JButton save = new JButton("Save");
		
		save.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				note.put(curDate,text.getText());
				JOptionPane.showMessageDialog(null, "The Note is Saved.","",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(save,BorderLayout.SOUTH);
	}
	
	private void noteByDate(){
		String str = note.get(newDate);
		text.setText(str);
		System.out.printf("Note:%s%n",str);
	}

	private class ComboBoxHandler extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox <Item> c = (JComboBox <Item>) e.getSource();
			Item item = (Item) c.getSelectedItem();
			switch (c.getName()){
			case "Year":
				newDate.setYear(item.getID());
				break;
			case "Month":
				newDate.setMonth(item.getID());
				break;
			case "Day":
				newDate.setDay(item.getID());
				break;


			}
		}
	}
	
	
	//class of the item in combo box
	private class Item {
		

	    private int ID;
	    private String data;

	    public Item(int ID, String data) {
	        this.ID = ID;
	        this.data = data;
	    }

	    public int getID() {
	        return ID;
	    }
	    

	    @Override
	    public String toString() {
	        return data;
	    }
	
}

}
