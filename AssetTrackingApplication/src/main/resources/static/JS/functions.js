/**
 * 
 */

function insertTextToInput(id, text){
	var area = document.getElementById(id);
	area.value = text;
}


function showPass(id, thisItem) {
	  var area = document.getElementById(id); 
	  area.type = "password";
	  thisItem = document.getElementById(thisItem);
	  thisItem.style.display = 'none';
	}

function enableButton(id){
	var button = document.getElementById(id);
	button.disabled = false;
}

function checkFile(fileid, buttonid){
	
	var file = document.getElementById(fileid);
	var button = document.getElementById(buttonid);

	
	if(file.files.length == 0){
		button.disabled = true;
		alert("Please select a file first");
		 
	}
	else{  
		button.disabled = false; 
	}
}