(function(){
    var option1 = document.getElementById('option1'),
        option2 = document.getElementById('option2'),
        option3 = document.getElementById('option3'),
        inputAdmin = document.getElementById('admin');
        inputAdmin.style.display = "none";

    option1.addEventListener('click', () => {mostrarInputSegunSelecione()});
    option2.addEventListener('click', () => {mostrarInputSegunSelecione()});
    option3.addEventListener('click', () => {mostrarInputSegunSelecione()});

    var mostrarInputSegunSelecione = () => {
        if(option2.checked || option3.checked){
            inputAdmin.style.display = "block";
        }else{
            inputAdmin.style.display = "none";
        }
    }
}())