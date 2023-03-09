package capProject;

public class User {
	
	private String username;
	private String password;
	private String address;
	private String email;
	private String city;
	private String province;
	private String country;
	private String postal;
	
public User() {
	
}

public String getUsername() {
	return username;
}
public String getPassword() {
	return password;
}
public String getAddress() {
	return address;
}
public String getEmail() {
	return email;
}
public String getCity() {
	return city;
}
public String getProvince() {
	return province;
}
public String getCountry() {
	return country;
}
public String getPostal() {
	return postal;
}
public void setUsername (String username ) {
	this.username = username ;
}
public void setPassword (String password ) {
	this.password = password ;
}
public void setEmail (String email) {
	this.email = email ;
}
public void setCity (String city) {
	this.city = city ;
}
public void setProvince (String province) {
	this.province = province;
}
public void setCountry (String country) {
	this.country = country;
}
public void setPostal (String postal) {
	this.postal = postal;
}
}