/**
 * This script configures datetimepicker.
 * "format" option sets date format.
 * "autoclose" option closes datetimepicker
 * window after the user has selected a date.
 * "todayBtn" option allows user to get current
 * date and time.
 * "pickerPosition" option sets datetimepicker
 * window position relative to the field.
 * "startDate" option prohibits choosing date that
 * earlier than current.
 *
 * @author Sharaban Sasha
 */
$(".form_datetime").datetimepicker({
    format: 'yyyy-mm-dd hh:ii',
    autoclose: true,
    todayBtn: true,
    pickerPosition: "bottom-center",
    startDate: getCurrentDate()
});

/**
 * returns formatted date and time
 * earlier which datetimepicker will
 * not allow select any date.
 *
 * @author Sharaban Sasha
 * @returns date in format (yyyy-MM-dd hh:mm)
 */
function  getCurrentDate(){
    var today = new Date();
    var mm = today.getMinutes();
    var hh = today.getHours();
    var dd = today.getDate();
    var MM = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();


    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }
    if(hh<10){
        hh='0'+hh
    }
    if(MM<10){
        MM='0'+MM
    }
    var today = yyyy+'-'+MM+'-'+dd+' '+hh+':'+mm;
    return today;
}