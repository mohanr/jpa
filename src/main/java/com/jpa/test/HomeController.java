package com.jpa.test;

import java.util.Locale;

import javax.persistence.TransactionRequiredException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {
	
	
	@Autowired
	private Test test;

	private static final Logger logger = LoggerFactory.getLogger("jpa");
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/Rest", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("@RequestMapping(value = \"/Rest\", method = RequestMethod.GET)");
		test.test();
		test.getAddress();
		//test.roundTrip();
		
		//ar.persistAndRead();
		return "home";
	}
	
}
