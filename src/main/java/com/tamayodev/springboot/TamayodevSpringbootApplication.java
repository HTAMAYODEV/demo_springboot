package com.tamayodev.springboot;

import com.tamayodev.springboot.bean.MyBean;
import com.tamayodev.springboot.bean.MyBeanWithDependency;
import com.tamayodev.springboot.bean.MyBeanWithProperties;
import com.tamayodev.springboot.entity.User;
import com.tamayodev.springboot.pojo.UserPojo;
import com.tamayodev.springboot.repository.PostRepository;
import com.tamayodev.springboot.repository.UserRepository;
import com.tamayodev.springboot.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TamayodevSpringbootApplication implements CommandLineRunner {
	//
    Log LOGGER = LogFactory.getLog(TamayodevSpringbootApplication.class);
    //
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
	//
	private UserRepository userRepository;
	private PostRepository postRepository;
	//
	private UserService userService;
	//
	public TamayodevSpringbootApplication(MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,
										  UserRepository userRepository, PostRepository postRepository,
										  UserService userService) {
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.userService = userService;
	}
	//
	public static void main(String[] args) {
		SpringApplication.run(TamayodevSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//testDependencies1();
		//
		saveUsersInDataBase();
		getInfoFromUser();
		//
		saveWithErrorTransactional();
		//
	}

	private void saveWithErrorTransactional() {
		LOGGER.info("----------------------------------TX------------------------------------");
		User test1 = new User("TestTx1", "text1tx@domain.com", LocalDate.now());
		//User test2 = new User("TestTx2", "text1tx@domain.com", LocalDate.now());
		User test2 = new User("TestTx2", "text2tx@domain.com", LocalDate.now());
		User test3 = new User("TestTx3", "text3tx@domain.com", LocalDate.now());
		User test4 = new User("TestTx4", "text4tx@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		LOGGER.info("--------------INSERT--------------");
		try {
			userService.saveTransactional(users);
		} catch (Exception ex) {
			LOGGER.error("Error in saveWithErrorTransactional: " + ex.getMessage());
		}
		LOGGER.info("--------------INSERT--------------");

		LOGGER.info("--------------GET--------------");
		userService.getAllUsers().stream().forEach(user -> LOGGER.info("Tx User: " + user));
		LOGGER.info("--------------GET--------------");
		LOGGER.info("----------------------------------TX------------------------------------");
	}

	private void getInfoFromUser() {
		LOGGER.info("----------------------------------JPQL------------------------------------");
		LOGGER.info("User: " +
				userRepository.findByUserEmail("harvey@tamayodev.com")
				.orElseThrow(()-> new RuntimeException("User not found.")));

		userRepository.findAndSort("H", Sort.by("id").descending())
				.stream().forEach(user -> LOGGER.info("Ordered User: " + user));

		LOGGER.info("User by Named Parameters: " +
				userRepository.getAllByBirthDateAndEmail(
						LocalDate.of(1989, 11, 30),
						"harvey@tamayodev.com")
				.orElseThrow(() -> new RuntimeException("NamedParameter JPQL Query not found coincidence.")));

		LOGGER.info("----------------------------------JPQL------------------------------------");

		LOGGER.info("----------------------------------QUERY METHODS------------------------------------");
		userRepository.findByName("Nicolas").stream()
				.forEach(user -> LOGGER.info("Query Method: " + user));

		LOGGER.info("User By Name And Email: "
				+ userRepository.findByNameAndEmail("Harvey", "harvey@tamayodev.com")
				.orElseThrow(() -> new RuntimeException("Query Method Not found Coincidence.")));

		userRepository.findByNameLike("%H%")
				.stream().forEach(user -> LOGGER.info("User Like Name: " + user));

		userRepository.findByNameOrEmail("Harvey", null)
				.stream().forEach(user -> LOGGER.info("User By Name Or Email: " + user));

		userRepository.findByBirthdateBetween(
						LocalDate.of(1989, 01, 01),
						LocalDate.of(1994, 12, 31))
				.stream().forEach(user -> LOGGER.info("User Birthdate: " + user));

		userRepository.findByNameLikeOrderByIdDesc("H%")
				.stream().forEach(user -> LOGGER.info("User Like Name Order DESC By Id: " + user));

		userRepository.findByNameContainingOrderByIdDesc("Nicolas")
				.stream().forEach(user -> LOGGER.info("User Containing Name Order DESC By Id: " + user));
		LOGGER.info("----------------------------------QUERY METHODS------------------------------------");
	}

	private void saveUsersInDataBase() {
		User harvey = new User("Harvey", "harvey@tamayodev.com", LocalDate.of(1989, 11, 30));
		User nicolas = new User("Nicolas", "nicolas@tamayodev.com", LocalDate.of(1994, 11, 30));
		User htamayo = new User("H.Nicolas", "htamayo@tamayodev.com", LocalDate.of(1999, 11, 30));

		List<User> users = Arrays.asList(harvey, nicolas, htamayo);

		users.stream().forEach(userRepository::save);
	}

	private void testDependencies1() {
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println("Properties: " + myBeanWithProperties.getUser());
		System.out.println("UserPojo: " + userPojo);
		try {
			int result = 10 / 0;
			LOGGER.debug("Result: " + result);
		} catch (Exception ex) {
			LOGGER.error("Catch: " + ex.getMessage());
		}
	}
}
