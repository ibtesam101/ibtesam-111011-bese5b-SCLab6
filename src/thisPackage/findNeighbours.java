package thisPackage;

import java.util.ArrayList;


public class findNeighbours {
	ArrayList<City> cityList;
	int numOfNeighs;
	
	public findNeighbours(int num, ArrayList<City> cl){
		this.numOfNeighs = num;
		this.cityList = cl;
	}
	
	public ArrayList<City> getNeighbors(City c, float threshold){
		ArrayList<City> neighList = new ArrayList<City>();
		int count = 0;
		greatCircleDistance disF = new greatCircleDistance();
		
		for(int i=0; i<numOfNeighs; i++){
			neighList.add(new City());
			
		}
		
		for(int i=0; i<cityList.size() && count<5; i++){
			float dis = disF.getDistance(c.getLongitude(), cityList.get(i).getLongitude(), 
					c.getLatitude(), cityList.get(i).getLatitude());
			if(dis<=threshold){
				neighList.get(count).setCity(cityList.get(i).getCity());
			}
		}
		
		return neighList;
	}
	
}
