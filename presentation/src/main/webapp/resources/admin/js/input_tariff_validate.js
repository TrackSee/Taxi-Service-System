/**
 * Created by Vitalii Diravka on 05.05.2015.
 * Tariff input validation
 */
function check(index, prefix, button) {

    var inputPrice = document.getElementById(prefix + index);
    var textMsg = document.getElementById('text_' + prefix + index);
    var err = document.getElementById('err_' + prefix + index);

    inputPrice.onclick = function () {
        textMsg.value = "";
    }

    inputPrice.oninput = function() {
        function isNumeric(n) {
            return !isNaN(parseFloat(n)) && isFinite(n);
        }
        if ((isNumeric(inputPrice.value) == false) || (inputPrice.value < 0)) { // введено не число
            // показать ошибку
            this.className = "err_" + prefix + index;
            err.value = 'Invalid data'
            document.getElementById(button+index).disabled = true;
            document.getElementById(button+index).style.backgroundColor = "grey";
    }
        else {
            if (this.className == 'err_' + prefix + index) { // сбросить состояние "ошибка", если оно есть
                this.className = "";
                err.value = "";
                document.getElementById(button+index).disabled = false
                document.getElementById(button+index).style.backgroundColor = "";
            }
        }
    }
};



