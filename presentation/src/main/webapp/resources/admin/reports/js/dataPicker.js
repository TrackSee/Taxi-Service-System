$('#start .date').datepicker({
    'format': 'yyyy-mm-dd',
    'autoclose': true
});

var basicExampleEl = document.getElementById('start');
var datepair = new Datepair(basicExampleEl);

$('#end .date').datepicker({
    'format': 'yyyy-mm-dd',
    'autoclose': true
});

var basicExampleEl2 = document.getElementById('end');
var datepair2 = new Datepair(basicExampleEl2);