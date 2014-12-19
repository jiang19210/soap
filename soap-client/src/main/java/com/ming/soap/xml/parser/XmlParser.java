package com.ming.soap.xml.parser;

import java.io.File;

/**
 * XML解析
 * @author jianggm
 * */
public interface XmlParser {
	/**
	 * XML解析
	 * @author jianggm
	 * @param  待解析的xml流
	 * */
	public Object parser (File file);

}
