package com.github.zyt;

import us.codecraft.webmagic.*;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @Author: zyt
 * @Date: 2019/4/1 9:49
 * @Description:
 */
public class BlogSpider implements PageProcessor {

    private static int size = 0;
    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);


    @Override
    public void process(Page page) {
        if (page.getUrl().regex("https://www.cnblogs.com/cate/java").match()) {
            //列表页
            page.addTargetRequests(page.getHtml().xpath("//*[@id='paging_block']/div/a").links().all());
            //详情页
            page.addTargetRequests(page.getHtml().xpath("//*[@id='post_list']/div/div/h3/a").links().all());
        } else {
            size++;
            Blog blog = new Blog();
            blog.setKey(size);
            blog.setTitle(page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/text()").get());
            blog.setContent(page.getHtml().xpath("//*[@id=\"topics\"]/allText()").get());
            System.out.println(blog.toString());
            BlogDao blogDao = new BlogDao();
            blogDao.insert(blog);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("130.10.7.141", 808)));
        //url入口
        Spider.create(new BlogSpider())
                .addUrl("https://www.cnblogs.com/cate/java/")
                .setDownloader(httpClientDownloader)
                .thread(5)
                .run();
    }
}
