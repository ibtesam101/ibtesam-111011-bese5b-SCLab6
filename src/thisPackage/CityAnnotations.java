package thisPackage;

import javax.persistence.*;

@Entity
@Table(name="City")
public class CityAnnotations {
	@Id @GeneratedValue
	@Column(name = "locId")
	int locId;
	
	@Column(name="country")
	String country;
	
	@Column(name="region")
	String region;
	
	@Column(name="city")
	String city;
	
	@Column(name="postalcode")
	int postalCode;
	
	@Column(name="latitude")
	float latitude;
	
	@Column(name="longitude")
	float longitude;
	

	
	public void setlocId(int id){
		this.locId = id;
	}
	
	public int getlocId(){
		return this.locId;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public String getCountry(){
		return this.country;
	}
	
	public void setRegion(String region){
		this.region = region;
	}
	
	public String getRegion(){
		return this.region;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public void setPostalCode(int pc){
		this.postalCode = pc;
	}
	
	public int getPostalCode(){
		return this.postalCode;
	}
	
	public void setLongitude(float lg){
		this.longitude = lg;
	}
	
	public float getLongitude(){
		return this.longitude;
	}
	
	public void setLatitude(float lt){
		this.latitude = lt;
	}
	
	public float getLatitude(){
		return this.latitude;
	}
}
