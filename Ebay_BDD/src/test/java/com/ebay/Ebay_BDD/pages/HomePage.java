package com.ebay.Ebay_BDD.pages;




import com.ebay.Ebay_BDD.constants.*;
import com.ebay.Ebay_BDD.utils.Util;

import static org.junit.Assert.assertEquals;


/**
 * Created by Vamshi.Ponnam on 08/12/2014.
 */
public class HomePage extends Util {

   //ReadingExcelData data = new ReadingExcelData();

        public void checkLoginPageDisplayed(){
            checkIfDisplayed(Core.EBAY_LOGO);
            checkIfDisplayed(Core.INPUT_SEARCH);
            checkIfDisplayed(Core.SEARCH_BUTTON);

    }





}
