var canvases = document.getElementsByClassName("canvas");
var images = document.getElementsByClassName("image");

for (i = 0; i < canvases.length; i++) {
    var canvas = (document.getElementsByClassName("canvas"))[i];
    var image = (document.getElementsByClassName("image"))[i];
    const ctx = canvas.getContext('2d');
    ctx.drawImage(image, 0, -150, 600, 450);
}
