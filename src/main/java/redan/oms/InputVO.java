package redan.oms;

public class InputVO {
	
	private String receiveId;
	private String customerName;
	private String customerPhone;
	private String prodName;
	private String receSpic1;
	private String receSpic2;
	private String receAmount;
	private String logistics;
	private String customerAddress;
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getReceSpic1() {
		return receSpic1;
	}
	public void setReceSpic1(String receSpic1) {
		this.receSpic1 = receSpic1;
	}
	public String getReceSpic2() {
		return receSpic2;
	}
	public void setReceSpic2(String receSpic2) {
		this.receSpic2 = receSpic2;
	}
	public String getReceAmount() {
		return receAmount;
	}
	public void setReceAmount(String receAmount) {
		this.receAmount = receAmount;
	}
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	@Override
	public String toString() {
		return "InputVO [receiveId=" + receiveId + ", customerName=" + customerName + ", customerPhone=" + customerPhone
				+ ", prodName=" + prodName + ", receSpic1=" + receSpic1 + ", receSpic2=" + receSpic2 + ", receAmount="
				+ receAmount + ", logistics=" + logistics + ", customerAddress=" + customerAddress + "]";
	}
	
	
	
	

}
