package org.veeson.common.tools.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.veeson.common.tools.core.service.HostsService;

import com.sztx.se.common.domain.Result;

@Controller
@RequestMapping("/")
public class HostsController extends BaseController{
	@Autowired
	private HostsService hostsService;

	@RequestMapping(value = "/listHosts")
	public Result listHosts() {
		Result result = commonSuccessResult(hostsService.getGoogleHosts());
		return result;
	}
	
	@RequestMapping(value = "/test")
	public String test() {
		return "index";
	}
}
