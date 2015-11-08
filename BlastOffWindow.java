import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


/**
 * Displays a timer counting down from 10 every second.  
 * When the time reaches 0,
 * an explosion occurs.  
 * @author Jeremy Timothy Brown
 *
 */
public class BlastOffWindow extends JFrame {
	

	javax.swing.Timer timer;
	BufferedImage img;
	ImageIcon icon;
	int i = 10;
	File f = new File("boom.wav");
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;
	
	/**
	 * Creates the window by setting the size, title, adding the component that holds the number
	 * of seconds left, and creates and starts a timer that goes off every second.  
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public BlastOffWindow() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		
		
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Countdown to Blastoff");
		setLayout(new BorderLayout());
		JLabel l = new JLabel();
		l.setFont(new Font("Courier New", Font.BOLD, 300));
		add(l, BorderLayout.EAST);
		setResizable(false);
		setVisible(true);
		
		try {
		    img = ImageIO.read(new File("explosion.jpg"));
		} catch (IOException e) {
		}
		icon = new ImageIcon(img.getScaledInstance(300, 300, 300));
		
		stream = AudioSystem.getAudioInputStream(f);
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
		
		class MyListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if (i==10){
					l.setText("10");
				}
				else if(i==9){
					l.setText("9");
				}
				else if(i==8){
					l.setText("8");
				}
				else if(i==7){
					l.setText("7");
				}
				else if(i==6){
					l.setText("6");
				}
				else if(i==5){
					l.setText("5");
				}
				else if(i==4){
					l.setText("4");
				}
				else if(i==3){
					l.setText("3");
				}
				else if(i==2){
					l.setText("2");
				}
				else if(i==1){
					l.setText("1");
				}
				else{
					clip.start();
					l.setText(null);
					l.setIcon(icon);
					timer.stop();
				}
				i--;
			}
		};
		
		MyListener listener = new MyListener();
		timer = new Timer(1000, listener);
		timer.start();
	}
	
	/**
	 * Creates an instance of the window. 
	 * @param args Command-line arguments (ignored).
	 * @throws LineUnavailableException 
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 */
	public static void main(String [] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		BlastOffWindow window = new BlastOffWindow();
	}

}
