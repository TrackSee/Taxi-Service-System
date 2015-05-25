/**
 * @author Ruslan Gunavardana
 */
$(".form_datetime").datetimepicker({
    format: 'hh:ii dd/mm/yyyy',
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
function getCurrentDate() {
    var today = new Date();
    var mm = today.getMinutes();
    var hh = today.getHours();
    var dd = today.getDate();
    var MM = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();


    if (dd < 10) {
        dd = '0' + dd
    }
    if (mm < 10) {
        mm = '0' + mm
    }
    if (hh < 10) {
        hh = '0' + hh
    }
    if (MM < 10) {
        MM = '0' + MM
    }
    var today = hh + ':' + mm + ' ' + dd + '/' + MM + '/' + yyyy;
    return today;
}