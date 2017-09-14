<#setting datetime_format="yyyy-MM-dd HH:mm"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body, div, ul, li, a, p, span {
            margin: 0;
            padding: 0;
        }

        body {
            font-size: 14px;
            color: #475669;
        }

        a {
            text-decoration: none!important;
            color: #475669;
        }

        .box {
            width: 700px;
            margin: 10px auto;
            border: 1px solid #8492A6;
            padding: 15px;
        }

        .header {
            font: 600 18px/30px Microsoft Yahei;
            border-bottom: 1px dashed #475669;
            padding-bottom: 10px;
        }

        .title {
            text-indent: 2em;
        }

        .main-list {

        }



        .main-list dl dt {
            border-bottom: 1px dashed #475669;
            padding: 10px 0;
        }

        .main-list p {
            height: 25px;
            line-height: 25px;
        }

        .main-list p:nth-of-type(1) {
            font-weight: 600;
        }

        .main-list p:nth-of-type(2) {
            color: #8492A6;
        }

        .negative {
            float: right;
            color: #d74e67;
            font-weight: 600;
            border-radius: 10px;
            border: 1px solid #d74e67;
            padding: 3px;
        }

        .positive {
            float: right;
            color: #0092ff;
            font-weight: 600;
            border: 1px solid #0092ff;
            border-radius: 10px;
            padding: 3px;
        }

        .neutral {
            float: right;
            color: #eba954;
            font-weight: 600;
            border-radius: 10px;
            border: 1px solid #eba954;
            padding: 3px;
        }

        .blue {
            color: #20A0FF;
        }

        .red {
            color: #FF4949;
        }

        .footer {
            height: 30px;
            line-height: 30px;
        }

        .more {
            float: right;
            font-weight: 600;
            color: #475669;
        }

        .ml-15 {
            margin-left: 15px;
        }
    </style>
</head>
<body>
<div class="box">
    <div class="header">
        <p>尊敬的:${warning.user?if_exists}</p>
        <p class="title">截至目前，您的预警方案<a href="${warning.specialUrl?if_exists}"><span class="blue">[${warning.specialName?if_exists}]</span></a>新增<span class="red">${warning.specialNumber?if_exists}条</span>预警</p>
    </div>
    <div class="main-list">
        <dl>
			<#if (warning.content)??>
            <#list warning.content as warningBody>
            <dt>
                <a href="${warningBody.url?if_exists}">
					<#if (warningBody.title)??>
                    <p>
                    <#if warningBody.title?length gt 30>
                    ${warningBody.title?substring(0,30)}...
                    <#else>
                    ${warningBody.title}
                    </#if>
						 <#if (warningBody.nlp.sentiment.label)??>
                                    <#if warningBody.nlp.sentiment.label == 'POS'>
                                        <td style = "width:60px;height:30px;">
                                            <span class="positive">正面</span>
                                        </td>
                                    <#elseif warningBody.nlp.sentiment.label  == 'NEG'>
                                        <td style = "width:60px;height:30px;">
                                             <span class="negative">负面</span>
                                        </td>
                                    <#elseif warningBody.nlp.sentiment.label == 'NEU'>
                                        <td style = "width:60px;height:30px;">
                                            <span class="neutral">中性</span>
                                        </td>
                                    <#else>
                                    </#if>
                                </#if>
                    </p>
					</#if>
                    <p><span>${warningBody.pubTime?replace(",","")?number?number_to_datetime}</span> <span class="ml-15">来自:${warningBody.site?if_exists}</span></p>
                    <p><#if warningBody.content?length gt 40>
                    ${warningBody.content?substring(0,40)}...
                    <#else>
                    ${warningBody.content}
                    </#if></p>
                    <#--<p>${warningBody.content?substring(0,40)}....</p>-->
                </a>
            </dt>
                </#list>
            </#if>
        </dl>
    </div>
    <div class="footer">
        <span class="more"><a href="${warning.warningLogUrl?if_exists}">查看更多</a></span>
    </div>
</div>


</body>
</html>