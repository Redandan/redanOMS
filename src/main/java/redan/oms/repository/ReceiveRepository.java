package redan.oms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import redan.oms.entity.Customer;
import redan.oms.entity.Product;
import redan.oms.entity.Receive;

@Repository
public interface ReceiveRepository extends JpaRepository<Receive, Long> {

	List<Receive> findByOrigId(Long origId);
	List<Receive> findByBuyer(Customer buyer);
	List<Receive> findByProds(Product prods);
	List<Receive> findByOrderDateBetween(Date starDate,Date endDate);
}
