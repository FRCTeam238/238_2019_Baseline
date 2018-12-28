package org.usfirst.frc.team238.core;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AutonomousPlay {
	
	/** Read the Autonomous Plays file and return a HashMap. 
	 * 
	 * This function will read the provided JSON file (play238.txt).
	 * 
	 * When reading the contents of each play will be read into 
	 * the HashMap.
	 * 
	 * Play names are currently:
	 *   L_LL, L_LR, L_RR, L_RL
	 *   C_LL, C_LR, C_RR, C_RL
	 *   R_LL, R_LR, R_RR, R_RL
	 *   
	 * The leading L, C, or R represents the starting field position. The
	 * remaining LL, LR, RR, and RL are the field disposition.
	 * 
	 * Generating the keys to access this hash should take the selected playgroup
	 * ("primary" or "secondary") concatenated with the field position ("L", "C", or "R")
	 * an underscore and the field disposition (lighting positions read from the dash 
	 * board.
	 * 
	 *    [playgroup][field position]_[field disposition]
	 * 
	 * @param filepath The file to load.
	 * @return A HashMap of the contents of the file.
	 * 
	 */
	public static HashMap<String, ArrayList<String> > readJson(String filepath)
	{
		HashMap<String, ArrayList<String> > autonomousPlays = new HashMap<String, ArrayList<String> >();
		
		try 
		{
			//System.out.println("AutonomousPlay.readJson(): BEGIN");
			JSONParser parser = new JSONParser();
			
			System.out.println("AutonomousPlay: Create FileReader:filepath=" + filepath);
			FileReader playsFile = new FileReader(filepath);
			
			if (playsFile == null)
			{
				System.out.println("AutonomousPlay: playsFile is null");
			}
	
			System.out.println("AutonomousPlay: JSON parse");
			Object obj = parser.parse(playsFile);
						
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject == null)
			{
				System.out.println("AutonomousPlay: jsonObject is null");
			}
			System.out.println("AutonomousPlay: jsonObject=" + jsonObject);
			
			System.out.println("AutonomousPlay: get(AutonomousPlays)");
			JSONArray targetPlays = (JSONArray) jsonObject.get("AutonomousPlays");
			if (targetPlays == null)
			{
				System.out.println("AutonomousPlay: targetPlays is null");
			}
			System.out.println("AutonomousPlay: targetPlays=" + targetPlays);
			System.out.println(String.format("AutonomousPlay: targetPlays.size=%d", targetPlays.size()));
			Iterator<JSONObject> targetPlaysIterator = targetPlays.iterator();
			while (targetPlaysIterator.hasNext())
			{
				System.out.println("AutonomousPlay: get next;");
				JSONObject playGroupJson = targetPlaysIterator.next();
				
				System.out.println("AutonomousPlay: next=" + playGroupJson.toString());
				String playGroupName = (String)playGroupJson.get("Name");
				
				//System.out.println(playGroupName);
				
				Set<JSONObject> playsKeysJson = (Set<JSONObject>)playGroupJson.keySet();
				
				Iterator<JSONObject> playsIterator = playsKeysJson.iterator();
				
				while (playsIterator.hasNext())
				{
					Object playName = playsIterator.next();
					
					if (!playName.equals("Name"))
					{						
						JSONArray modes = (JSONArray)playGroupJson.get(playName);
						
						Iterator<String> modesIterator = modes.iterator();
						
						//System.out.println("  " + playName);
						
						ArrayList<String> modesAsList = new ArrayList<String>(modes.size());
						while (modesIterator.hasNext())
						{
							String mode = modesIterator.next();
							modesAsList.add(mode);
						}
						
						String combinedKeyName = playGroupName + "_" + playName;
						autonomousPlays.put(combinedKeyName, modesAsList);						
					}
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		
		return autonomousPlays;
	}
    
	public static void dump(HashMap<String, ArrayList<String> > plays)
	{
	    System.out.println("AutonomousPlay:dump");
		Iterator<String> playsIterator = plays.keySet().iterator();
		while (playsIterator.hasNext())
		{
			String playKeyname = playsIterator.next();
			System.out.println(playKeyname);
			
			ArrayList<String> modes = plays.get(playKeyname);
			Iterator<String> modesIterator = modes.iterator();
			
			while (modesIterator.hasNext())
			{
				String mode = modesIterator.next();
				System.out.println("    " + mode);
			}
		}
	}
}
