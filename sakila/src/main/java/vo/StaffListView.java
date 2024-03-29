package vo;

public class StaffListView {
	private int Id;
	private String name;
	private String address;
	private String zipCode;
	private long phone;
	private String city;
	private String country;
	private int sid;
	
	@Override
	public String toString() {
		return "staffListView [Id=" + Id + ", address=" + address + ", zipCode=" + zipCode + ", phone=" + phone + ", city=" + city + ", country=" + country + ", sid=" + sid + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
}
