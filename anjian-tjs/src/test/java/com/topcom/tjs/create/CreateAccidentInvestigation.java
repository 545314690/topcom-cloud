package com.topcom.tjs.create;

import com.topcom.tjs.domain.TjsAccidentInvestigation;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.service.TjsAccidentInvestigationManager;
import com.topcom.tjs.service.TjsSpecialCompanyManager;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author maxl
 * @date 2018/3/28 0028
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateAccidentInvestigation {

    @Autowired
    TjsAccidentInvestigationManager tjsAccidentInvestigationManager;
    public void create(){

    }

}
