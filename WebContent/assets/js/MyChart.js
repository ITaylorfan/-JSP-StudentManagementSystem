function getData(){
	//获取数据
	$.get("../MyCourseChart",function(data,status){
	    //console.log("数据: " + JSON.stringify(data)+ "\n状态: " + status);
	    let DATA=JSON.stringify(data);
	    //console.log(JSON.parse(DATA))
	    DATA=JSON.parse(DATA)
	     
	    data2Array(DATA);
	   
	  });
}
function DrawEchart(DATA) {
	/*var classDatas1 = [ [ '8:00-8:45', '星期一', '\n英语\n1栋306' ],
			[ '9:20-10:00', '周一', '\n高数\n12栋110' ],
			[ '10:20-11:00', '周一', '\nC语言\n10栋603' ],
			[ '11:10-11:50', '周二', '\n马克思主义概论\n10栋501' ],
			[ '15:20-16:00', '周四', '\n心理学\n10栋201' ], ];*/
	/*	var classDatas2 = [ 
	 [ '8:30-9:10', '周一', '\n英语\n1栋306' ], [ '9:20-10:00',
	 '周一', '\n体育\n12栋106' ], [ '10:20-11:00', '周一',
	 '\nC语言\n10栋301' ], [ '11:10-11:50', '周三',
	 '\n马克思主义概论\n10栋501' ], [ '14:30-15:10', '周三',
	 '\n计算机基础\n10栋412' ],
	 ];*/
	var classDatas1 = DATA;
	var classDatas2 =DATA;
	option = {
		backgroundColor : 'RGB(240, 248, 255)',
		title : {
			text : '5183002142课程表',
			subtext : '2020下半年',

		},
		legend : {
			top : 20,
			right : 80,
			selectedMode : 'single',
			data : [ '单周', '双周' ],
			inactiveColor : '#9FB6CD',
			textStyle : {
				color : '#6495ED'
			}

		},
		grid : {},
		toolbox : {
			feature : {
				saveAsImage : {}
			}
		},
		tooltip : {
			show : true,
			formatter : '{c}',
			// trigger:'axis',
			hideDelay : 300,
		},
		xAxis : {
			type : 'category',
			data : [ '', '8:00-9:40', '', '10:00-11:40',
					'', '14:00-15:40', '', '16:00-17:40',
					'', '19:00-21:40','' ],
			boundaryGap : false,
			splitLine : {
				show : true
			},
			axisLabel : {
				interval : 0,
				rotate : 30
			}

		},

		yAxis : {
			type : 'category',
			boundaryGap : false,
			splitLine : {
				show : true
			},
			data : [ '', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日']
		},
		series : [ {
			name : '单周',
			type : 'scatter',
			symbol : 'pin',
			symbolSize : 60,
			// symbolRotate:30,
			label : {
				normal : {
					show : true,
					rotate : 30,
					backgroundColor : 'rgb(224,255,255)',
					borderColor : '#aaa',
					fontWeight : 200,
					borderWidth : 1,
					borderRadius : 4,
					formatter : [ '{a|{c}}' ].join('\n'),
					rich : {
						a : {
							align : 'center',
							color : '#1E90FF',
							fontSize : 10,
							textShadowBlur : 2,
							// textShadowColor: '#7A67EE',
							textBorderColor : '#9F79EE',
							textBorderWidth : 1
						},
					}
				}
			},
			coordinateSystem : 'cartesian2d',
			data : classDatas1

		}, {
			name : '双周',
			type : 'scatter',
			symbol : 'pin',
			symbolSize : 60,
			// symbolRotate:30,
			label : {
				normal : {
					show : true,
					rotate : 30,
					backgroundColor : 'rgb(224,255,255)',
					borderColor : '#aaa',
					borderWidth : 1,
					borderRadius : 4,
					formatter : [ '{a|{c}}' ].join('\n'),
					rich : {
						a : {
							align : 'center',
							color : '#1E90FF',
							fontSize : 10,
							textShadowBlur : 1,
							// textShadowColor: '#7A67EE',
							textBorderColor : '#9F79EE',
							textBorderWidth : 1
						},
					}
				}
			},
			coordinateSystem : 'cartesian2d',
			data : classDatas2

		} ]
	};
	var myChart = echarts.init(document.getElementById('MyChat'));
	myChart.setOption(option);
}

//对象转换成数组
function data2Array(data){
	//console.log(data);
	let a=[];
	for(let i=0;i<data.length;i++){
		let b=[];
		b=data[i].date.split(",");
		b.reverse();
		let c="\n"+data[i].cname+"\n"+data[i].address;
		b[2]=c;
		//console.log(b);
		a[i]=b;
		//console.log(c)
	}
	//console.log(a);
	//转换完成调用画图方法
	DrawEchart(a);
}


getData();
//data2Array(data);



/*
 * window.onresize = function(){
 * //document.getElementById('MyChat').innerHTML=""; var myChart =
 * echarts.init(document.getElementById('MyChat')); myChart.setOption(option);
 * console.log("aaaa000"); }
 */