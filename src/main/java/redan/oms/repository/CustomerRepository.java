package redan.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import redan.oms.entity.Customer;
import redan.oms.entity.Product;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByName(String name);
	List<Customer> findByPhone(String phone);
}
