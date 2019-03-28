package com.ruiyi.askandanswer.base;

public class BaseConstant {
    //本地服务器地址
    public static String BASE_URL = "https://center.lexuewang.cn:8013/";
    public static int RESULT_OK = 200;
    public static int RESULT_EXPIRED = 401;
    public static String RESULT_401 = "HTTP 401";

    public static String BASE_CSS = "<html><header><style>" + "div.quizPutTag { display: inline-block; padding: 3px 10px 1px 10px; margin: 0 3px; font-size: 14px; min-width: 1em; min-height: 16px; line-height: 18px; height: auto; border-bottom: 1px solid #0033FF; text-decoration: none; zoom: 1; background: #fff; color: #127176; word-break: break-all; }\n" +
            "div.quizPutTag:hover { color: #f60; }\n" +
            "\n" +
            "table.edittable{ border-collapse: collapse; text-align: center; margin: 2px; }\n" +
            "table.edittable th, table.edittable td{ line-height: 30px; padding: 5px; white-space: normal; word-break: break-all; border: 1px solid #000; vertical-align: middle; }\n" +
            "table.composition{ border-collapse: collapse; text-align: left; margin: 2px; width: 98%; }\n" +
            "table.composition th, table.composition td{ line-height: 30px; white-space: normal; word-break: break-all; border-width: 0px; vertical-align: middle; }\n" +
            "table.composition2{ border-collapse: collapse;width:auto }\n" +
            "table.composition2 th, table.composition2 td{text-align:left;line-height:30px; white-space:normal;word-break:break-all;border:none;border-width: 0px;vertical-align: middle; }\n" +
            ".MathJye{ border: 0 none; direction: ltr; line-height: normal; display: inline-block; float: none; font-family: 'Times New Roman','宋体'; font-size: 15px; font-style: normal; font-weight: normal; letter-spacing: 1px; line-height: normal; margin: 0; padding: 0; text-align: left; text-indent: 0; text-transform: none; white-space: nowrap; word-spacing: normal; word-wrap: normal; -webkit-text-size-adjust: none; }\n" +
            ".MathJye div, .MathJye span{ border: 0 none; margin: 0; padding: 0; line-height: normal; text-align: left; height: auto; _height: auto; white-space: normal; }\n" +
            ".MathJye table{ border-collapse: collapse; margin: 0; padding: 0; text-align: center; vertical-align: middle; line-height: normal; font-size: inherit; *font-size: 100%; _font-size: 100%; font-style: normal; font-weight: normal; border: 0; float: none; display: inline-block; *display: inline; zoom: 0; }\n" +
            ".MathJye table td{ padding: 0; font-size: inherit; line-height: normal; white-space: nowrap; border: 0 none; width: auto; _height: auto; }\n" +
            ".MathJye_mi{ font-style: italic; }\n" +
            ".flipv{-ms-transform: scaleX(-1);-moz-transform: scaleX(-1);-webkit-transform: scaleX(-1);-o-transform: scaleX(-1);transform: scaleX(-1);filter: FlipH;}\n" +
            ".fliph{-ms-transform: scaleY(-1);-moz-transform: scaleY(-1);-webkit-transform: scaleY(-1);-o-transform: scaleY(-1);transform: scaleY(-1);filter: FlipV;}\n" +
            ".mathjye-bold{font-weight:800}\n" +
            ".mathjye-del{text-decoration:line-through}\n" +
            ".mathjye-underline{border-bottom:1px solid #000;padding-bottom:2px;}\n" +
            "@-moz-document url-prefix() {.mathjye-underline{padding-bottom:0px;}}\n" +
            ".mathjye-underpline{border-bottom:2px dotted #000; padding-bottom:3px;}\n" +
            "@-moz-document url-prefix() {.mathjye-underpline {padding-bottom:1px;}}\n" +
            ".mathjye-underpoint{background: url(http://img.jyeoo.net/images/formula/point.png) no-repeat center bottom; padding-bottom:4px;}\n" +
            ".mathjye-underpoint2{border-bottom:2px dotted #000; padding-bottom:3px;}\n" +
            "@-moz-document url-prefix() {.mathjye-underpoint{padding-bottom:1px;}}\n" +
            ".mathjye-underwave{background: url(http://img.jyeoo.net/images/formula/wave.png) bottom repeat-x; padding-bottom:4px;}\n" +
            "@-moz-document url-prefix() {.mathjye-underwave {padding-bottom:1px;}}\n" +
            ".mathjye-alignleft{display:block;text-align:left;}\n" +
            ".mathjye-aligncenter{display:block;text-align:center;}\n" +
            ".mathjye-alignright{display:block;text-align:right;}" + "</style></header><body>";

    public static String APP_ID = "";
}
