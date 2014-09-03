package GameIO;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ReadScoresData {
	
	public ArrayList<String> getScores(){
		ArrayList<String> scores = new ArrayList<String>();
		
		File input2 = new File("gameData\\scores.txt");
		FileInputStream fis2 = null;
		BufferedInputStream bis2 = null;
		DataInputStream dis2 = null;
		
		
		
		try {
			
			fis2 = new FileInputStream(input2);
			bis2 = new BufferedInputStream(fis2);
			dis2 = new DataInputStream(bis2);
			

			while (dis2.available() != 0) 
				scores.add(dis2.readLine());

			fis2.close();
			bis2.close();
			dis2.close();
			
		} catch (FileNotFoundException e) {return null;}
		  catch (IOException e) { return null;}
		
		
		
		
		ArrayList<Integer> highscores = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		
		for(String score : scores){
			if(!score.equals("")){
				String[] temp = score.split(" ---- ");
				highscores.add(Integer.parseInt(temp[0]));
				names.add(temp[1]);
			}
		}
		
		for(int i = 1; i < highscores.size(); i++){
			
			int temp = highscores.get(i);
			String temp2 = names.get(i);
			
			int j;
			for(j = i - 1; j >= 0 && temp > highscores.get(j); j--){
				highscores.set(j + 1, highscores.get(j));
				names.set(j + 1, names.get(j));
			}
			
			highscores.set(j + 1, temp);
			names.set(j + 1, temp2);
		}
		
		scores.clear();
		
		for(int i = 0; i < highscores.size(); i++)
			scores.add(highscores.get(i) + " ---- " + names.get(i));
		
		highscores = null;
		names = null;
		
		return scores;
	}
	
}
