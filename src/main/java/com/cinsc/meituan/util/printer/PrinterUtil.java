package com.cinsc.meituan.util.printer;

import com.cinsc.meituan.DTO.Detail;
import com.cinsc.meituan.DTO.Order;
import com.cinsc.meituan.exception.MyException;
import com.cinsc.meituan.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PrinterUtil {//TODO，对接飞鹅打印机的工具类，没有对接可以忽略
    public static final String URL = "http://api.feieyun.cn/Api/Open/";//不需要修改
    public static final String USER = "1054988241@qq.com";//*必填*：账号名
    public static final String UKEY = "2v7dFcKwUURBHVA2";//*必填*: 注册账号后生成的UKEY
    //*必填*：打印机编号，必须要在管理后台里添加打印机或调用API接口添加之后，才能调用API
    public static final String SN = "918503304";

    public static String printOrder(String sn,Order order){
        String content = null;
        content = setOrderForm(order);
        //标签说明：
        //单标签:
        //"<BR>"为换行,"<CUT>"为切刀指令(主动切纸,仅限切刀打印机使用才有效果)
        //"<LOGO>"为打印LOGO指令(前提是预先在机器内置LOGO图片),"<PLUGIN>"为钱箱或者外置音响指令
        //成对标签：
        //"<CB></CB>"为居中放大一倍,"<B></B>"为放大一倍,"<C></C>"为居中,<L></L>字体变高一倍
        //<W></W>字体变宽一倍,"<QR></QR>"为二维码,"<BOLD></BOLD>"为字体加粗,"<RIGHT></RIGHT>"为右对齐
        //拼凑订单内容时可参考如下格式
        //根据打印纸张的宽度，自行调整内容的格式，可参考下面的样例格式

        //通过POST请求，发送打印信息到服务器
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)//读取超时
                .setConnectTimeout(30000)//连接超时
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user",USER));
        String STIME = String.valueOf(System.currentTimeMillis()/1000);
        nvps.add(new BasicNameValuePair("stime",STIME));
        nvps.add(new BasicNameValuePair("sig",signature(USER,UKEY,STIME)));
        nvps.add(new BasicNameValuePair("apiname","Open_printMsg"));//固定值,不需要修改
        nvps.add(new BasicNameValuePair("sn",sn));
        nvps.add(new BasicNameValuePair("content",content));
        nvps.add(new BasicNameValuePair("times","1"));//打印联数

        CloseableHttpResponse response = null;
        String result = null;
        try
        {
            post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
            response = httpClient.execute(post);
            int statecode = response.getStatusLine().getStatusCode();
            if(statecode == 200){
                HttpEntity httpentity = response.getEntity();
                if (httpentity != null){
                    //服务器返回的JSON字符串，建议要当做日志记录起来
                    result = EntityUtils.toString(httpentity);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                post.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
    //生成签名字符串
    private static String signature(String USER,String UKEY,String STIME){
        String s = DigestUtils.sha1Hex(USER+UKEY+STIME);
        return s;
    }


    /**
     * 设置订单打印的内容和格式
     * @param order
     * @return
     */
    public static String setOrderForm(Order order) {
        log.info("orderInfo:{}",order.toString());
        String content;
        content = "<CB>外卖平台</CB><BR>";//打印订餐的外卖平台信息
        content += "名称　　　　　 规格  数量  小计<BR>";
        content += "--------------------------------<BR>";
        for (Detail detail:order.getDetails()){
            log.info(detail.toString());
            content += convert(detail.getFood_name(),11)+
            convert(detail.getSpec(),6)+
            convert(detail.getQuantity().toString(),4)+
            convert(String.valueOf(detail.getQuantity()*detail.getPrice()),5)+
            "<BR>";
        }
        content += "备注:"+order.getCaution()+"<BR>";
//        content += "饭　　　　　　 小份    1   1.0<BR>";
//        content += "炒饭　　　　　 大份    10  10.0<BR>";
//        content += "蛋炒饭　　　　 个     10  100.0<BR>";
//        content += "鸡蛋炒饭　　　 100.0  1   100.0<BR>";
//        content += "番茄蛋炒饭　　 1000.0 1   100.0<BR>";
//        content += "西红柿蛋炒饭　 1000.0 1   100.0<BR>";
//        content += "西红柿鸡蛋炒饭 100.0  10  100.0<BR>";

//        content += "备注：加辣<BR>";
        content += "--------------------------------<BR>";
        content += "合计："+order.getTotal()+"元<BR>";
        content += "送货地点："+order.getRecipientAddress()+"<BR>";
        content += "联系电话："+order.getRecipientPhone()+"<BR>";
        content += "订餐时间:"+ MyUtil.convertTimeFormat(order.getCTime())+"<BR>";
//        content += "<QR>http://www.dzist.com</QR>";
        return content;
    }

    /**
     * 转换打印参数的长度格式
     * 菜品名长度:11
     * 规格长度:6
     * 数量长度:4
     * 小计长度:5
     * @param str
     * @param lenth
     * @return
     */
    public static String convert(String str,int lenth){
        log.info("Str:{},str.len{}",str,str.length());
        int len = str.length();
        if (len<=lenth){
            for (int i=0;i<lenth-len;i++){
                str +=" ";
            }
            return str;
        }
        throw new MyException(0,"打印参数长度异常");
    }
}
