import java.util.Scanner;
import java.io.*; 
import java.io.IOException; 
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;



class ColourBlind implements ItemListener, ActionListener{

	static JFrame f;
	static JPanel p;
	static Choice c;
	static Button b;

	public static void main(String args[])throws Exception{ 

		ColourBlind thisClass = new ColourBlind();

		f = new JFrame("Colour Blindness");
		p = new JPanel();
		c = new Choice();
		b = new Button("Next");
		b.setFont(new Font("Lucida",Font.PLAIN,24));
	
		Label h = new Label("Colour Blindness");		
		h.setFont(new Font("Lucida",Font.BOLD,50));
		
		c.add("Select the type of Blindness\t\t\t");
		c.add("Red");
		c.add("Blue");
		c.add("Green");
		c.setFont(new Font("Lucida",Font.PLAIN,24));

		c.addItemListener(thisClass);
		b.addActionListener(thisClass);

		p.add(h);
		p.add(c);
		p.add(b);
		
		f.add(p); 
		f.show(); 
		f.setSize(700, 300);
	}

	public void itemStateChanged(ItemEvent e){ 
	} 

	public void actionPerformed(ActionEvent e){
		try{
			Main callMain = new Main();
			if(c.getSelectedIndex()!=0){
				callMain.start1(c.getSelectedIndex());
			}
		}
		catch(Exception E){
			System.out.println("Warning!!\n"+E);
		}
	}
}


class Main implements ActionListener{

	BufferedImage image, redStripe, blueStripe, greenStripe;
	File d;

	static JFrame f1;
	static JPanel p1;
	static JLabel sample;
	static Choice c1;
	static Button b1, b2;
	static TextField text;
	static int y;

	Main()throws Exception{

		image = null;
		redStripe = null;
		blueStripe = null;
		greenStripe = null;
		d= null;
		d=new File("Inp1.jpg");
		image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		image = ImageIO.read(d);
		d=new File("red.jpg");
		redStripe = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		redStripe = ImageIO.read(d);
		d=new File("blue.jpg");
		blueStripe = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		blueStripe = ImageIO.read(d);
		d=new File("green.jpg");
		greenStripe = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		greenStripe = ImageIO.read(d);
	}


	public static void start1(int x)throws Exception{ 
		y = x;

		Main thisClass = new Main();

		f1 = new JFrame();
		Label h1 = new Label("Colour Blindness");

		if(x==1)
		{
			f1 = new JFrame("Protanomaly");
			h1 = new Label("Protanomaly");
		}
		
		else if(x==2){
			f1 = new JFrame("Tritanomaly");
			h1 = new Label("Tritanomaly");
		}
		else if(x==3){
			f1 = new JFrame("Deuteranomaly");
			h1 = new Label("Deuteranomaly");
		}

		p1 = new JPanel();
		b1 = new Button("OK");
		b2 = new Button("Web Camera");
		b1.setFont(new Font("Lucida",Font.PLAIN,24));
		b2.setFont(new Font("Lucida",Font.PLAIN,24));
		sample = new JLabel();
		text = new TextField("Inp1",15);
		text.setFont(new Font("Lucida",Font.PLAIN,24));
		h1.setFont(new Font("Lucida",Font.BOLD,50));
		
		sample.setIcon(new ImageIcon("Inp1.jpg"));
		//sample.setPreferredSize(new Dimension(50, 75)); 

		p1.add(h1);
		p1.add(text);
		p1.add(b1);
		p1.add(b2);
		p1.add(sample);
		
		b1.addActionListener(thisClass);
		b2.addActionListener(thisClass);
		f1.add(p1);
		f1.show();
		f1.setSize(2000,1500); 	
	}


	public void actionPerformed(ActionEvent e) 
	{
		try{
			if(e.getSource()==b1){
				d=new File(text.getText()+".jpg");
				image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
				image = ImageIO.read(d);
			
				switch (y)
				{
					case 1: red(image);
							break;
					case 2: blue(image);
							break;
					case 3: green(image);
							break;
				}
				sample.setIcon(new ImageIcon(text.getText()+y+".jpg"));
			}
			else if(e.getSource()==b2){
				final Webcam webcam = Webcam.getDefault();

		        final WebcamPanel panel = new WebcamPanel(webcam);
		        //panel.setFPSDisplayed(true);
		        panel.setFillArea(true);
		        JButton button = new JButton("Capture");
		        button.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                if(e.getActionCommand().equals("Capture")){
		                    System.out.println("done::");
		                    BufferedImage image = webcam.getImage();
		                    try {
		                        ImageIO.write(image, "jpg", new File("sample.jpg"));
		                        switch (y){
									case 1: red(image);
											break;
									case 2: blue(image);
											break;	
									case 3: green(image);
											break;
								}
		                    }
		                    catch (IOException e1) {
		                        e1.printStackTrace();
		                    }
		                }
		            }
		        });
		        JFrame window = new JFrame("Frame");
		        window.add(panel);
		        panel.add(button);
		        window.pack();
		        window.setVisible(true);
					}
		}
		catch(Exception E){
			System.out.println(E);
		}
	}


	void red(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	    int height = image.getHeight();
	    int width1 = redStripe.getWidth();
	   	int height1 = redStripe.getHeight();
	    System.out.println("the "+height1+width1);

		for (int y = 0; y < height; y++) 
        	{ 
            for (int x = 0; x < width; x++) 
           		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int r = (p>>16)&0xff; 
				int g = (p>>8)&0xff; 
				int b = (p)&0xff;
				if((0.8*r > g ) && (0.8*r > b)){
					p = redStripe.getRGB(x%width1,y%height1);
		  		}
				image.setRGB(x, y, p); 
            } 
		}
		File outputImage = new File(text.getText()+"1.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Red Image");
		sample.setIcon(new ImageIcon(text.getText()+y+".jpg"));
	}

	void blue(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	    int height = image.getHeight();
	    int width1 = blueStripe.getWidth();
	   	int height1 = blueStripe.getHeight();
	    System.out.println("the "+height1+width1);

		for (int y = 0; y < height; y++) 
        	{ 
            for (int x = 0; x < width; x++) 
           		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int r = (p>>16)&0xff; 
				int g = (p>>8)&0xff; 
				int b = (p)&0xff;
				if((0.8*b > g ) && (0.8*b > r)){
					p = blueStripe.getRGB(x%width1,y%height1);
		  		}
				image.setRGB(x, y, p); 
            } 
		}
		File outputImage = new File(text.getText()+"2.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Blue Image");
		sample.setIcon(new ImageIcon(text.getText()+y+".jpg"));
	}

	void green(BufferedImage image)throws IOException{
		
		int width = image.getWidth(); 
	    int height = image.getHeight();
	    int width1 = blueStripe.getWidth();
	   	int height1 = blueStripe.getHeight();
	    System.out.println("the "+height1+width1);

		for (int y = 0; y < height; y++) 
        	{ 
            for (int x = 0; x < width; x++) 
           		{ 
				int p = image.getRGB(x,y); 
		  
				int a = (p>>24)&0xff; 
				int r = (p>>16)&0xff; 
				int g = (p>>8)&0xff; 
				int b = (p)&0xff;
				if((0.8*g > b ) && (0.8*g > r)){
					p = blueStripe.getRGB(x%width1,y%height1);
		  		}
				image.setRGB(x, y, p); 
            } 
		}
		File outputImage = new File(text.getText()+"3.jpg");
		ImageIO.write(image, "jpg", outputImage);
		System.out.println("Created Green Image");
		sample.setIcon(new ImageIcon(text.getText()+y+".jpg"));
	}
}