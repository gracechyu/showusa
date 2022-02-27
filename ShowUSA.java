
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;

public class ShowUSA extends JFrame 
{
	/**
	 *  Creates a ShowUSA object, adding a JPanel
	 *  to the JFrame.
	 */
	 
	public ShowUSA() 
	{
		
		super("United States of America");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GetAndDrawCities cities = new GetAndDrawCities();
		getContentPane().add(cities);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new ShowUSA();
	}
}


class GetAndDrawCities extends JPanel 
{
	private static final int PREF_W = 1000;
	private static final int PREF_H = 600;
	
	public GetAndDrawCities()
	{
		setBackground(Color.white);
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(PREF_W, PREF_H);
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		addCities(g);
		addCapitals(g);
	}
	
	/**
	 *  Here, you should open up the cities text file, and translate the
	 *  numbers you find to coordinate values to be plotted.  The
	 *  dots should be gray and of diameter 4.
	 */
	public void addCities(Graphics g)
	{
		Scanner infile = OpenFile.openToRead("cities.txt");
		if(infile==null)
			System.out.println("Error");
		String temp = null;
		int index,index2;
		String longitudal,latitudal;
		double longitude,latitude;
		//int longitude2,latitude2;
		int lat,lon;
		while(infile.hasNext())
		{
			temp = infile.nextLine();
			index=temp.indexOf(',');
			index=temp.indexOf(',',index+1);
			index=temp.indexOf(',',index+1);
			index2=temp.indexOf(',',index+1);
			longitude=Double.parseDouble(temp.substring(index+1,index2-1));
			latitude=Double.parseDouble(temp.substring(index2+1));
			lat=(int)((125-latitude)*1000.0/60.0);
			lon=(int)((50-longitude)*600/25);
			g.setColor(Color.gray);
			g.fillOval(lat, lon, 4, 4);
		}
		infile.close();
	//	int diameter = 4;
	//	g.fillOval(400-diameter/2,200-diameter/2,diameter,diameter);
		
	}
	
	/**
	 *  Open up the capitals text file, and, for each city you find in
	 *  this file, compare to the cities in cities.txt.  If you find a
	 *  match, print a red dot with diameter 12.
	 */
	/**
	 * @param g
	 */
	public void addCapitals(Graphics g)
	{
		Scanner infile3 = OpenFile.openToRead("cities.txt");
		
		String temp = null;
		int index,index2,index3;
		String state2=null,city2=null;
		int lat,lon;
		double longitude,latitude;
		int xindex,xindex2;
		while(infile3.hasNext())
		{
			temp = infile3.nextLine();//Read each line in cities.txt
			index=temp.indexOf(',',0);//Find the 1st, 2nd and 3rd indexes for ,
			index2=temp.indexOf(',',index+1);
			index3=temp.indexOf(',',index2+1);

			//longitudal=temp.substring(index,index2);
			state2=temp.substring(index+1,index2);//obtain the state substring
			city2=temp.substring(index2+1,index3);//obtain teh city substring
			//trim state for capital
			if(checkForCapital(city2, state2))//check whether or not this city is capital
			{
				
				/*if((city2.equals(city))&&(state2.equals(state))){*/
					xindex=temp.indexOf(',',index3+1);
					index=temp.indexOf(',',index+1);
					index=temp.indexOf(',',index+1);
					index2=temp.indexOf(',',index+1);
					longitude=Double.parseDouble(temp.substring(index+1,index2-1));
					latitude=Double.parseDouble(temp.substring(index2+1));
					lat=(int)((125-latitude)*1000.0/60.0);
					lon=(int)((50-longitude)*600/25);
					g.setColor(Color.red);
					g.fillOval(lat, lon, 12, 12);
				/*	}*/
				}
				
			}
		
	}
	
	public boolean checkForCapital(String city,String state)
	{
		Scanner infile2 = OpenFile.openToRead("capitals.txt");
	
		String temp = null;
		int index,index2=0;
		int lat,lon;
		String Ccity=null, Cstate=null;
		
		while(infile2.hasNext())
		{
			temp = infile2.nextLine();//Read each line in cities.txt
			index=temp.indexOf(',',0);
			index2=temp.indexOf(',',index2+1);
			//longitudal=temp.substring(index,index2);
			Cstate=temp.substring(index+1);//obtain the state substring
			Ccity=temp.substring(0,index);//obtain teh city substring
			Cstate=Cstate.trim();
			if((Ccity.equals(city))&&(Cstate.equals(state)))
			{
				infile2.close();
				return true;
			}
				
		//open the captial.txt file
		// Use the while loop to read each line in captial.txt
		//in the while loop, for each line obtain the City and State
		//Compare City and city && State and state
		//if they are both true, reurn true and close file
		//If not found in the while loop, close file and return false
	}
	infile2.close();
	return false;
}
}
