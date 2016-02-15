package org.veeson.common.tools.web.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sztx.se.common.domain.Result;
import com.sztx.se.common.domain.ResultCode;
/**
 * 基础Controller
 * 
 * @see 
 * @author qinys
 * 
 */
@Controller
@RequestMapping("/")
public abstract class BaseController extends com.sztx.se.web.controller.BaseController {
	/**
	 * 日志
	 */
	protected final Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/")
	public ModelAndView index() throws IOException {
		return new ModelAndView("index");
	}
	
	protected String getIpAddress() {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
		HttpServletRequest request = getHttpServletRequest();
		String ip = request.getHeader("X-Forwarded-For");
		if (logger.isDebugEnabled()) {
			logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (logger.isDebugEnabled() && ip != null) {
					logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (logger.isDebugEnabled() && ip != null) {
					logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				if (logger.isDebugEnabled() && ip != null) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if (logger.isDebugEnabled() && ip != null) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (logger.isDebugEnabled() && ip != null) {
					logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
				}
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}

	protected Result commonSuccessResult() {
		return new Result(ResultCode.COMMON_SUCCESS, true);
	}

	protected Result commonSuccessResult(Object data) {
		Result result = new Result(ResultCode.COMMON_SUCCESS, true);
		result.setUseDateFormat(true);
		result.setProperty("data", data);
		logger.debug(result.toString());
		return result;
	}
	
	protected Result pageSuccessResult(Integer size,Collection<?> e) {
		Result result = new Result(ResultCode.COMMON_SUCCESS, true);
		result.setUseDateFormat(true);
		result.setPage(size, e);;
		logger.debug(result.toString());
		return result;
	}
	
	protected HttpSession getSession() {
		HttpServletRequest request = getHttpServletRequest();
		return request.getSession();
	}

	protected void setSessionAttribute(String key, Object value) {
		HttpSession httpSession = getSession();
		httpSession.setAttribute(key, value);
	}

	protected Object getSessionAttribute(String key) {
		HttpSession httpSession = getSession();
		return httpSession.getAttribute(key);
	}
	
}