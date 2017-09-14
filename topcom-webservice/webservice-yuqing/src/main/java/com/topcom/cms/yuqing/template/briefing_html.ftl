<!DOCTYPE html>
<!-- saved from url=(0048)http://localhost:8090/module/decisionReport.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <style>
    .body img {
        vertical-align: middle;
        height: 410px;
    }
    .report-header {
    text-align: center;
    border-bottom: 2px solid #888;
    font-size: 20px;
    height: 140px;
}
.report-header .title {
    color: #ff0000;
    font-size: 30px;
    line-height: 78px;
    width: 378px;
    height: 78px;
    margin: auto;
    background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAXoAAABOCAYAAAAuPN2fAAAACXBIW…8KRZ6eBvk26+gdDofD8cuN6KfA4XA4nOgdDofD8UuM/x0AkO2uMFKu5BUAAAAASUVORK5CYII=);
}
.title.level-one {
    color: #333;
    font-size: 26px;
    line-height: 78px;
}
.report-content .title.level-one {
    color: #333;
    font-size: 26px;
    line-height: 78px;
}
.outline {
        text-align: left;
        padding:10px;
        border: 1px solid #7DB5EE;
        -webkit-box-shadow: 0 0 10px #1D90E6;
        -moz-box-shadow: 0 0 10px #1D90E6;
        box-shadow: 0 0 10px #1D90E6;
        border-radius: 5px;
        font-size: 20px;
        min-height: 50px;
        margin: 50px;
        margin-bottom: 20px;
        line-height: 50px;
        font-weight: 600;
        }
        #article-type-pie-chart{
         min-height:500px
        }
.report-content .describe-text {
    text-indent: 2em;
    color: #000000;
    font-size: 18px;
    font-weight: 600;
    line-height: 36px;
}
.report-content .title.level-two {
    color: #555;
    font-size: 18px;
    line-height: 50px;
}
.report-content .describe-text .describe-redText {
    color: #eb7e23;
    font-size: 24px;
}
.article-type-pie-chart{

       margin-left: 230px!important;


}
    </style>
    <title >安全生产與请报告</title>

<body>
<div id="decision">
    <div class="el-row" style="margin-left: -2.5px; margin-right: -2.5px;">
        <div class="el-col el-col-24" style="padding-left: 2.5px; padding-right: 2.5px;">
            <div class="el-card box-card">
                <div class="el-card__header">
                </div>
                <div class="el-card__body" style="padding: 10px;">
                    <div class="card-body">
                        <div data-v-4d3c7668="" class="report-header">
                            <div data-v-4d3c7668="" class="title">${briefing.title?if_exists}</div>
                            <div data-v-4d3c7668="" class="period">${briefing.issue?if_exists}</div>
                            <div data-v-4d3c7668="" class="organization">${briefing.author?if_exists}</div>
                        </div>
                        <div class="report-content">
                            <div class="title outline">	${briefing.outline?if_exists}</div>
								<#list briefing.briefingBody as briefOne>
                            <div>
                                <div class="title level-one">${briefOne.title?if_exists}</div>
                            </div>
							<#list briefOne.children as briefTwo>
                            <div>
                                <div class="title ">${briefTwo.title?if_exists}</div>
								  <#if (briefTwo.imageUrl)??><#if (briefTwo.imageUrl)!="">
                                <div class="pie-chart">
                                    <div id="article-type-pie-chart" _echarts_instance_="ec_1495785140186"
                                         style="-webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background: transparent;">
                                        <div
                                            style="position: relative; overflow: hidden; width: 800px; height: 400px; padding: 0px; margin: 0px; border-width: 0px; cursor: default;">
                                            <canvas width="800" height="400" data-zr-dom-id="zr_0"
                                                    style="position: absolute; left: 0px; top: 0px; width: 800px; height: 400px; -webkit-user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); padding: 0px; margin: 0px; border-width: 0px;">
												<img src="data:image/jpeg;base64,${briefTwo.imageUrl?if_exists}"/>
                                        </div>
                                    </div>
                                </div> <!---->
								</#if>
								</#if>
                                <div class="description">
                                   <div class="describe-text"><span>${briefTwo.description?if_exists}</span>
                                </div>
                            </div>
                        </div>
						  <#list briefTwo.children as briefThe>
                                    <div>
                                        <div class="title">${briefThe.title?if_exists}</div>
                                        <#if (briefThe.imageUrl)??><#if (briefThe.imageUrl)!="">
                                            <div class="pie-chart">
                                                <div id="article-type-pie-chart">
                                                    <img src="data:image/jpeg;base64,${briefThe.imageUrl?if_exists}"/>
                                                </div>
                                            </div>
                                        </#if>
                                        </#if>
                                        <div class="description">
                                            <div class="describe-text"><span >${briefThe.description?if_exists}</span>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
							</#list>
							</#list>		
                        <div class="report-summary"><!----></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>




