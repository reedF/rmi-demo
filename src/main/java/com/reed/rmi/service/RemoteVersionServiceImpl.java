package com.reed.rmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Service;

@Service
public class RemoteVersionServiceImpl implements RemoteVersionService {

	@Autowired
	private Environment env;

	private final static String key = "version";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lashou.v.rmi.service.RemoteVersionService#getVersion(int)
	 */
	@Override
	public String getVersion() {
		String v = null;
		StandardEnvironment senv = (StandardEnvironment) env;
		v = (String) senv.getPropertySources().get("version").getProperty(key);

		return v;
	}
}
