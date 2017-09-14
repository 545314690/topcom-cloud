package com.topcom.cms.yuqing.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.topcom.cms.data.domain.News;
import net.sf.json.JSONObject;
import org.nlpcn.commons.lang.finger.FingerprintService;
import org.nlpcn.commons.lang.finger.SimHashService;
import org.springframework.stereotype.Component;

import java.util.List;

public class NewsIdUtil {

    public static JSONObject produceNewsId(JSONObject news) {
//        String url = news.get("url").toString();
//        String domainUlr = UrlDomain.urlDamain(url);
//        String id = MD5Utils.md5(url);
//        news.put("old_id",news.get("id"));
//        news.put("id",id);
        news.put("textSimHash",SimHasher.analysis( news.getString("title") + news.getString("content")).toString());
        news.put("titleSimHash",SimHasher.analysis( news.getString("title")).toString());
        return news;
    }

    public static void main(String[] args) {
        SimHashService simHashService = new SimHashService();
        FingerprintService fingerprintService = new FingerprintService();
        String title = "AVANCE: Asciende a 62 cifra de muertes por incendios forestales en Portugal AVANCE: Asciende a 62 cifra de muertes por incendios forestales en Portugal";
        String content = "LISBOA, 18 jun (Xinhua) -- El n&uacute;mero de gente que ha perdido la vida en los incendios forestales que ocurrieron en el centro de Portugal ascendi&oacute; a 62 y otras 54 han resultado heridas, dijo hoy el secretario de Estado del Ministerio de Asuntos Internos, Jorge Gomes. Gomes inform&oacute; a los reporteros que de las v&iacute;ctimas, dos murieron en un accidente de tr&aacute;nsito y que cuatro de los heridos est&aacute;n graves. El gobierno portugu&eacute;s declar&oacute; un estado de emergencia en el &aacute;rea del incendio y luto nacional de tres d&iacute;as a partir de hoy. Los incendios estallaron el s&aacute;bado en Pedrogao Grande, a unos 150 kil&oacute;metros al nordeste de Lisboa, y se propagaron r&aacute;pidamente a las localidades de Figueird dos Vinhos y Castanheira de Pera en el distrito de Leiria, donde los bomberos siguen luchando contra el fuego.";
        String title1 = "文在寅到访美国 美韩总统将会晤";
        String content1 = "　　→→ 更多时政新闻 (责编：李美玉、梁倩)";
        String s1 = title1+content1;
        String s=title+content;
        System.out.println(fingerprintService.fingerprint(s1));
        System.out.println(fingerprintService.fingerprint(s));
        System.out.println(fingerprintService.fingerprint(title));
        System.out.println(fingerprintService.fingerprint(title1));
        System.out.println(fingerprintService.fingerprint("sascxa"));
        List<Term> strings = HanLP.newSegment().enableAllNamedEntityRecognize(true).seg(title1);
        System.out.println(strings);
    }
}
