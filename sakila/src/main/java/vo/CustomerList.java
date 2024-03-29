package vo;

public class CustomerList {
	private int costomerListId;
	private String name;
	private String address;
	private String zipCode;
	private long phone;
	private String city;
	private String country;
	private String notes;
	private String sid;
	
	@Override
	public String toString() {
		return "CustomerList [costomerListId=" + costomerListId + ", name=" + name + ", address=" + address + ", zipCode="
				+ zipCode + ", phone=" + phone + ", city=" + city + ", country=" + country + ", notes=" + notes + ", sid=" + sid +"]";
	}
	public int getCostomerListId() {
		return costomerListId;
	}
	public void setCostomerListId(int costomerListId) {
		this.costomerListId = costomerListId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
}
