package com.rebwon.demosecurity.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rebwon.demosecurity.account.Account;
import com.rebwon.demosecurity.account.AccountService;
import com.rebwon.demosecurity.book.Book;
import com.rebwon.demosecurity.book.BookRepository;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

	@Autowired
	AccountService accountService;

	@Autowired
	BookRepository bookRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Account rebwon = createUser("rebwon");
		Account solomon = createUser("solomon");
		createBook("spring", rebwon);
		createBook("hibernate", solomon);
	}

	private void createBook(String title, Account keesun) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(keesun);
		bookRepository.save(book);
	}

	private Account createUser(String username) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword("123");
		account.setRole("USER");
		return accountService.create(account);
	}
}
