package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	/*De esta forma no creamos constructor
	@Autowired
	@Qualifier("componentTwoImplement")*/
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;
	@Autowired
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		//getInformationJpqlFromUser();
		//saveWithErrorTransactional();
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " " + userPojo.getPassword() + " " + userPojo.getAge());
		LOGGER.error("Esto es un error del aplicativo");
	}

	private void saveUsersInDataBase(){
		User user1 = new User("John","john@gmail.com", LocalDate.of(2021,3,20));
		User user2 = new User("John","julie@gmail.com", LocalDate.of(2021,1,21));
		User user3 = new User("Daniel","daniel@gmail.com", LocalDate.of(2001,7,2));
		User user4 = new User("Maria","maria@gmail.com", LocalDate.of(2005,2,15));
		User user5 = new User("Andres","andres@gmail.com", LocalDate.of(1980,3,15));
		User user6 = new User("Mateo","mateo@gmail.com", LocalDate.of(1992,5,25));
		User user7 = new User("Gloria","gloria@gmail.com", LocalDate.of(2000,5,22));
		User user8 = new User("Sandra","sandra@gmail.com", LocalDate.of(2002,9,27));
		User user9 = new User("Xiomara","xiomara@gmail.com", LocalDate.of(1990,12,2));
		User user10 = new User("Jairo","jairo@gmail.com", LocalDate.of(1998,1,1));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRepository::save);
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("testTransactional1","testTransactional1@gmail.com", LocalDate.of(2021,3,20));
		User test2 = new User("testTransactional2","testTransactional2@gmail.com", LocalDate.of(2000,1,2));
		User test3 = new User("testTransactional3","testTransactional3@gmail.com", LocalDate.of(1998,5,2));
		User test4 = new User("testTransactional4","testTransactional4@gmail.com", LocalDate.of(1994,12,10));

		List<User> users = Arrays.asList(test1,test2,test3,test4);
		userService.saveTransactional(users);
		userService.getAllUsers()
				.stream()
				.forEach(user -> LOGGER.info("Este es el usuario del metodo transaccional " + user));
	}

	private void getInformationJpqlFromUser(){
		/*
		LOGGER.info("Usuario con el metodo findByUserEmail" +
				userRepository.findByUserEmail("xiomara@gmail.com")
				.orElseThrow(() -> new RuntimeException(("No se encontro el usuario"))));
		*/
		/*
		userRepository.findAndSort("J", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort" + user));
		*/
		/*
		userRepository.findByName("John")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method" + user) );
		*/
		/*
		LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.findByEmailAndName("julie@gmail.com","John")
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
		*/
		/*
		userRepository.findByNameLike("%ani%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike" + user));
		*/
		/*
		userRepository.findByNameOrEmail("Fufurufa","mateo@gmail.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail" + user));
		 */
		/*
		userRepository.findByBirthDateBetween(LocalDate.of(2000,2,1),LocalDate.of(2005,1,2))
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByBirthdayDateBetween" + user));
		*/
		/*
		userRepository.findByNameLikeOrderByIdDesc("%a%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLikeOrderByIdDesc" + user));
		*/
		/*
		userRepository.findByNameContainingOrderByIdDesc("a")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameContainOrderByIdDesc" + user));
		 */
		/*
		LOGGER.info(userRepository.getAllByBirthDateAndEmail(LocalDate.of(2002,9,27),"sandra@gmail.com")
				.orElseThrow(()->
						new RuntimeException("No se encontro el usuario a partir del named Parameter")));
		 */
	}
}
