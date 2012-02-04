var estadisticas1 = "http://localhost:8080/yourphotos/statistics/poblaciones-sugerencias";
var estadisticas2 = "http://localhost:8080/yourphotos/statistics/geo-estadistica";
//Load the Visualization API and the piechart package.
google.load('visualization', '1.0', {'packages':['corechart','geochart']});

// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(getDataTable);

function getDataTable() {
	$.getJSON(estadisticas1, function(data) {
		drawChart(data);
	});
	$.getJSON(estadisticas2, function(data) {
		drawGeoChart(data);
		drawPieChart(data);
	});
}

function getDataForChart(dataTable) {
	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn(dataTable.columnsType[0], dataTable.columns[0]);
	data.addColumn(dataTable.columnsType[1], dataTable.columns[1]);
	data.addRows(dataTable.table);
	return data;
}

function drawChart(dataTable) {	
	var data = getDataForChart(dataTable);
	
	// Set chart options
	var options = {'title':'Número de sugerencias por poblacion',
	               'width':800,
	               'height':600};
	
	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	chart.draw(data, options);
}

function drawGeoChart(dataTable) {	
	var data = getDataForChart(dataTable);
	
	var options = {
		width:800,
        height:600,
		region: 'ES',
		displayMode: 'markers',
		colorAxis: {colors: ['green', 'orange', 'yellow']}
	};
	
	var chart = new google.visualization.GeoChart(document.getElementById('chart_div2'));
	chart.draw(data, options);
}

function drawPieChart(dataTable) {
	var data = getDataForChart(dataTable);
	var options = {
		width: 450, height: 300,
		title: '% Sugerencias por provincia'
	};
	var chart = new google.visualization.PieChart(document.getElementById('chart_div3'));
	chart.draw(data, options);
}