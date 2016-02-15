package org.veeson.common.tools.core.service.impl;

import org.springframework.stereotype.Service;
import org.veeson.common.tools.commons.HttpUtils;
import org.veeson.common.tools.core.service.HostsService;
/**
 * 
 * @author 覃禹顺
 * @createTime 2016年2月14日下午4:33:41
 */
@Service
public class HostsServiceImpl implements HostsService{

	/* (non-Javadoc)
	 * @see org.veeson.common.tools.core.service.HostsService#getGoogleHosts()
	 */
	@Override
	public String getGoogleHosts() {
		return HttpUtils.getGoogleHosts();
	}
	
}
