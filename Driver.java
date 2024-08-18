import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

public class Driver extends JFrame implements ActionListener {
	
	// Declare class data
	private JFrame frame;
	private JPanel panel;
	private JButton playButton;
	private JCheckBox checkBox;
	private JComboBox animationTime;
	private JLabel includeStopsLabel;
	private JMapViewer mapViewer;
	private static ArrayList<TripPoint> movingTrip;
	private static ArrayList<TripPoint> trip;
	private MapMarker currentMarker;
	private Timer animationTimer;
	 
	
	//
	Driver(){
		// Set up frame, include your name in the title
    	frame = new JFrame("Garrett");
    	setLayout(new FlowLayout(FlowLayout.CENTER));
    	setVisible(true);
    	setSize(500, 500);
    	setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        // Set up Panel for input selections
        panel = new JPanel();
    	
        // Play Button
        playButton = new JButton("Play");
        playButton.addActionListener(new ButtonListener());
   
    	
        // CheckBox to enable/disable stops
        checkBox = new JCheckBox();
        includeStopsLabel = new JLabel("Include Stops");
        
        
       
    	
        // ComboBox to pick animation time
        // Array of strings with times
        String[] times = {"Animation Time", "15", "30", "60", "90"};
        animationTime = new JComboBox(times);
        animationTime.addActionListener(new comboBoxListener());
    	
        // Add all to top panel
        add(animationTime);
        add(checkBox);
        add(includeStopsLabel);
        add(playButton);
        
        // Set up mapViewer
        mapViewer = new JMapViewer();
        
        // Add listeners for GUI components
        
        
        // Set the map center and zoom level
        
		
	}
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			}
		}
	class comboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

    	// Read file and call stop detection
    	TripPoint.readFile("Triplog.csv");
    	trip = TripPoint.getTrip();
    	movingTrip = TripPoint.getMovingTrip();
    	
    	
        // Set up mapViewer
        
        
        // Add listeners for GUI components
        

        // Set the map center and zoom level
        
        
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    // Animate the trip based on selections from the GUI components
    
}