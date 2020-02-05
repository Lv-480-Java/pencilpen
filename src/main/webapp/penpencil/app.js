
window.addEventListener("load", function onWindowLoad() {

  initializePalette();

  var canvas = document.getElementById("canvas");
  var context = canvas.getContext("2d");

  context.lineCap = "round";
  context.lineWidth = 10;

  var button_plus = document.getElementById("bigger");
  var button_minus = document.getElementById("smaller");

  var SubmitButton = document.getElementById("submit-button");

  button_plus.addEventListener('click', function changeColor(e) {
    context.lineWidth = context.lineWidth + 2;
  });
  button_minus.addEventListener('click', function changeColor(e) {
    context.lineWidth = context.lineWidth - 2;
  });
   SubmitButton.addEventListener('click', function changeColor(e) {
      putImage();
    });

  canvas.onmousemove = function drawIfPressed(e) {
    var x = e.offsetX;
    var y = e.offsetY;
    var dx = e.movementX;
    var dy = e.movementY;
    if (e.buttons > 0) {
      context.beginPath();
      context.moveTo(x, y);
      context.lineTo(x - dx, y - dy);
      context.stroke();
      context.closePath();
    }
  };
  function initializePalette() {
    var cusid_ele = document.getElementsByClassName('palete-block');
    for (var i = 0; i < cusid_ele.length; ++i) {
      var item = cusid_ele[i];
      item.addEventListener('click', function changeColor(e) {
        context.strokeStyle = e.target.style.backgroundColor;
      });
    }
  }

  function putImage() {
    var canvas = document.getElementById("canvas");
    var ctx1 = canvas.getContext("2d");
    var dataURL = canvas.toDataURL("image/png");

    img = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");

    var form = $("#input-form").serializeArray();

  
    var package={ 
      tag: $("#tag").val(),
      description: $("#description").val(),
      imageData: img
     }

    $.ajax({
        url: '/add',
        type: 'POST',
        data: JSON.stringify(package),
        processData: false,
        contentType: "text/html",
        success: function(respons){
        }
    });

  }
});
