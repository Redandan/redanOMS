package redan.oms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Customer {

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)   
	private Long id;
	
	private String name;

	private String phone;

	private String address;
//	@ManyToMany
//	@OneToMany(targetEntity=Receive.class, fetch=FetchType.EAGER)
//	@JoinColumn
//	private Set<Receive> receives;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public Set<Receive> getReceives() {
//		return receives;
//	}
//	public void setReceives(Set<Receive> receives) {
//		this.receives = receives;
//	}
//	@Override
//	public String toString() {
//		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", receives="
//				+ receives + "]";
//	}	
	
	
}
