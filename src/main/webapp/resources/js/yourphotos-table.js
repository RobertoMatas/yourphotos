var estadisticas = "http://localhost:8080/yourphotos/statistics/table-estadistica";

google.load('visualization', '1.0', {'packages':['table']});

// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(getDataTable);

function getDataTable() {
	$.getJSON(estadisticas, function(data) {
		drawTableChart(data);
	});
}

function getDataForChart(dataTable) {
	// Create the data table.
	var data = new google.visualization.DataTable();
	var columns = dataTable.columns;
	var rows = dataTable.table;
	for ( var i = 0; i < columns.length; i++) {
		data.addColumn(dataTable.columnsType[i], columns[i]);
	}
	data.addRows(rows.length);
	for ( var i = 0; i < rows.length; i++) {
		for ( var j = 0; j < rows[i].length; j++) {
			data.setCell(i, j, rows[i][j]);
		}
	}
	return data;
}

function drawTableChart(dataTable) {	
	var data = getDataForChart(dataTable);
	
	var table = new google.visualization.Table(document.getElementById('chart_table'));
    table.draw(data, {showRowNumber: true});
}