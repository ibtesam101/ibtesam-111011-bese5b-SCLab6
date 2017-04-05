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

public class csvParser {
	
	public static void main(String[] args){
		//creating configuration object  
		/*Configuration cfg=new Configuration();  
		cfg.configure("/thisPackage/hibernate.cfg.xml");//populates the data of the configuration file  

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()). build();
		*/
		
		//SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);
		//creating session object  
		
		SessionFactory factory= new Configuration().configure("/thisPackage/hibernate.cfg.xml").buildSessionFactory();
		String csvFile = "GeoLiteCity-Location.csv";
		String line = "";
		String cvsSplitBy = ",";
	
		int count = 0;
		
		BufferedReader br = null;
		
		String check = "wut";
		
		try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

            	if(count>2){
            		
            		Session session=factory.openSession();  
        			//creating transaction object  
            		Transaction t=session.beginTransaction();  

        		
	                // use comma as separator
	                String[] city = line.split(cvsSplitBy);
	
	                City c = new City();
	                
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

	                session.persist(c);
	                t.commit();

	        		session.close();
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
		
	}
}
