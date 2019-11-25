<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <#include "common.ftl" >
    <!-- 引入 echarts.js -->
    <script src="${ctx}/js/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 800px;height:600px;"></div>
<script type="text/javascript">

    $.ajax({
        url: ctx + '/report/queryCustomerLevel',
        success:function (data) {

            //处理数据
            var indicator = [];
            var value = [];

            for(var i=0;i<data.length;i++){
                indicator.push({name:data[i].name,max:10});
                value.push(data[i].value);
            }

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '客户构成分析'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    x: 'center',
                    data:['客户类别']
                },
                radar: [
                    {
                        indicator: indicator,
                        radius: 180
                    }
                ],
                series: [
                    {
                        type: 'radar',
                        tooltip: {
                            trigger: 'item'
                        },
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: [
                            {
                                value: value,
                                name: '客户类别'
                            }
                        ]
                    }
                ]
            };


            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });


</script>
</body>
</html>