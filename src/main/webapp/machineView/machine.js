var token;
var state;
var endTime;
// Running,Out_Of_Order,Idle,Logged_In,Locked
// self executing function, executes on page initialization
(function() {
  hideAll();

  if(typeof token == 'undefined'){
    //authenticate
    $.when(authenticate()).done(function(tokenJSON){
      token = tokenJSON['token'];
      console.log("Token value: " + token)
        //getStatus
      $.when(getMachineState()).done(function(data){
        state = data['status'];
        //intiliaze view
        initializeState(state);
      });
    });
  }else{
    $.when(getMachineState()).done(function(data){
      state = data['status'];
      initializeState(state);
    });
  }
})();

function authenticate(){
  var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/auth/machine",
  "method": "POST",
  "headers": {
    "Content-Type": "application/json",
    "cache-control": "no-cache",
  },
  "processData": false,
  "data": "{\n\t\"identifier\":\"123ABC\"\n}"
  }
  return $.ajax(settings);
}

function hideAll(){
  $("#WelcomeScreen").hide();
  $("#SelectProgram").hide();
  $("#RunningScreen").hide();
  $("#EmployeScreen").hide();
  $("#FinishedScreen").hide();
}

function getMachineState(){
  var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/machine/getStatus",
  "method": "GET",
  "headers": {
    "Authorization": "Bearer " + token,
    "cache-control": "no-cache",
  }
}
  return $.ajax(settings);
}

function initializeState(state){
  console.log("initializing state: " + state);
  switch (state) {
  //  Running,Out_Of_Order,Idle,Logged_In,Locked
    case "Idle":
      initIdleScreen();
      break;
    case "Running":
      getRunningEndTime();
      break;
    case "Out_Of_Order":
      //TODO When employe logs in on machine it should have to option to put the machine out of order
      $("#EmployeScreen").show();
      break;
    case "Logged_In":
      var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/machine/getLoggedInInfo",
            "method": "GET",
            "headers": {
              "cache-control": "no-cache",
              "Authorization": "Bearer " + token
            },
            "processData": false,
          }
      $.ajax(settings).done(function (response) {
      initAuthScreen(response);
      });
      break;
    case "Locked":
      initLockedScreen();
      break;
    default:
      // add error screen
  }
}

function getRunningEndTime(){
  var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/machine/getEndTime",
  "method": "GET",
  "headers": {
    "Authorization": "Bearer "+token,
    "cache-control": "no-cache",
  }
}

$.ajax(settings).done(function (response) {
  initRunningScreen(response['EndTime']);
});
}

//USER LOGIN
$("#WelcomeScreen button").click(function() {
  var input  = $("#WelcomeScreen input").val();
  console.log("input: " + input);
  userAuthenticate(input);
});

function userAuthenticate(userAuthItem){
  var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/machine/login?authItemToken=" + userAuthItem,
  "method": "POST",
  "headers": {
    "Content-Type": "application/json",
    "cache-control": "no-cache",
    "Authorization": "Bearer " + token
  },
  "processData": false,
  "data": "{\"authItemToken\":1}"
  }

  $.ajax(settings).done(function (response) {
    console.log(response);
    initAuthScreen(response);
  }).fail(function(jqXHR, textStatus){
    console.log("FAIL: " + textStatus)
  });

}
// USER LOGOUT
$("#SelectProgram > button").click(function(){
  userLogout();
})

function userLogout(){
    var settings = {
    "async": true,
    "crossDomain": true,
    "url": "http://localhost:8080/machine/logout",
    "method": "POST",
    "headers": {
      "cache-control": "no-cache",
      "Authorization": "Bearer " + token,
    }
  }

  $.ajax(settings).done(function (response) {
    console.log(response);
    initIdleScreen();
  });
}
//startProgram
function startProgram(programId){
    var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/machine/startProgram?programId="+programId,
  "method": "POST",
    "headers": {
      "Authorization": "Bearer " + token,
      "cache-control": "no-cache",
    }
  }
  $.ajax(settings).done(function (response) {
    initRunningScreen(response['EndTime'])
  });
}

//Cancel Program
$("#RunningScreen > div > span > button").click(function(){
  console.log("Cancel Program: "+ $("#RunningScreen input").val());
  unlockMachine($("#RunningScreen input").val());
});

//Unlock Machine
$("#FinishedScreen > div > span > button").click(function(){
  console.log("Cancel Program: "+ $("#FinishedScreen input").val());
  unlockMachine($("#FinishedScreen input").val());
})

function unlockMachine(authItem){
  var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/machine/unlockMachine?authItemToken="+ authItem,
  "method": "POST",
  "headers": {
    "Authorization": "Bearer "+ token,
    "cache-control": "no-cache",
  }
}
  $.ajax(settings).done(function (response) {
    console.log("before idle screen: " + response['status'])
    if(response['status']){
      console.log("init idle screen");
      initIdleScreen();
    }
  });
}

//
//functions for the sole purpose to change te view
//
function initAuthScreen(obj){
  hideAll();
  $("#SelectProgram").show();
  //set Account balance
  $("#SelectProgram > p:nth-child(3) > span").text("€"  + obj['Account Balance']);
  //set programslist
  function prepProgramItem(id,name,duration,price){
    return `<div class="card">
       <div class="card-header">
         <h5 class="mb-0">
           <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse`+id +`" aria-controls="collapseOne">
             `+ name +`<span class="badge badge-primary">€`+ price+`</span>
           </button>
         </h5>
       </div>
       <div id="collapse`+id +`" class="collapse show" data-parent="#programAccordion">
         <div class="card-body">
           `+ duration +`
           <button type="button" class="btn btn-primary" onclick="startProgram(`+id+`)" >startProgram</button>
         </div>
       </div>
    </div>`;
  }
  var result ="";
  for(var i=0;i<obj['Programs'].length;i++){
    var program = obj['Programs'][i];
    result += prepProgramItem(program['id'],program['name'],program['duration'],program['cost']);
  }
  //add programs to UI
  $("#programAccordion").html(result);
  //collapse all programs
  $('.collapse').collapse()

}
function initIdleScreen(){
  hideAll();
  $("#WelcomeScreen").show();
  $("#WelcomeScreen input").val("") ;

}
function initRunningScreen(time){
  hideAll();
  $("#RunningScreen").show();
  $("#RunningScreen input").val("") ;
  initTimer(time);
}
function initLockedScreen(){
  hideAll();
  $("#FinishedScreen").show();
  $("#FinishedScreen input").val("") ;
}


function initTimer(time){
  // Set the date we're counting down to
var countDownDate =time;

// Update the count down every 1 second
var x = setInterval(function() {

  // Get todays date and time
  var now = new Date().getTime();

  // Find the distance between now and the count down date
  var distance = countDownDate - now;

  // Time calculations for days, hours, minutes and seconds
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

  var result = "";
  if(days>0){
    result += days + "d ";
  }
  if(hours>0){
    result += hours + "h ";
  }
  result +=minutes + "m " + seconds + "s ";

  // Display the result in the element with id="demo"
  document.getElementById("Running Timer").innerHTML = result

  // If the count down is finished, write some text
  if (distance < 0) {
    clearInterval(x);
    getMachineState().done(function(status){
      state = status['status'];
      //intiliaze view
      initializeState(state);
    })
  }
}, 1000);
}
