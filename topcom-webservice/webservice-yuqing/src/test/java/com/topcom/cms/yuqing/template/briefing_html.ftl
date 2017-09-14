<html lang="en">
    <title>安全生成與请报告</title>

<body>
<div id="decision">
    <div class="el-row" style="margin-left: -2.5px; margin-right: -2.5px;">
        <div class="el-col el-col-24" style="padding-left: 2.5px; padding-right: 2.5px;">
            <div class="el-card box-card">
                <div class="el-card__body" style="padding: 10px;">
                    <div class="card-body">
                        <div data-v-4d3c7668="" class="report-header">
                            <div data-v-4d3c7668="" class="title">${briefing.title?if_exists}</div>
                            <div data-v-4d3c7668="" class="period">${briefing.issue?if_exists}</div>
                            <div data-v-4d3c7668="" class="organization">${briefing.author?if_exists}</div>
                        </div>
                        <div class="report-content">
                            <div class="title">${briefing.outline?if_exists}</div>
							<#list briefing.briefingBody as briefOne>
                            <div>
                                <div class="title">${briefOne.title?if_exists}</div>
                            </div>
							<#list briefOne.children as briefTwo>
                            <div>
                                <div class="title">${briefTwo.title?if_exists}</div>
								<#if (briefTwo.imageUrl)??><#if (briefTwo.imageUrl)!="">
                                <div class="pie-chart">
                                    <div id="article-type-pie-chart">
                                        <img src="data:image/jpeg;base64,${briefTwo.imageUrl?if_exists}"/>
                                    </div>
                                </div>
								</#if>
								</#if>
                                <div class="description">
                                    <div class="describe-text"><span class="describe-redText">${briefTwo.description?if_exists}</span>
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
                                            <div class="describe-text"><span class="describe-redText">${briefThe.description?if_exists}</span>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
							</#list>
							</#list>
                        </div>
                        <div class="report-summary"><!----></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
