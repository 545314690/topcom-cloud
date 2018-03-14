package com.topcom;

import com.topcom.yzswf.service.EsFileService;
import com.topcom.yzswf.vo.EsIndexFile;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnjianYzsfwApplicationTests {
	@Autowired
	private EsFileService esFileService;
	private String folder = "I:\\非煤特重大事故案例汇编\\word";
	@Test
	public void indexFileTest() {
		File[] files = new File(folder).listFiles();
		for (int i = 0; i <files.length ; i++) {
			try {
				EsIndexFile esIndexFile = new EsIndexFile(files[i].getAbsolutePath());
				String name = files[i].getName();
				esIndexFile.setFilename(name);
				esIndexFile.setSource("安监总局");
				esIndexFile.setType("事故案例");
				Random random = new Random();
				//随机取时间
				int date = -random.nextInt(100);
				esIndexFile.setUploadTime(DateUtils.addDays(new Date(),date).getTime());
				if(name!=null && name.split("\\.").length == 2){
					esIndexFile.setFileType(name.split("\\.")[1]);
				}
				esFileService.index2Es(esIndexFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
