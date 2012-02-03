<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.1.min.js" />"></script>

<script type="text/javascript">
var serverUrl = "http://localhost:8080/yourphotos/statistics/poblaciones-sugerencias";
//Load the Visualization API and the piechart package.
google.load('visualization', '1.0', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(getDataTable);

function getDataTable() {
	$.getJSON(serverUrl, function(data) {
		console.debug(data);
		drawChart(data);
	});
}

function drawChart(dataTable) {		
	
	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn(dataTable.columnsType[0], dataTable.columns[0]);
	data.addColumn(dataTable.columnsType[1], dataTable.columns[1]);
	data.addRows(dataTable.table);
	
	// Set chart options
	var options = {'title':'Numero de sugerencias por poblacion',
	               'width':400,
	               'height':300};
	
	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	chart.draw(data, options);
}

</script>
</head>
<body>
<div id="chart_div" style="width:400; height:300"></div>
</body>
</html>