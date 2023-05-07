package textproc;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map.Entry;

import javax.swing.*;


public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
}
    private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
    
		SortedListModel<Entry<String, Integer>> model = new SortedListModel<>(counter.getWordList());
		 JList<Entry<String, Integer>> myList = new JList<>(model);
		 JScrollPane scrollPane = new JScrollPane(myList);
		 
		 //Skapar 
		 JPanel menu = new JPanel();
		 JRadioButton alp = new JRadioButton("Alphabetic", true);
		 JRadioButton fre = new JRadioButton("Frequency");
		 
		 	ButtonGroup group = new ButtonGroup();
		 	group.add(alp);
		 	group.add(fre);
		 	
		 JTextField textruta = new JTextField();
		 textruta.setPreferredSize(new Dimension(125,20));
		 JButton search = new JButton("Search");
		 
		 
		 //Lägger till
		 menu.add(alp);
		 menu.add(fre);
		 menu.add(textruta);
		 menu.add(search);
		 pane.add(menu, BorderLayout.SOUTH);
		 pane.add(scrollPane);
		 
		 
		 // Lägger till vad som ska hända i menyn
		 
		 alp.addActionListener(event -> model.sort( (m1, m2) -> m1.getKey().compareTo(m2.getKey())));
		 fre.addActionListener(event -> model.sort( (m1, m2) -> m2.getValue()- m1.getValue()));
		
		 search.addActionListener(event -> { // triggas av sökknappen
			 
			 String input = textruta.getText().trim().toLowerCase(); // ser till att versaler + mellanslag funkar
			 
			 for (int i = 0; i < model.getSize(); i++) {
				 
				 if (input.equals(model.getElementAt(i).getKey())) {
					 myList.ensureIndexIsVisible(i);
					 myList.setSelectedIndex(i);
					 return;
				 }
				 
			 } JOptionPane.showMessageDialog(null, "Word not in list."); // felmeddelande om ord ej i lista
		 });
		 
		 textruta.addActionListener(event -> { //triggas av enter
			 
			 String input = textruta.getText().trim().toLowerCase(); // ser till att versaler + mellanslag funkar
			 
			 for (int i = 0; i < model.getSize(); i++) {
				 
				 if (input.equals(model.getElementAt(i).getKey())) {
					 myList.ensureIndexIsVisible(i);
					 myList.setSelectedIndex(i);
					 return;
				 }
				 
			 } JOptionPane.showMessageDialog(null, "Word not in list."); // felmeddelande om ord ej i lista
		 });
		 
       frame.pack();
       frame.setVisible(true);
     
 } 
	
}
