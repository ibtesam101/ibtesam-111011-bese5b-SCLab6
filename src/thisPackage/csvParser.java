package thisPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;  
import org.hibernate.service.ServiceRegistry;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.cfg.annotations.*;

import org.hibernate.Session;


import org.hibernate.Transaction;


public class csvParser {
	
	Session currSession = null;
	
	public csvParser(){
		SessionFactory factory= new Configuration().configure("/thisPackage/hibernate.cfg.xml").buildSessionFactory();
		
		currSession = factory.openSession();
	}
	
	public void endSession(){
		currSession.close();
	}
	
	public void printCities(){

		Query query=currSession.createQuery("from City");

		List<CityAnnotations> list=((org.hibernate.query.Query) query).list();

		Iterator<CityAnnotations> itr=list.iterator();

		while(itr.hasNext()){

			CityAnnotations city=itr.next();

			System.out.println("City Name: "+city.getCity());

			}

		}
	
	public void save(ArrayList<CityAnnotations> cityList){

		
		Transaction t=currSession.beginTransaction();

		for(int i=0;i<cityList.size();i++){

			currSession.persist(cityList.get(i));

		}

			t.commit();

		}
	
	public static void main(String[] args){
		ArrayList<CityAnnotations> myCityList = new ArrayList<CityAnnotations>();
		
		csvParser myC = new csvParser();
		String csvFile = "GeoLiteCity-Location.csv";
		String line = "";
		String cvsSplitBy = ",";
	
		int count = 0;
		
		BufferedReader br = null;
		
		String check = "wut";
		
		try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null  && count <1000) {

            	if(count>2){

	                // use comma as separator
	                String[] city = line.split(cvsSplitBy);
	
	                CityAnnotations c = new CityAnnotations();
	                
	                System.out.println("City [id= " + city[0] + " , country="+city[1]+ "]"+
	                		"[region= " + city[2] + " , city="+city[3]+ "]"+
	                		"[postalcode= " + city[4] + " , latitutude="+city[5]+ "]"+
	                		"[longitude= " + city[6]);
	                c.setlocId(Integer.parseInt(city[0]));
	                c.setCountry(city[1]);
	                c.setRegion(city[2]);
	                c.setCity(city[3]);
	                try{
	                	c.setPostalCode(Integer.parseInt(city[4]));
	                }
	                catch(NumberFormatException e){
	                	
	                }
	                try{
	                	c.setLatitude(Float.parseFloat(city[5]));
	                }
	                catch(NumberFormatException e){
	                	
	                }
	                try{
	                	c.setLongitude(Float.parseFloat(city[6]));
	                }
	                catch(NumberFormatException e){
	                	
	                }
	                myCityList.add(c);

            	}
            	
            	count++;
                
            }
            
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		myC.save(myCityList);
		myC.printCities();
	}
}
