import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Coordinate; 
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource;


public class Driver extends JFrame {
	
	private static JFrame frame; 
    private static JPanel controlPanel;
    private static JButton playButton;
    private static JComboBox animationTime;
    private static JCheckBox checkBox;
    private static JMapViewer mapViewer;
    private ArrayList<TripPoint> trip;
    private ArrayList<TripPoint> movingTrip;
    private Timer timer;
    public Integer selectedSeconds = 60;
    public ArrayList<Coordinate> coords;
    public static boolean includeStops = false;
    public static int timerCounter = 0;
    private ImageIcon raccoon = new ImageIcon("raccoon.png");
    public ArrayList<Coordinate> addingPolygons;

    
    Driver(){
    	trip = TripPoint.getTrip();
    	movingTrip = TripPoint.getMovingTrip();
    	
    	frame = new JFrame("Project 5 - Sahith Gondi");
    	setVisible(true);
    	setSize(600,600);
    	setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    	
    	controlPanel = new JPanel();
    	
    	mapViewer = new JMapViewer();
    	mapViewer.setTileSource(new BingAerialTileSource());
    	mapViewer.setDisplayPosition(new Coordinate(trip.get(100).getLat(), trip.get(100).getLon()), 5);
    	add(mapViewer);
    		
    	playButton = new JButton("Play");
    	playButton.addActionListener(new ButtonListener());
    	
    	checkBox = new JCheckBox();
    	JLabel includeStopsLabel = new JLabel("Include Stops");
    	
    	Integer[] times = {15, 30, 60, 90};
    	animationTime = new JComboBox(times);
    	animationTime.addActionListener(new comboBoxListener());
    	
    	controlPanel.add(animationTime);
    	controlPanel.add(checkBox);
    	controlPanel.add(includeStopsLabel);
    	controlPanel.add(playButton);
    	add(controlPanel, BorderLayout.NORTH);
    	
    }
     class checkBoxListener implements ActionListener{
    	 public void actionPerformed(ActionEvent e) {
    		 includeStops = checkBox.isSelected();
    	 }
     }
    	
     class TimerListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    		 if (timerCounter < coords.size()) {
    			 if (timerCounter == 0) {
    				 mapViewer.addMapMarker(new IconMarker(coords.get(timerCounter), raccoon.getImage()));
    			 } else {
    				 mapViewer.removeAllMapMarkers();
    				 mapViewer.addMapMarker(new IconMarker(coords.get(timerCounter), raccoon.getImage()));
    				 
    				 Coordinate current = coords.get(timerCounter);
    				 Coordinate previous = coords.get(timerCounter - 1);
    				 Coordinate next = coords.get(timerCounter + 1);
    				 
    				 MapPolygonImpl line = new MapPolygonImpl(new Coordinate[] {previous, current, current});
    				 
    				 add(line);
    			 } timerCounter++;
    			 
    		 } else {
    			 timer.stop();
    		 }
    	 }
     }
    	
     public void add(MapPolygonImpl poly) {
    	 this.mapViewer.addMapPolygon(poly);
     }
     
     class ButtonListener implements ActionListener{
    	 public void actionPerformed(ActionEvent e) {
    		 if (timer != null) {
    			 timer.stop(); 
    		 }
    		 
    		 includeStops = checkBox.isSelected();
    		 
    		 selectedSeconds = (Integer)animationTime.getSelectedItem();
    		 
    		 mapViewer.removeAllMapMarkers();
    		 mapViewer.removeAllMapPolygons();
    		 
    		 coords = new ArrayList<Coordinate>();
    		 
    		 if (includeStops ) {
    			 for (TripPoint t : trip) {
    				 coords.add(new Coordinate(t.getLat(), t.getLon()));
    			 }
    		 }
    		 timerCounter = 0;
    		 timer = new Timer((selectedSeconds * 1000) / coords.size(), new TimerListener());
    		 timer.start();
    	 }

		

     }
     
     class comboBoxListener implements ActionListener{
    	 public void actionPerformed(ActionEvent e) {
    		 Integer seconds = (Integer) animationTime.getSelectedItem();
    		 
    		 switch(seconds) {
    		 case 15:
    			 selectedSeconds = 15;
    		 case 30:
    			 selectedSeconds = 30;
    		 case 60: 
    			 selectedSeconds = 60;
    		 case 90:
    			 selectedSeconds = 90;
    		 default: 
    			 selectedSeconds = 15;
    		 } 
    	 }
     }
     
     public static void main(String[]args) throws FileNotFoundException, IOException {
    	 
    	 TripPoint.readFile("triplog.csv");
    	 TripPoint.h2StopDetection();
    	 
    	 Driver test = new Driver();
     }
     
    	
    }
 