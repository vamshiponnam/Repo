package com.ebay.Ebay_BDD.utils;



import com.ebay.Ebay_BDD.Trade;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReader {
	String dataXLS;
	public DataReader(String workbookName) {
		dataXLS ="/cucumber/examples/java/jms/data/" + workbookName;
	}

	public List<Trade> getTrades() throws IOException, SAXException, InvalidFormatException {
		String xmlConfig = "/cucumber/examples/java/jms/jxls-reader/trade-data.xml";
		InputStream inputXML = new BufferedInputStream(getClass().getResourceAsStream(xmlConfig));
        XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );
		InputStream inputXLS = new BufferedInputStream(getClass().getResourceAsStream(dataXLS));
        List<Trade> trades = new ArrayList<Trade>();
        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put("trades", trades);
        XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
        readStatus.getReadMessages();
        return trades;
	}

}
