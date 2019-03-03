package redan.oms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import redan.oms.entity.Customer;
import redan.oms.entity.Product;
import redan.oms.entity.Receive;
import redan.oms.repository.CustomerRepository;
import redan.oms.repository.ProductRepository;
import redan.oms.repository.ReceiveRepository;

public class FileUploadDao extends BaseDAO {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ReceiveRepository receiveRepository;

	public boolean saveFileDataInDB(List<InputVO> inputList) {
		try {
			for (InputVO vo : inputList) {

				// product
				if (!(productRepository.findByName(vo.getProdName()).size() > 0)) {
					Product product = new Product();
					product.setName(vo.getProdName());
					productRepository.save(product);
				}

			}

			for (InputVO vo : inputList) {

				// customer
				if (!(customerRepository.findByName(vo.getCustomerName()).size() > 0)) {
					Customer customer = new Customer();
					customer.setName(vo.getCustomerName());
					customer.setAddress(vo.getCustomerAddress());
					customer.setPhone(vo.getCustomerPhone());
					customerRepository.save(customer);
				}else {
					
				}


			}

			for (InputVO vo : inputList) {				

				// receive
				if (!(receiveRepository.findByOrigId(Long.parseLong(vo.getReceiveId())).size() > 0)) {
					Receive receive = new Receive();
					receive.setOrigId(Long.parseLong(vo.getReceiveId()));
					receive.setLogistics(vo.getLogistics());
					receive.setReceAmount(Integer.parseInt(vo.getReceAmount()));
					receive.setSpecification1(vo.getReceSpic1());
					receive.setSpecification2(vo.getReceSpic2());
					receive.setTrackingNumber(vo.getTrackingNumber());
					
					String ordeDateString = vo.getOrderDate();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date OrderDate = sdf.parse(ordeDateString);
					receive.setOrderDate(OrderDate);
					
					
//					// if buyer not exists
					if (!(customerRepository.findByName(vo.getCustomerName()).size() > 0)) {
						Customer customer = new Customer();
						customer.setName(vo.getCustomerName());
						customer.setAddress(vo.getCustomerAddress());
						customer.setPhone(vo.getCustomerPhone());
						customerRepository.save(customer);
						receive.setBuyer(customer);
					} else {
						receive.setBuyer(customerRepository.findByName(vo.getCustomerName()).get(0));
					}
					// if prod not exists
					if (!(productRepository.findByName(vo.getProdName()).size() > 0)) {						
						Product product = new Product();
						product.setName(vo.getProdName());
						productRepository.save(product);
						HashSet<Product> prodSet = new HashSet<Product>();
						prodSet.addAll(productRepository.findByName(vo.getProdName()));
						receive.setProds(prodSet);
					}else {						
						HashSet<Product> prodSet = new HashSet<Product>();
						prodSet.addAll(productRepository.findByName(vo.getProdName()));
						receive.setProds(prodSet);
					}

					HashSet<Product> prodSet = new HashSet<Product>();
					prodSet.addAll(productRepository.findByName(vo.getProdName()));
					 receive.setProds(prodSet);
//					System.out.println(vo);
//					System.out.println(productRepository.findByName(vo.getProdName()).toString());
					receiveRepository.save(receive);
				} else {
					System.out.println("prod2");
					// update rece
					Receive receive = receiveRepository.findByOrigId(Long.parseLong(vo.getReceiveId())).get(0);
					HashSet<Product> prodSet = new HashSet<Product>();
					
					prodSet.addAll(receive.getProds());
					prodSet.addAll(productRepository.findByName(vo.getProdName()));		
					System.out.println(prodSet);
					receive.setProds(prodSet);
					receiveRepository.save(receive);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
