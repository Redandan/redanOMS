package redan.oms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import redan.oms.config.Env.RedanConfig;
import redan.oms.entity.Customer;
import redan.oms.entity.Product;
import redan.oms.entity.Receive;
import redan.oms.repository.CustomerRepository;
import redan.oms.repository.ProductRepository;
import redan.oms.repository.ReceiveRepository;

@RestController
public class FileOperationController {

	@Autowired
	RedanConfig redanConfig;
	
	String filePath = redanConfig.filePath;

	@Autowired
	FileUploadService uploadService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ReceiveRepository receiveRepository;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
			uploadService.uploadFileData(filePath + path.getFileName());
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("message",
					"Failure occured during upload '" + file.getOriginalFilename() + "'");
			e.printStackTrace();
		}
		return "done";
	}

	private FileSystemResource downloadFile(List<Receive> outPutReces) {
		final String FILE_NAME = filePath + "outputtemp.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("output sheet");
		int rowNum = 0;

		for (Receive receive : outPutReces) {

			Row row = sheet.createRow(rowNum++);
			Cell id = row.createCell(0);
			id.setCellValue(receive.getOrigId());
			Cell buyerName = row.createCell(1);
			buyerName.setCellValue(receive.getBuyer().getName());
			Cell buyerPhone = row.createCell(2);
			buyerPhone.setCellValue(receive.getBuyer().getPhone());
			Cell buyeraddr = row.createCell(3);
			buyeraddr.setCellValue(receive.getBuyer().getAddress());

			int colnum = 4;
			for (Product prods : receive.getProds()) {
				Cell cell = row.createCell(colnum++);
				cell.setCellValue(prods.getName());
			}

		}

		try {
			FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
			workbook.write(outputStream);
			// workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new FileSystemResource(new File(FILE_NAME));

	}

	@RequestMapping(value = "/findByCustomerName", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public FileSystemResource findByCustomerName(@RequestParam("customerName") String customerName) {
		List<Customer> customerList = customerRepository.findByName(customerName);
		System.out.println(customerName);
		customerList.forEach(System.out::println);

		return downloadFile(receiveRepository.findByBuyer(customerList.get(0)));
	}

	@RequestMapping(value = "/findByCustomerPhoneNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public FileSystemResource findByCustomerPhoneNumber(
			@RequestParam("customerPhoneNumber") String customerPhoneNumber) {
		List<Customer> customerList = customerRepository.findByPhone(customerPhoneNumber);
		System.out.println(customerPhoneNumber);
		customerList.forEach(System.out::println);

		return downloadFile(receiveRepository.findByBuyer(customerList.get(0)));
	}

	@RequestMapping(value = "/findByProd", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public FileSystemResource findByProd(@RequestParam("prodName") String prodName) {
		List<Product> prodList = productRepository.findByName(prodName);
		System.out.println(prodName);
		prodList.forEach(System.out::println);

		return downloadFile(receiveRepository.findByProds(prodList.get(0)));
	}
	@RequestMapping(value = "/findByDateBetween", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public FileSystemResource findByDateBetween(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		
		System.out.println(startDate);
		System.out.println(endDate);		

		return downloadFile(receiveRepository.findByOrderDateBetween(startDate, endDate));
		
	}
}
