function writtingEffect(element, speed) {
  var text = element.innerHTML;
  element.innerHTML = "";
  var i = 0;
  var timer = setInterval(function() {
    if (i < text.length) {
      element.append(text.charAt(i));
      i++;
    } else {
      clearInterval(timer);
    }
  }, speed);
}

var speed = 75;
var h1 = document.querySelector('h1');
var delay = h1.innerHTML.length * speed + speed;

writtingEffect(h1, speed); // Effecture l'action dès la fin du chargement de index.html

var idProfile = 0;

function createNewProfile(name) {
  new Profile(name, idProfile);
}
