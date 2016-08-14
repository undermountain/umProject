/**
 *
 */

$(document).ready(function(){
	$(".errorlist").click(function(){
		$(this).fadeOut();
	});

	$("._multifield > input").change(addMultiInput);

	$("select[readonly] > option:not([selected])").attr("disabled","disabled");

});

function addMultiInput(){
	var parent=$(this).parent().get(0);
	var maxIndex=$(parent).children("input").length-1;

	for(var i=maxIndex;i>=0;i--){
		var input=$(parent).children("input").get(i);
		if(maxIndex==i){
			if(input.value.trim()!=""){
				var temp=parent.children[0].children[0].cloneNode(true);
				$(temp).change(addMultiInput);
				parent.appendChild(temp);
			}
		}else if(input.value.trim()==""){
			$(input).remove();
		}
	}
}
