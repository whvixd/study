package com.github.whvixd.util;


import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

/**
 * Created by wangzhx on 2018/6/14.
 */
public class CrawlerUtil extends BreadthCrawler {

    public CrawlerUtil(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);

        for (int i = 1; i <= 5; i++) {
            addSeed(new CrawlDatum("http://news.hfut.edu.cn/list-1-" + i + ".html")
                    .meta("depth", 1));
        }

        /*正则规则用于控制爬虫自动解析出的链接，用户手动添加的链接，例如添加的种子、或
          在visit方法中添加到next中的链接并不会参与正则过滤*/
        /*自动爬取类似"http://news.hfut.edu.cn/show-xxxxxxhtml"的链接*/
        addRegex("http://news.hfut.edu.cn/show-.*html");
        /*不要爬取jpg|png|gif*/
        addRegex("-.*\\.(jpg|png|gif).*");
        /*不要爬取包含"#"的链接*/
        addRegex("-.*#.*");

    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        System.out.println("visiting:" + page.url() + "\tdepth=" + page.meta("depth"));
    }

    @Override
    protected void afterParse(Page page, CrawlDatums next) {
        //当前页面的depth为x，则从当前页面解析的后续任务的depth为x+1
        int depth = 1;
        //如果在添加种子时忘记添加depth信息，可以通过这种方式保证程序不出错
        try {
            depth = page.metaAsInt("depth");
        } catch (Exception ex) {

        }
        depth++;
        next.meta("depth", depth);
    }

    /**
     * 获取页面html内容
     *
     * @param url
     * @return
     */
    public static String getPageContent(String url) {
        if (StringUtils.isBlank(url)) {
            url = "https://www.baidu.com/";
        }

        StringBuilder stringBuilder = new StringBuilder();
        String finalUrl = url;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(finalUrl).openStream()))) {
            String line;
            while (Objects.nonNull(line = bufferedReader.readLine())) {
                stringBuilder.append(String.format("%s\n", line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //在方法中创建的线程，先执行了main线程，main线程跑完了，再去执行 task ，所以stringBuilder 是空的
        return stringBuilder.toString();
    }


  /*  public static void main(String[] args) throws Exception {
//        DemoDepthCrawler crawler = new DemoDepthCrawler("depth_crawler", true);
//        crawler.getConf().setTopN(5);
//        crawler.start(3);

        System.out.println(CrawlerUtil.getPageContent(""));

    }*/
}
