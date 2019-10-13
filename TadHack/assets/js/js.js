$(document).ready(function(){
    getTxt();

    //$("#submit").click(sendData);
    $(document).on("click", "#submit",sendData);
    $(document).on("click", ".dohvatiMe" , prikaziFormu);
    $(document).on("click", ".ugasiMe" , skloniFormu);
});


function getTxt() {
    $.ajax({
        url: "models/getDataFromTxt.php",
        method: "post",
        dataType: "json",
        success: function (data) {
            showTxt(data);
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

function showTxt(data) {
    var html = "";
    for(let d of data){
        var obj = d.split("|");
        //console.log(obj);
        html+=` 
         <div class="video-item">
            <div class="video-thumb">
                <img src="${obj[0]}" alt="Movie${3}">
            </div>
            <div class="video-details">

                
                <div class="video-attributes">
                    <p class="cast"><label>Description:</label>${obj[1]}</p>
                    <p class="duration"><label>Date:</label> ${obj[2]}</p>
                    
                </div>
                
            </div>
            <div class="watch-now-wrap">
                <a class="btn-watch-now dohvatiMe" data-idHidden="${obj[3]}" href="#">Remind me</a>
            </div>
         </div>
        `
    }
    $("#wrapEvent").html(html);

}
function prikaziFormu(e) {
    e.preventDefault();
    let id = $(this).data("idhidden").trim();
    console.log(id);
    $("#popUp").css("display","block");
    $("#hidenPolje").val(id);

}
function skloniFormu(e) {
    e.preventDefault();
    $("#popUp").css("display","none");
    obrisiNumber();

}
function sendData() {
    var mobileNumber = $("#number").val().trim();
    var hiddenId = $("#hidenPolje").val().trim();


    var reNumber = /\d/;

    var greske = [];

    if(!reNumber.test(mobileNumber)){
        toastr.error("Please enter valid mobile number");
        greske.push("Pogresan broj");
    }


    if(greske == 0){

        $.ajax({
            url:"models/insertInTxt.php",
            method: "post",
            data:{
                mobileNumber: mobileNumber,
                hiddenId: hiddenId

            },
            success: function () {
                toastr.success("You have successfully organized");
                obrisiNumber();
            },
            error: function (xhr, error, status) {
                console.log(error);
            }
        });

    }



}

function obrisiNumber() {
    $("#number").val("");
}
