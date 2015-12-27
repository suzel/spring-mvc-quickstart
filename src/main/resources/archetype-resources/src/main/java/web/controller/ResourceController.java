package ${package}.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceController {

	private static final String VIEW_FAVICON = "forward:/resources/img/icons/favicon.ico";
	
	@RequestMapping("favicon.ico")
	String favicon() {
		return VIEW_FAVICON;
	}

}