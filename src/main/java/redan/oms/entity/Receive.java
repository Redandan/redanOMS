package redan.oms.entity;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Receive {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)   	
	private Long id;
	
	private Long origId;
	
	private String logistics;
	
	private String specification1;
	
	private String specification2;	
	
	private Integer receAmount;
	
	private Date orderDate;	
	
	private String activateName;
	
	private String trackingNumber; 
	
	@ManyToMany
	@JoinColumn(nullable = false)
	private Set<Product> prods;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
	private Customer buyer;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	
	public String getSpecification1() {
		return specification1;
	}
	public void setSpecification1(String specification1) {
		this.specification1 = specification1;
	}
	public String getSpecification2() {
		return specification2;
	}
	public void setSpecification2(String specification2) {
		this.specification2 = specification2;
	}	
	
	public Long getOrigId() {
		return origId;
	}
	public void setOrigId(Long origId) {
		this.origId = origId;
	}
	public Integer getReceAmount() {
		return receAmount;
	}
	public void setReceAmount(Integer receAmount) {
		this.receAmount = receAmount;
	}
	public Set<Product> getProds() {
		return prods;
	}
	public void setProds(Set<Product> prods) {
		this.prods = prods;
	}
	public Customer getBuyer() {
		return buyer;
	}
	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	public String getActivateName() {
		return activateName;
	}
	public void setActivateName(String activateName) {
		this.activateName = activateName;
	}
	
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	@Override
	public String toString() {
		return "Receive [id=" + id + ", origId=" + origId + ", logistics=" + logistics + ", specification1="
				+ specification1 + ", specification2=" + specification2 + ", receAmount=" + receAmount + ", prods="
				+ prods + ", buyer=" + buyer + "]";
	}

	
	
	
}
