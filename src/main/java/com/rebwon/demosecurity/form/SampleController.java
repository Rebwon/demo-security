package com.rebwon.demosecurity.form;

import java.security.Principal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rebwon.demosecurity.account.AccountRepository;
import com.rebwon.demosecurity.common.SecurityLogger;

@Controller
public class SampleController {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	SampleService sampleService;

	@GetMapping("/")
	public String index(Model model, Principal principal) {
		if(principal == null) {
			model.addAttribute("message", "Hello Spring Security");
		} else{
			model.addAttribute("message", "Hello, " + principal.getName());
		}
		return "index";
	}

	@GetMapping("/info")
	public String info(Model model) {
		model.addAttribute("message", "Info");
		return "info";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("message", "Hello " + principal.getName());
		sampleService.dashboard();
		return "dashboard";
	}

	@GetMapping("/admin")
	public String admin(Model model, Principal principal) {
		model.addAttribute("message", "Hello Admin, " + principal.getName());
		return "admin";
	}

	@GetMapping("/user")
	public String user(Model model, Principal principal) {
		model.addAttribute("message", "Hello User, " + principal.getName());
		return "user";
	}

	@GetMapping("/async-handle")
	@ResponseBody
	public Callable<String> asyncHandler() {
		SecurityLogger.log("MVC");

		return () -> {
			SecurityLogger.log("Callable");
			return "Async Handler";
		};
	}

	@GetMapping("/async-service")
	@ResponseBody
	public String asyncService() {
		SecurityLogger.log("MVC, before async service");
		sampleService.async();
		SecurityLogger.log("MVC, after async service");
		return "Async Service";
	}
}
