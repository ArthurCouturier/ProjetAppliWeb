/*'use strict';

// Declare app level module which depends on views, and core components
angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'myApp.version'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/view1'});
}]);*/

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


writtingEffect(h1, speed);
