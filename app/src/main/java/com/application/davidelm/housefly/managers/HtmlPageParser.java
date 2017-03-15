package com.application.davidelm.housefly.managers;

import android.net.wifi.WifiConfiguration;
import android.util.Log;

import com.application.davidelm.housefly.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

import butterknife.ButterKnife;
import okhttp3.Protocol;


/**
 * Created by davide on 10/03/2017.
 */

public class HtmlPageParser {
    public void parseHtml() {
        try {
            Document doc = Jsoup.parse("http://torino.bakeca.it/annunci/offro-casa/inserzionistacase/privato/luogo/torino/tipoimmobile/");
            Elements list = doc.getElementsByAttribute("ul[data-bk-filtro-type='checkboxlist'] li");
            for (Element item : list) {
                String id = item.id();
                if (id != null) {
                    Element result = doc.getElementById(id).children().get(0).children().get(0);
                    Log.e("TAG", result.getElementsByAttribute("data-index") + "hey");
                    Log.e("TAG", result.getElementsByAttribute("data-code") + "hey");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        $("ul[data-bk-filtro-type='checkboxlist'] li").each(function(){
//            var id = $(this).attr('id');
//            if (id != undefined) {
//                var temp = $("#" + id).children().children()[0];
//                console.log($(temp).attr("data-index") + ";" + $(temp).attr("data-code"));
//            }
//        })



//        http://torino.bakeca.it/annunci/offro-casa/inserzionistacase/privato/luogo/Torino/tipoimmobile/52,50/nope/true/quartiere/6,142,4

    }

}
